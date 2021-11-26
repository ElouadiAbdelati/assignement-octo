package ma.octo.assignement.service.impl;


import ma.octo.assignement.response.Result;
import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompteServiceImpl implements CompteService {

    @Autowired
    private CompteRepository compteRepository;

    @Override
    public Result<List<Compte>> findAll() {
         List<Compte>  comptes = compteRepository.findAll();
         Result<List<Compte>> result =new Result<>();
         result.setData(comptes);
         return result;
    }

    @Override
    public Result<Compte> save(Compte compte) {
        compte =compteRepository.save(compte);
        Result<Compte> result =new Result<>();
        result.setData(compte);
        return result ;
    }

    @Override
    public Result<Compte> findByNumero(String numero) {
        Compte compte = compteRepository.findByNumero(numero);
        Result<Compte> result =new Result<>();
        result.setData(compte);
        return result;
    }
}
