package ma.octo.assignement.service;


import ma.octo.assignement.domain.enums.OperationType;

public interface AuditService {
    void save(String message, OperationType operationType);
}
