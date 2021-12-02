package ma.octo.assignement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.octo.assignement.domain.Utilisateur;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompteDto {
  private Long id;
  private String numero;
  private String rib;
  private BigDecimal solde;
  private String username;

}
