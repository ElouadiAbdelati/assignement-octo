package ma.octo.assignement.web;

import ma.octo.assignement.dto.CompteDto;
import ma.octo.assignement.mapper.CompteMapper;
import ma.octo.assignement.response.Result;
import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.service.CompteService;
import ma.octo.assignement.util.messaging.LocalMessageReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/copmtes")
class CompteController {

    @Autowired
    private CompteService compteService;

    @Autowired
    private LocalMessageReader localMessageReader;

    @GetMapping("/")
    public Result<List<CompteDto>> loadAl() {
        Result<List<Compte>> result =compteService.findAll();
        Result<List<CompteDto>> resultDto = new Result<>();
        resultDto.setErrors(result.getErrors());
        resultDto.setInfos(result.getInfos());
        resultDto.setData(CompteMapper.toList(result.getData()));
        return resultDto;
    }


    @GetMapping("/test")
    public  String test(){
        return  localMessageReader.getMessage("test") ;
    }
}
