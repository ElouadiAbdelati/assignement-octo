package ma.octo.assignement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDto {
  private Long id;
  private String username;
  private String genre;
  private String nom;
  private String prenom;
  private Date dateNaissance;
}
