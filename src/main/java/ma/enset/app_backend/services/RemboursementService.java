package ma.enset.app_backend.services;

import ma.enset.app_backend.dtos.RemboursementDTO;
import java.util.List;

public interface RemboursementService {
    List<RemboursementDTO> getAllRemboursements();

    RemboursementDTO getRemboursementById(Long id);

    RemboursementDTO saveRemboursement(RemboursementDTO remboursementDTO);

    void deleteRemboursement(Long id);

    List<RemboursementDTO> getRemboursementsByCreditId(Long creditId);
}
