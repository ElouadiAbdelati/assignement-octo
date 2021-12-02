package ma.octo.assignement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.octo.assignement.domain.enums.OperationType;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditVirementDto {
  private Long id;
  private String message;
  private OperationType operationType;
  private Instant createdAt;
  private  String version;
}
