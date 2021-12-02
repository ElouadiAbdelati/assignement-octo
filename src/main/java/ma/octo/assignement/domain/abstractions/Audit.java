package ma.octo.assignement.domain.abstractions;

import lombok.Data;
import ma.octo.assignement.domain.enums.OperationType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100)
    private String message;

    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @CreatedDate
    private Instant createdAt;

    private  String version;
}
