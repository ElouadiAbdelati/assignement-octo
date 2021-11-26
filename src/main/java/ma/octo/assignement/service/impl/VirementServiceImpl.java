package ma.octo.assignement.service.impl;


import lombok.SneakyThrows;
import ma.octo.assignement.response.Result;
import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.domain.enums.OperationType;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.VirementRepository;
import ma.octo.assignement.service.AuditService;
import ma.octo.assignement.service.CompteService;
import ma.octo.assignement.service.VirementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class VirementServiceImpl implements VirementService {
    public static final int MONTANT_MAXIMAL = 10000;
    Logger logger = LoggerFactory.getLogger(VirementServiceImpl.class);

    @Autowired
    private VirementRepository virementRepository;

    @Autowired
    private AuditService auditService;
    @Autowired
    private CompteService compteService;

    @Override
    public Result<List<Virement>> findAll() {
        List<Virement>  virements = virementRepository.findAll();
        Result<List<Virement>> result =new Result<>();
        result.setData(virements);
        return result;
    }

    @SneakyThrows
    @Override
    public Result<Void> save(Virement virement) {

        Compte compteEmetteur = compteService.findByNumero(virement.getCompteEmetteur().getNumero()).getData();
        Compte compteBeneficiaire = compteService
                .findByNumero(virement.getCompteBeneficiaire().getNumero()).getData();

        if (compteEmetteur == null) {
            throw new CompteNonExistantException("Compte emetteur Non existant");
        }

        if (compteBeneficiaire == null) {
            throw new CompteNonExistantException("Compte beneficiaire Non existant");
        }

        if (virement.getMontant().intValue() == 0) {
            throw new TransactionException("Montant vide");
        } else if (virement.getMontant().intValue() < 10) {
            throw new TransactionException("Montant minimal de virement non atteint");
        } else if (virement.getMontant().intValue() > MONTANT_MAXIMAL) {
            throw new TransactionException("Montant maximal de virement dépassé");
        }

        if (virement.getMotif().isEmpty()) {
            throw new TransactionException("Motif vide");
        }

        if (compteEmetteur.getSolde().intValue() - virement.getMontant().intValue() < 0) {
            logger.error("Solde insuffisant pour l'utilisateur");
        }

        if (compteEmetteur.getSolde().intValue() - virement.getMontant().intValue() < 0) {
            logger.error("Solde insuffisant pour l'utilisateur");
        }

        compteEmetteur.setSolde(compteEmetteur.getSolde().subtract(virement.getMontant()));
        compteService.save(compteEmetteur);

        compteBeneficiaire
                .setSolde(new BigDecimal(compteBeneficiaire.getSolde().intValue() + virement.getMontant().intValue()));
        compteService.save(compteBeneficiaire);

        virement.setDateExecution(virement.getDateExecution());
        virement.setCompteBeneficiaire(compteBeneficiaire);
        virement.setCompteEmetteur(compteEmetteur);
        virement.setMontant(virement.getMontant());

        virementRepository.save(virement);

        auditService.save("Virement depuis " + virement.getCompteEmetteur().getNumero() + " vers " + virement
                .getCompteBeneficiaire().getNumero() + " d'un montant de " + virement.getCompteBeneficiaire().getNumero(), OperationType.VIREMENT);

        Result<Void> result =new Result<>();
        result.addInfosMessage("converti avec succes");

        return result ;
    }
}
