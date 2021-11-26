package ma.octo.assignement.web;

import ma.octo.assignement.response.Result;
import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/copmtes")
class CompteController {


    @Autowired
    private CompteService compteService;

    @GetMapping("/")
    public Result<List<Compte>> loadAl() {
        return compteService.findAll();
    }

}
