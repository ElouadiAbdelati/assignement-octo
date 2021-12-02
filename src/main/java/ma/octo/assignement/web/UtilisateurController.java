package ma.octo.assignement.web;

import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.dto.UtilisateurDto;
import ma.octo.assignement.mapper.UtilisateurMapper;
import ma.octo.assignement.response.Result;
import ma.octo.assignement.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/utilisateurs")
class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/")
   public Result<List<UtilisateurDto>> loadAll() {
        Result<List<Utilisateur>> result =utilisateurService.findAll();
        Result<List<UtilisateurDto>> resultDto = new Result<>();
        resultDto.setErrors(result.getErrors());
        resultDto.setInfos(result.getInfos());
        resultDto.setData(UtilisateurMapper.toList(result.getData()));
       return resultDto ;
    }


}
