package ma.enset.app_backend.services;

import ma.enset.app_backend.dtos.CreditDTO;
import java.util.List;

public interface CreditService {
    List<CreditDTO> getAllCredits();

    CreditDTO getCreditById(Long id);

    CreditDTO saveCredit(CreditDTO creditDTO);

    void deleteCredit(Long id);

    List<CreditDTO> getCreditsByClientId(Long clientId);
}
