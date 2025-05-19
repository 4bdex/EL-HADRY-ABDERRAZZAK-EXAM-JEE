package ma.enset.app_backend.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;
import ma.enset.app_backend.enums.TypeRemboursement;

@Entity
@Data
public class Remboursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private double montant;
    @Enumerated(EnumType.STRING)
    private TypeRemboursement type;

    @ManyToOne
    @JoinColumn(name = "credit_id")
    private Credit credit;
}
