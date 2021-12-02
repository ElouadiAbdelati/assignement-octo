package ma.octo.assignement.web;

import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.dto.VersementDto;
import ma.octo.assignement.mapper.VersementMapper;
import ma.octo.assignement.response.Result;
import ma.octo.assignement.service.VersementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/versements")
class VersementController {

    @Autowired
    private VersementService versementService;

    @GetMapping("/")
    public Result<List<VersementDto>> loadAll() {
        Result<List<Versement>> result =versementService.findAll();
        Result<List<VersementDto>> resultDto = new Result<>();
        resultDto.setErrors(result.getErrors());
        resultDto.setInfos(result.getInfos());
        resultDto.setData(VersementMapper.toList(result.getData()));
     return resultDto;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Result<Void> save(@RequestBody @Valid VersementDto versementDto){
        return versementService.save(VersementMapper.toVersement(versementDto));
    }

}
