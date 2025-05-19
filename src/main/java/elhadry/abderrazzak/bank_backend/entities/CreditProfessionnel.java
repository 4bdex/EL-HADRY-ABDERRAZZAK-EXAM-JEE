package elhadry.abderrazzak.bank_backend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("PROFESSIONNEL")
@Data
@EqualsAndHashCode(callSuper = true)
public class CreditProfessionnel extends Credit {
    private String motif;
    private String raisonSociale;
}
