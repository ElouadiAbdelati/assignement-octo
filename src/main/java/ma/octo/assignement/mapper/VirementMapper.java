package ma.octo.assignement.mapper;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Virement;
import ma.octo.assignement.dto.VirementDto;

import java.util.Date;

public class VirementMapper {

    private VirementMapper() {

    }

    public static VirementDto toVirementDto(Virement virement) {
        VirementDto virementDto = new VirementDto();
        virementDto.setMontant(virement.getMontant());
        virementDto.setNumeroCompteEmetteur(virement.getCompteEmetteur().getNumero());
        virementDto.setNumeroCompteBeneficiaire(virement.getCompteBeneficiaire().getNumero());
        return virementDto;
    }

    public static Virement toVirement(VirementDto virementDto) {
        Virement virement = new Virement();
        virement.setMontant(virementDto.getMontant());
        virement.setDateExecution(new Date());
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
