package elhadry.abderrazzak.bank_backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

import elhadry.abderrazzak.bank_backend.enums.StatutCredit;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_credit")
@Data
public abstract class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateDemande;
    @Enumerated(EnumType.STRING)
    private StatutCredit statut;
    private LocalDate dateAcception;
    private double montant;
    private int dureeRemboursement;
    private double tauxInteret;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "credit")
    private List<Remboursement> remboursements;
}
