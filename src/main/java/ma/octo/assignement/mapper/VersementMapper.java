package ma.octo.assignement.mapper;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.domain.Versement;
import ma.octo.assignement.dto.VersementDto;

import java.util.List;
import java.util.stream.Collectors;

public class VersementMapper {

    private VersementMapper() {

    }

    public static List<VersementDto> toList(List<Versement> versements) {
        return versements.stream().map(VersementMapper::toVersementDto).collect(Collectors.toList());
    }

    public static VersementDto toVersementDto(Versement versement) {
        VersementDto versementDto = new VersementDto();
        versementDto.setMontant(versement.getMontant());
        versementDto.setMotif(versement.getMotif());
        versementDto.setNomEmetteur(versement.getNomEmetteur());
        versementDto.setPrenomEmetteur(versement.getPrenomEmetteur());
        versementDto.setNumeroCompteBeneficiaire(versement.getCompteBeneficiaire().getNumero());
        return versementDto;
    }

    public static Versement toVersement(VersementDto versementDto) {
        Versement versement = new Versement();
        versement.setMontant(versementDto.getMontant());
        versement.setNomEmetteur(versementDto.getNomEmetteur());
        versement.setPrenomEmetteur(versementDto.getPrenomEmetteur());
        Compte compteBeneficiaire = new Compte();
        compteBeneficiaire.setNumero(versementDto.getNumeroCompteBeneficiaire());
        versement.setCompteBeneficiaire(compteBeneficiaire);
        versement.setMotif(versementDto.getMotif());
        return versement;
    }
}
