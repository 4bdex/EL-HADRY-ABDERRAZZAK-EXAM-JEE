package elhadry.abderrazzak.bank_backend.services;

import java.util.List;

import elhadry.abderrazzak.bank_backend.dtos.CreditDTO;

public interface CreditService {
    List<CreditDTO> getAllCredits();

    CreditDTO getCreditById(Long id);

    CreditDTO saveCredit(CreditDTO creditDTO);

    void deleteCredit(Long id);

    List<CreditDTO> getCreditsByClientId(Long clientId);
}
