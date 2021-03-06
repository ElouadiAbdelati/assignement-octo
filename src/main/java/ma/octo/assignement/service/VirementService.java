package ma.octo.assignement.service;


import ma.octo.assignement.response.Result;
import ma.octo.assignement.domain.Virement;

import java.util.Date;
import java.util.List;

public interface VirementService {
    Result<List<Virement>> findAll();
    Result<Void> save(Virement virement);
    int countByCompteBeneficiairRibAndDateExecution(String rib , Date date);
}
