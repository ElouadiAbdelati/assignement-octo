package ma.octo.assignement.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VirementDto {

   @NotNull(message = "Le numéro de compte source est obligatoire")
   String numeroCompteEmetteur;

   @NotNull(message = "Le numéro de compte destinataire est obligatoire")
   String numeroCompteBeneficiaire;

   @NotBlank(message = "Le motif du virement est obligatoire")
   String motif;

   @NotNull(message = "Le montant du virement est obligatoire")
   BigDecimal montant;
}
