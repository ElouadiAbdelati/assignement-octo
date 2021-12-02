package ma.octo.assignement.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VirementDto {

   @NotNull(message = "{transfer.transmitter_account_number.not_empty}")
   String numeroCompteEmetteur;

   @NotNull(message = "{transfer.beneficiary_account_number.not_empty}")
   String numeroCompteBeneficiaire;
   private Date dateExecution;
   @NotBlank(message = "{transfer.pattern.not_empty}")
   String motif;

   @NotNull(message = "{transfer.amount.not_empty}")
   BigDecimal montant;
}
