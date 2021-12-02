package ma.octo.assignement.service;


import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.response.Result;

import java.util.List;

public interface VersementService {
    Result<List<Versement>> findAll();
    Result<Void> save(Versement versement);
}
