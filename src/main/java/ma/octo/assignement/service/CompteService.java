package ma.octo.assignement.service;


import ma.octo.assignement.response.Result;
import ma.octo.assignement.domain.Compte;

import java.util.List;

public interface CompteService {
    Result<List<Compte>> findAll();
    Result<Compte> save(Compte compte);
    Result<Compte> findByNumero(String numero);
}
