package ma.octo.assignement.service;


import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.response.Result;

import java.util.List;

public interface UtilisateurService {
    Result<List<Utilisateur> > findAll();
    Result<Utilisateur> save(Utilisateur utilisateur);

}
