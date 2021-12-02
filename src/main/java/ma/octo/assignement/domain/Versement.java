package ma.octo.assignement.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ma.octo.assignement.domain.abstractions.Operation;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "VERSEMENT")
public class Versement extends Operation {
  private String nomEmetteur;
  private String prenomEmetteur;
}
