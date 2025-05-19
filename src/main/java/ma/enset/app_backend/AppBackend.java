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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

// import ma.enset.app_backend.dtos.*;

// import ma.enset.app_backend.repositories.*;
// import ma.enset.app_backend.services.*;

@SpringBootApplication
public class AppBackend {

    public static void main(String[] args) {
        SpringApplication.run(AppBackend.class, args);
    }
    // @Bean
    // CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
    // return args -> {
    // Stream.of("A","B","C").forEach(name->{
    // CustomerDTO customer=new CustomerDTO();
    // customer.setName(name);
    // customer.setEmail(name+"@gmail.com");
    // bankAccountService.saveCustomer(customer);
    // });
    // bankAccountService.listCustomers().forEach(customer->{
    // try {
    // bankAccountService.saveCurrentBankAccount(Math.random()*90000,9000,customer.getId());
    // bankAccountService.saveSavingBankAccount(Math.random()*120000,5.5,customer.getId());

    // } catch (CustomerNotFoundException e) {
    // e.printStackTrace();
    // }
    // });
    // List<BankAccountDTO> bankAccounts = bankAccountService.bankAccountList();
    // for (BankAccountDTO bankAccount:bankAccounts){
    // for (int i = 0; i <10 ; i++) {
    // String accountId;
    // if(bankAccount instanceof SavingBankAccountDTO){
    // accountId=((SavingBankAccountDTO) bankAccount).getId();
    // } else{
    // accountId=((CurrentBankAccountDTO) bankAccount).getId();
    // }
    // bankAccountService.credit(accountId,10000+Math.random()*120000,"Credit");
    // bankAccountService.debit(accountId,1000+Math.random()*9000,"Debit");
    // }
    // }
    // };
    // }
    // //@Bean
    // CommandLineRunner start(CustomerRepository customerRepository,
    // BankAccountRepository bankAccountRepository,
    // AccountOperationRepository accountOperationRepository){
    // return args -> {
    // Stream.of("M","K","O").forEach(name->{
    // Customer customer=new Customer();
    // customer.setName(name);
    // customer.setEmail(name+"@gmail.com");
    // customerRepository.save(customer);
    // });
    // customerRepository.findAll().forEach(cust->{
    // CurrentAccount currentAccount=new CurrentAccount();
    // currentAccount.setId(UUID.randomUUID().toString());
    // currentAccount.setBalance(Math.random()*90000);
    // currentAccount.setCreatedAt(new Date());
    // currentAccount.setStatus(AccountStatus.CREATED);
    // currentAccount.setCustomer(cust);
    // currentAccount.setOverDraft(9000);
    // bankAccountRepository.save(currentAccount);

    // SavingAccount savingAccount=new SavingAccount();
    // savingAccount.setId(UUID.randomUUID().toString());
    // savingAccount.setBalance(Math.random()*90000);
    // savingAccount.setCreatedAt(new Date());
    // savingAccount.setStatus(AccountStatus.CREATED);
    // savingAccount.setCustomer(cust);
    // savingAccount.setInterestRate(5.5);
    // bankAccountRepository.save(savingAccount);

    // });
    // bankAccountRepository.findAll().forEach(acc->{
    // for (int i = 0; i <10 ; i++) {
    // AccountOperation accountOperation=new AccountOperation();
    // accountOperation.setOperationDate(new Date());
    // accountOperation.setAmount(Math.random()*12000);
    // accountOperation.setType(Math.random()>0.5? OperationType.DEBIT:
    // OperationType.CREDIT);
    // accountOperation.setBankAccount(acc);
    // accountOperationRepository.save(accountOperation);
    // }

    // });
    // };

    // }
    @Bean
    CommandLineRunner start(ClientRepository clientRepository, CreditRepository creditRepository,
            RemboursementRepository remboursementRepository) {
        return args -> {
            // Création de clients
            Client client1 = new Client();
            client1.setNom("Dupont");
            client1.setEmail("dupont@gmail.com");
            clientRepository.save(client1);

            Client client2 = new Client();
            client2.setNom("Martin");
            client2.setEmail("martin@gmail.com");
            clientRepository.save(client2);

            // Création de crédits
            CreditPersonnel cp = new CreditPersonnel();
            cp.setDateDemande(LocalDate.now());
            cp.setStatut(StatutCredit.EN_COURS);
            cp.setMontant(10000);
            cp.setDureeRemboursement(24);
            cp.setTauxInteret(4.5);
            cp.setMotif("Achat voiture");
            cp.setClient(client1);
            creditRepository.save(cp);

            CreditImmobilier ci = new CreditImmobilier();
            ci.setDateDemande(LocalDate.now());
            ci.setStatut(StatutCredit.ACCEPTE);
            ci.setDateAcception(LocalDate.now());
            ci.setMontant(200000);
            ci.setDureeRemboursement(120);
            ci.setTauxInteret(2.5);
            ci.setTypeBien("Maison");
            ci.setClient(client2);
            creditRepository.save(ci);

            CreditProfessionnel cpro = new CreditProfessionnel();
            cpro.setDateDemande(LocalDate.now());
            cpro.setStatut(StatutCredit.REJETE);
            cpro.setMontant(50000);
            cpro.setDureeRemboursement(36);
            cpro.setTauxInteret(5.0);
            cpro.setMotif("Investissement matériel");
            cpro.setRaisonSociale("SARL Tech");
            cpro.setClient(client1);
            creditRepository.save(cpro);

            // Création de remboursements
            Remboursement r1 = new Remboursement();
            r1.setDate(LocalDate.now());
            r1.setMontant(500);
            r1.setType(TypeRemboursement.MENSUALITE);
            r1.setCredit(cp);
            remboursementRepository.save(r1);

            Remboursement r2 = new Remboursement();
            r2.setDate(LocalDate.now());
            r2.setMontant(1000);
            r2.setType(TypeRemboursement.REMBOURSEMENT_ANTICIPE);
            r2.setCredit(ci);
            remboursementRepository.save(r2);
        };
    }

}
