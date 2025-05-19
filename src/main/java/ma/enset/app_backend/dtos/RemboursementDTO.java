package ma.enset.app_backend.dtos;

import lombok.Data;
import ma.enset.app_backend.enums.TypeRemboursement;
import java.time.LocalDate;

@Data
public class RemboursementDTO {
    private Long id;
    private LocalDate date;
    private double montant;
    private TypeRemboursement type;
    private Long creditId;
}
