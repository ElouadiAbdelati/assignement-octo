package ma.octo.assignement.service.impl;

import lombok.RequiredArgsConstructor;
import ma.octo.assignement.domain.AuditVersement;
import ma.octo.assignement.domain.AuditVirement;
import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.domain.enums.OperationType;
import ma.octo.assignement.repository.AuditVersementRepository;
import ma.octo.assignement.repository.AuditVirementRepository;
import ma.octo.assignement.service.AuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    Logger logger = LoggerFactory.getLogger(AuditServiceImpl.class);
    private final  AuditVirementRepository auditVirementRepository;
    private final AuditVersementRepository auditVersementRepository;

    public void saveAuditVirement(Virement virement) {
        logger.info("Audit de l'événement {}", OperationType.VIREMENT);
        AuditVirement audit = new AuditVirement();
        audit.setOperationType(OperationType.VIREMENT);
        Map<String,String> messages = new HashMap<>();
        messages.put("numero_metteur",virement.getCompteEmetteur().getNumero());
        messages.put("numero_beneficiaire",virement.getCompteBeneficiaire().getNumero());
        messages.put("montant",virement.getMontant().toString());
        audit.setMessage(messages.toString());
        audit.setVersion("v1");
        auditVirementRepository.save(audit);
    }

    public void saveAuditVersement(Versement versement) {
        logger.info("Audit de l'événement {}", OperationType.VERSEMENT);
        AuditVersement audit = new AuditVersement();
        audit.setOperationType(OperationType.VERSEMENT);
        Map<String,String> messages = new HashMap<>();
        messages.put("nom_emetteur",versement.getNomEmetteur());
        messages.put("prenom_emetteur",versement.getPrenomEmetteur());
        messages.put("numero_beneficiaire",versement.getCompteBeneficiaire().getNumero());
        messages.put("montant",versement.getMontant().toString());
        audit.setMessage(messages.toString());
        audit.setVersion("v1");
        auditVersementRepository.save(audit);
    }
}
