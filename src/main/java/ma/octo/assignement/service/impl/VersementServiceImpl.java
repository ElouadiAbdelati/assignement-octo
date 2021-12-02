package ma.octo.assignement.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.VersementRepository;
import ma.octo.assignement.response.Result;
import ma.octo.assignement.service.AuditService;
import ma.octo.assignement.service.CompteService;
import ma.octo.assignement.service.VersementService;
import ma.octo.assignement.util.Constants;
import ma.octo.assignement.util.messaging.LocalMessageReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VersementServiceImpl implements VersementService {

    private final VersementRepository versementRepository;
    private final AuditService auditService;
    private final CompteService compteService;
    private final LocalMessageReader localMessageReader;

    @Override
    public Result<List<Versement>> findAll() {
        List<Versement> versements = versementRepository.findAll();
        Result<List<Versement>> result = new Result<>();
        result.setData(versements);
        return result;
    }

    @Override
    @Transactional
    public Result<Void> save(Versement versement) {
        Compte compteBeneficiaire = compteService
                .findByNumero(versement.getCompteBeneficiaire().getNumero()).getData();
        validate(versement, compteBeneficiaire);
        return run(versement, compteBeneficiaire);
    }

    @SneakyThrows
    private void validate(Versement versement, Compte compteBeneficiaire) {
        if (compteBeneficiaire == null) {
            throw new CompteNonExistantException(localMessageReader.getMessage("payment.save.beneficiary.not_found"));
        }
        if (versement.getMontant().compareTo(BigDecimal.valueOf(Constants.MONTANT_MINIMAL)) < 0) {
            throw new TransactionException(localMessageReader.getMessage("payment.save.amount_not_reached"));
        } else if (versement.getMontant().compareTo(BigDecimal.valueOf(Constants.MONTANT_MAXIMAL)) > 0) {
            throw new TransactionException(localMessageReader.getMessage("payment.save.amount_exceeded"));
        }

    }

    private Result<Void> run(Versement versement, Compte compteBeneficiaire) {

        compteBeneficiaire
                .setSolde(compteBeneficiaire.getSolde().add(versement.getMontant()));
        compteService.save(compteBeneficiaire);

        versement.setCompteBeneficiaire(compteBeneficiaire);
        versementRepository.save(versement);
        auditService.saveAuditVersement(versement);
        Result<Void> result = new Result<>();
        result.addInfosMessage(localMessageReader.getMessage("payment.save.created_sucess"));
        return result;
    }


}
