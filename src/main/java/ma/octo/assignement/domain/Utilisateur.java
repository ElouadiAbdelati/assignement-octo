package ma.octo.assignement.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "UTILISATEUR")
public class Utilisateur {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 10, nullable = false, unique = true)
  private String username;

  @Column(length = 10, nullable = false)
  private String genre;

  @Column(length = 60, nullable = false)
  private String nom;

  @Column(length = 60, nullable = false)
  private String prenom;

  @JsonFormat(pattern = "dd/MM/yyyy")
  @Temporal(TemporalType.DATE)
  private Date dateNaissance;


}
