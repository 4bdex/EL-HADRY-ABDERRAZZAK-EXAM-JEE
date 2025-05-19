package ma.enset.app_backend.mappers;

import ma.enset.app_backend.dtos.*;
import ma.enset.app_backend.entities.*;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper {
    public ClientDTO toDTO(Client client) {
        if (client == null)
            return null;
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setNom(client.getNom());
        dto.setEmail(client.getEmail());
        if (client.getCredits() != null) {
            dto.setCredits(client.getCredits().stream().map(this::creditToDTO).collect(Collectors.toList()));
        }
        return dto;
    }

    public Client toEntity(ClientDTO dto) {
        if (dto == null)
            return null;
        Client client = new Client();
        client.setId(dto.getId());
        client.setNom(dto.getNom());
        client.setEmail(dto.getEmail());
        // Les crédits sont gérés séparément
        return client;
    }

    public CreditDTO creditToDTO(Credit credit) {
        if (credit == null)
            return null;
        CreditDTO dto = new CreditDTO();
        dto.setId(credit.getId());
        dto.setDateDemande(credit.getDateDemande());
        dto.setStatut(credit.getStatut());
        dto.setDateAcception(credit.getDateAcception());
        dto.setMontant(credit.getMontant());
        dto.setDureeRemboursement(credit.getDureeRemboursement());
        dto.setTauxInteret(credit.getTauxInteret());
        dto.setClientId(credit.getClient() != null ? credit.getClient().getId() : null);
        if (credit.getRemboursements() != null) {
            dto.setRemboursements(
                    credit.getRemboursements().stream().map(this::remboursementToDTO).collect(Collectors.toList()));
        }
        if (credit instanceof CreditPersonnel) {
            dto.setMotif(((CreditPersonnel) credit).getMotif());
            dto.setTypeCredit("PERSONNEL");
        } else if (credit instanceof CreditImmobilier) {
            dto.setTypeBien(((CreditImmobilier) credit).getTypeBien());
            dto.setTypeCredit("IMMOBILIER");
        } else if (credit instanceof CreditProfessionnel) {
            dto.setMotif(((CreditProfessionnel) credit).getMotif());
            dto.setRaisonSociale(((CreditProfessionnel) credit).getRaisonSociale());
            dto.setTypeCredit("PROFESSIONNEL");
        }
        return dto;
    }

    public RemboursementDTO remboursementToDTO(Remboursement remboursement) {
        if (remboursement == null)
            return null;
        RemboursementDTO dto = new RemboursementDTO();
        dto.setId(remboursement.getId());
        dto.setDate(remboursement.getDate());
        dto.setMontant(remboursement.getMontant());
        dto.setType(remboursement.getType());
        dto.setCreditId(remboursement.getCredit() != null ? remboursement.getCredit().getId() : null);
        return dto;
    }

    // Ajoutez les méthodes de mapping inverse si besoin
}
