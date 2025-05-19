package ma.enset.app_backend.dtos;

import lombok.Data;
import ma.enset.app_backend.enums.StatutCredit;
import java.time.LocalDate;
import java.util.List;

@Data
public class CreditDTO {
    private Long id;
    private LocalDate dateDemande;
    private StatutCredit statut;
    private LocalDate dateAcception;
    private double montant;
    private int dureeRemboursement;
    private double tauxInteret;
    private Long clientId;
    private List<RemboursementDTO> remboursements;
    // Champs spécifiques pour les sous-types
    private String motif; // Pour personnel et professionnel
    private String typeBien; // Pour immobilier
    private String raisonSociale; // Pour professionnel
    private String typeCredit; // Pour indiquer le type (PERSONNEL, IMMOBILIER, PROFESSIONNEL)
}
