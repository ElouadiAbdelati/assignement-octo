package ma.octo.assignement.service.impl;


import lombok.RequiredArgsConstructor;
import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.repository.UtilisateurRepository;
import ma.octo.assignement.response.Result;
import ma.octo.assignement.service.UtilisateurService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;

    @Override
    public Result<List<Utilisateur>> findAll() {
        List<Utilisateur> utilisateurs =utilisateurRepository.findAll();
        Result<List<Utilisateur>> result =new Result<>();
        result.setData(utilisateurs);
        return result;
    }

    @Override
    public Result<Utilisateur> save(Utilisateur utilisateur) {
        utilisateur = utilisateurRepository.save(utilisateur);
        Result<Utilisateur> result =new Result<>();
        result.setData(utilisateur);
        return result ;
    }
}
