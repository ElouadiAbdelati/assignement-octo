package ma.octo.assignement.service;


import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.domain.Virement;

public interface AuditService {
    void saveAuditVirement(Virement virement);
    void saveAuditVersement(Versement versement);

}
