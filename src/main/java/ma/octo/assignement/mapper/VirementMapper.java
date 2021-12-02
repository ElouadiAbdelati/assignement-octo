package ma.octo.assignement.mapper;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.dto.VirementDto;

import java.util.List;
import java.util.stream.Collectors;

public class VirementMapper {

    private VirementMapper() {
    }

    public static List<VirementDto> toList(List<Virement> virements) {
        return virements.stream().map(VirementMapper::toVirementDto).collect(Collectors.toList());
    }

    public static VirementDto toVirementDto(Virement virement) {
        VirementDto virementDto = new VirementDto();
        virementDto.setMontant(virement.getMontant());
        virementDto.setNumeroCompteEmetteur(virement.getCompteEmetteur().getNumero());
        virementDto.setNumeroCompteBeneficiaire(virement.getCompteBeneficiaire().getNumero());
        virementDto.setDateExecution(virement.getDateExecution());
        return virementDto;
    }

    public static Virement toVirement(VirementDto virementDto) {
        Virement virement = new Virement();
        virement.setMontant(virementDto.getMontant());
        Compte compteEmetteur = new Compte();
        compteEmetteur.setNumero(virementDto.getNumeroCompteEmetteur());
        virement.setCompteEmetteur(compteEmetteur);
        Compte compteBeneficiaire = new Compte();
        compteBeneficiaire.setNumero(virementDto.getNumeroCompteBeneficiaire());
        virement.setCompteBeneficiaire(compteBeneficiaire);
        virement.setMotif(virementDto.getMotif());
        return virement;
    }
}
