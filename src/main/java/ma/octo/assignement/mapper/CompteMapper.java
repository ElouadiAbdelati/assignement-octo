package ma.octo.assignement.mapper;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.dto.CompteDto;
import java.util.List;
import java.util.stream.Collectors;

public class CompteMapper {

    private CompteMapper() {

    }

    public static List<CompteDto> toList(List<Compte> comptes) {
        return comptes.stream().map(CompteMapper::toCompteDto).collect(Collectors.toList());
    }

    public static CompteDto toCompteDto(Compte compte) {
        CompteDto compteDto = new CompteDto();
        compteDto.setNumero(compte.getNumero());
        compteDto.setSolde(compte.getSolde());
        compteDto.setRib(compte.getRib());
        compteDto.setId(compte.getId());
        compteDto.setUsername(compte.getUtilisateur().getUsername());
        return compteDto;
    }

    public static Compte toCompte(CompteDto compteDto) {
        Compte compte= new Compte();
        compte.setNumero(compteDto.getNumero());
        compte.setSolde(compteDto.getSolde());
        compte.setRib(compteDto.getRib());
        compte.setId(compteDto.getId());
        return compte;
    }

}
