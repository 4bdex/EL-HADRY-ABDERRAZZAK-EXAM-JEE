package elhadry.abderrazzak.bank_backend.dtos;

import lombok.Data;

import java.time.LocalDate;

import elhadry.abderrazzak.bank_backend.enums.TypeRemboursement;

@Data
public class RemboursementDTO {
    private Long id;
    private LocalDate date;
    private double montant;
    private TypeRemboursement type;
    private Long creditId;
}
