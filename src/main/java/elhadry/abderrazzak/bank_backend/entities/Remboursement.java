package elhadry.abderrazzak.bank_backend.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

import elhadry.abderrazzak.bank_backend.enums.TypeRemboursement;
import lombok.Data;

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
