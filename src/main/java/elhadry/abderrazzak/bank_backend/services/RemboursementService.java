package elhadry.abderrazzak.bank_backend.services;

import java.util.List;

import elhadry.abderrazzak.bank_backend.dtos.RemboursementDTO;

public interface RemboursementService {
    List<RemboursementDTO> getAllRemboursements();

    RemboursementDTO getRemboursementById(Long id);

    RemboursementDTO saveRemboursement(RemboursementDTO remboursementDTO);

    void deleteRemboursement(Long id);

    List<RemboursementDTO> getRemboursementsByCreditId(Long creditId);
}
