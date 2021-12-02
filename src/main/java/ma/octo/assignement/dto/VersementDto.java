package ma.octo.assignement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VersementDto {
    private Long id;
    @NotNull(message = "{payment.amount.not_empty}")
    private BigDecimal montant;
    private Date dateExecution;
    @NotNull(message = "{payment.transmitter_account_number.not_empty}")
    private String nomEmetteur;
    @NotNull(message = "{payment.transmitter_account_number.not_empty}")
    private String prenomEmetteur;
    @NotNull(message = "{payment.beneficiary_account_number.not_empty}")
    private String numeroCompteBeneficiaire;
    @NotBlank(message = "{payment.pattern.notempty}")
    private String motif;
}
