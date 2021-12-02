package ma.octo.assignement.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ma.octo.assignement.response.Result;
import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.VirementRepository;
import ma.octo.assignement.service.AuditService;
import ma.octo.assignement.service.CompteService;
import ma.octo.assignement.service.VirementService;
import ma.octo.assignement.util.Constants;
import ma.octo.assignement.util.messaging.LocalMessageReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VirementServiceImpl implements VirementService {
    private final  VirementRepository virementRepository;
    private final AuditService auditService;
    private final CompteService compteService;
    private final LocalMessageReader localMessageReader;

    @Override
    public Result<List<Virement>> findAll() {
        List<Virement> virements = virementRepository.findAll();
        Result<List<Virement>> result = new Result<>();
        result.setData(virements);
        return result;
    }

    @Override
    @Transactional
    public Result<Void> save(Virement virement) {
        Compte compteEmetteur = compteService.findByNumero(virement.getCompteEmetteur().getNumero()).getData();
        Compte compteBeneficiaire = compteService
                .findByNumero(virement.getCompteBeneficiaire().getNumero()).getData();
        validate(virement, compteEmetteur, compteBeneficiaire);
        return run(virement, compteEmetteur, compteBeneficiaire);
    }

    @Override
    public int countByCompteBeneficiairRibAndDateExecution(String rib, Date date) {
        return virementRepository.countByCompteBeneficiairRibAndDateExecution(rib,date);
    }

    @SneakyThrows
    private void validate(Virement virement, Compte compteEmetteur, Compte compteBeneficiaire) {
        if (compteEmetteur == null) {
            throw new CompteNonExistantException(localMessageReader.getMessage("transfer.save.transmitter.not_found"));
        }

        if (compteBeneficiaire == null) {
            throw new CompteNonExistantException(localMessageReader.getMessage("transfer.save.beneficiary.not_found"));
        }

        if(virementRepository.countByCompteBeneficiairRibAndDateExecution(compteBeneficiaire.getRib(),new Date())>=10){
            throw new TransactionException(localMessageReader.getMessage("transfer.save.max_virment"));
        }

       if (virement.getMontant().compareTo(BigDecimal.valueOf(Constants.MONTANT_MINIMAL)) <0) {
            throw new TransactionException(localMessageReader.getMessage("transfer.save.amount_not_reached"));
        } else if (virement.getMontant().compareTo(BigDecimal.valueOf(Constants.MONTANT_MAXIMAL)) > 0) {
            throw new TransactionException(localMessageReader.getMessage("transfer.save.amount_exceeded"));
        }

        if (compteEmetteur.getSolde().subtract(virement.getMontant()).compareTo(BigDecimal.ZERO)< 0) {
            throw new TransactionException(localMessageReader.getMessage("transfer.save.insufficient_balance"));
        }
    }

    private Result<Void> run(Virement virement, Compte compteEmetteur, Compte compteBeneficiaire) {
        compteEmetteur.setSolde(compteEmetteur.getSolde().subtract(virement.getMontant()));
        compteService.save(compteEmetteur);
        compteBeneficiaire
                .setSolde(compteBeneficiaire.getSolde().add(virement.getMontant()));

        compteService.save(compteBeneficiaire);
        virement.setCompteBeneficiaire(compteBeneficiaire);
        virement.setCompteEmetteur(compteEmetteur);
        virementRepository.save(virement);
        auditService.saveAuditVirement(virement);

        Result<Void> result = new Result<>();
        result.addInfosMessage(localMessageReader.getMessage("transfer.save.created_sucess"));
        return result;
    }
}
