package elhadry.abderrazzak.bank_backend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("IMMOBILIER")
@Data
@EqualsAndHashCode(callSuper = true)
public class CreditImmobilier extends Credit {
    private String typeBien;
}
