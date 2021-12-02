package ma.octo.assignement.mapper;

import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.dto.UtilisateurDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UtilisateurMapper {

    private UtilisateurMapper() {

    }

    public static List<UtilisateurDto> toList(List<Utilisateur> utilisateurs) {
        return utilisateurs.stream().filter(Objects::nonNull).map(UtilisateurMapper::toUtilisateurDto).collect(Collectors.toList());
    }

    public static UtilisateurDto toUtilisateurDto(Utilisateur utilisateur) {
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setId(utilisateur.getId());
        utilisateurDto.setGenre(utilisateur.getGenre());
        utilisateurDto.setDateNaissance(utilisateur.getDateNaissance());
        utilisateurDto.setNom(utilisateur.getNom());
        utilisateurDto.setPrenom(utilisateur.getPrenom());
        utilisateurDto.setUsername(utilisateur.getUsername());
        return utilisateurDto;
    }

    public static Utilisateur toUtilisateur(UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setGenre(utilisateurDto.getGenre());
        utilisateur.setDateNaissance(utilisateurDto.getDateNaissance());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setUsername(utilisateurDto.getUsername());
        return utilisateur;
    }

}
