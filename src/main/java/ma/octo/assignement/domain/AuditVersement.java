package ma.octo.assignement.domain;

import lombok.EqualsAndHashCode;
import ma.octo.assignement.domain.abstractions.Audit;

import javax.persistence.*;

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "AUDIT_VERSEMENT")
public class AuditVersement extends Audit {

}
