package ma.octo.assignement.service.impl;

import ma.octo.assignement.domain.Audit;
import ma.octo.assignement.domain.enums.OperationType;
import ma.octo.assignement.repository.AuditRepository;
import ma.octo.assignement.service.AuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuditServiceImpl implements AuditService {

    Logger LOGGER = LoggerFactory.getLogger(AuditServiceImpl.class);

    @Autowired
    private AuditRepository auditRepository;

    public void save(String message,OperationType operationType) {
        LOGGER.info("Audit de l'événement {}", operationType);
        Audit audit = new Audit();
        audit.setOperationType(operationType);
        audit.setMessage(message);
        auditRepository.save(audit);
    }
}
