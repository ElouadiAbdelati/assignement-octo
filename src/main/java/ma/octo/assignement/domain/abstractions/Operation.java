package ma.octo.assignement.domain.abstractions;

import lombok.Data;
import ma.octo.assignement.domain.Compte;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract  class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(precision = 16, scale = 2, nullable = false)
    private BigDecimal montant;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date dateExecution;

    @Column(length = 200)
    private String motif;

    @ManyToOne
    private Compte compteBeneficiaire;
}
