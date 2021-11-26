package ma.octo.assignement.web;

import ma.octo.assignement.response.Result;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.dto.VirementDto;
import ma.octo.assignement.mapper.VirementMapper;
import ma.octo.assignement.service.VirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/virements")
class VirementController {

    @Autowired
    private VirementService virementService;

    @GetMapping("/")
    public Result<List<Virement>> loadAll() {
     return virementService.findAll();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Result<Void> save(@RequestBody @Valid VirementDto virementDto){
        return virementService.save(VirementMapper.toVirement(virementDto));
    }

}
