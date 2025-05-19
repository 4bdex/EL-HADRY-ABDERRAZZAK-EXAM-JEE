package elhadry.abderrazzak.bank_backend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("PERSONNEL")
@Data
@EqualsAndHashCode(callSuper = true)
public class CreditPersonnel extends Credit {
    private String motif;
}
