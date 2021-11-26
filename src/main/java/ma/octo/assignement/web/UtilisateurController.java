package ma.octo.assignement.web;

import ma.octo.assignement.domain.Utilisateur;
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
   public Result<List<Utilisateur>> loadAll() {
       return utilisateurService.findAll();
    }


}
