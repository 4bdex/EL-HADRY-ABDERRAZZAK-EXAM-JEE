package ma.enset.app_backend;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import ma.enset.app_backend.entities.*;
import ma.enset.app_backend.enums.StatutCredit;
import ma.enset.app_backend.enums.TypeRemboursement;
import ma.enset.app_backend.repositories.*;
import ma.enset.app_backend.services.ClientService;
import ma.enset.app_backend.services.CreditService;
import ma.enset.app_backend.services.RemboursementService;
import ma.enset.app_backend.dtos.ClientDTO;
import ma.enset.app_backend.dtos.CreditDTO;
import ma.enset.app_backend.dtos.RemboursementDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppBackend {

    public static void main(String[] args) {
        SpringApplication.run(AppBackend.class, args);
    }

    @Bean
    CommandLineRunner start(ClientService clientService, CreditService creditService,
            RemboursementService remboursementService) {
        return args -> {
            // Création de clients
            ClientDTO client1 = new ClientDTO();
            client1.setNom("Dupont");
            client1.setEmail("dupont@gmail.com");
            client1 = clientService.saveClient(client1);

            ClientDTO client2 = new ClientDTO();
            client2.setNom("Martin");
            client2.setEmail("martin@gmail.com");
            client2 = clientService.saveClient(client2);

            // Création de crédits
            CreditDTO cp = new CreditDTO();
            cp.setDateDemande(java.time.LocalDate.now());
            cp.setStatut(ma.enset.app_backend.enums.StatutCredit.EN_COURS);
            cp.setMontant(10000);
            cp.setDureeRemboursement(24);
            cp.setTauxInteret(4.5);
            cp.setMotif("Achat voiture");
            cp.setTypeCredit("PERSONNEL");
            cp.setClientId(client1.getId());
            cp = creditService.saveCredit(cp);

            CreditDTO ci = new CreditDTO();
            ci.setDateDemande(java.time.LocalDate.now());
            ci.setStatut(ma.enset.app_backend.enums.StatutCredit.ACCEPTE);
            ci.setDateAcception(java.time.LocalDate.now());
            ci.setMontant(200000);
            ci.setDureeRemboursement(120);
            ci.setTauxInteret(2.5);
            ci.setTypeBien("Maison");
            ci.setTypeCredit("IMMOBILIER");
            ci.setClientId(client2.getId());
            ci = creditService.saveCredit(ci);

            CreditDTO cpro = new CreditDTO();
            cpro.setDateDemande(java.time.LocalDate.now());
            cpro.setStatut(ma.enset.app_backend.enums.StatutCredit.REJETE);
            cpro.setMontant(50000);
            cpro.setDureeRemboursement(36);
            cpro.setTauxInteret(5.0);
            cpro.setMotif("Investissement matériel");
            cpro.setRaisonSociale("SARL Tech");
            cpro.setTypeCredit("PROFESSIONNEL");
            cpro.setClientId(client1.getId());
            cpro = creditService.saveCredit(cpro);

            // Création de remboursements
            RemboursementDTO r1 = new RemboursementDTO();
            r1.setDate(java.time.LocalDate.now());
            r1.setMontant(500);
            r1.setType(ma.enset.app_backend.enums.TypeRemboursement.MENSUALITE);
            r1.setCreditId(cp.getId());
            remboursementService.saveRemboursement(r1);

            RemboursementDTO r2 = new RemboursementDTO();
            r2.setDate(java.time.LocalDate.now());
            r2.setMontant(1000);
            r2.setType(ma.enset.app_backend.enums.TypeRemboursement.REMBOURSEMENT_ANTICIPE);
            r2.setCreditId(ci.getId());
            remboursementService.saveRemboursement(r2);

          
            System.out.println("\n--- Test Service: Liste des clients ---");
            for (ClientDTO c : clientService.getAllClients()) {
                System.out.println(c);
            }
            System.out.println("\n--- Test Service: Liste des crédits ---");
            for (CreditDTO cr : creditService.getAllCredits()) {
                System.out.println(cr);
            }
            System.out.println("\n--- Test Service: Liste des remboursements ---");
            for (RemboursementDTO r : remboursementService.getAllRemboursements()) {
                System.out.println(r);
            }
        };
    }

}
