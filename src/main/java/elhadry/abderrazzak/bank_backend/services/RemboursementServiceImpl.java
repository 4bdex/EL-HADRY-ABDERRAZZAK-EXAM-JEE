package elhadry.abderrazzak.bank_backend.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import elhadry.abderrazzak.bank_backend.dtos.RemboursementDTO;
import elhadry.abderrazzak.bank_backend.entities.Credit;
import elhadry.abderrazzak.bank_backend.entities.Remboursement;
import elhadry.abderrazzak.bank_backend.mappers.ClientMapper;
import elhadry.abderrazzak.bank_backend.repositories.CreditRepository;
import elhadry.abderrazzak.bank_backend.repositories.RemboursementRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RemboursementServiceImpl implements RemboursementService {
    private final RemboursementRepository remboursementRepository;
    private final CreditRepository creditRepository;
    private final ClientMapper clientMapper;

    public RemboursementServiceImpl(RemboursementRepository remboursementRepository, CreditRepository creditRepository,
            ClientMapper clientMapper) {
        this.remboursementRepository = remboursementRepository;
        this.creditRepository = creditRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public List<RemboursementDTO> getAllRemboursements() {
        return remboursementRepository.findAll().stream().map(clientMapper::remboursementToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RemboursementDTO getRemboursementById(Long id) {
        return remboursementRepository.findById(id).map(clientMapper::remboursementToDTO).orElse(null);
    }

    @Override
    public RemboursementDTO saveRemboursement(RemboursementDTO dto) {
        Remboursement remboursement = new Remboursement();
        remboursement.setId(dto.getId());
        remboursement.setDate(dto.getDate());
        remboursement.setMontant(dto.getMontant());
        remboursement.setType(dto.getType());
        if (dto.getCreditId() != null) {
            Credit credit = creditRepository.findById(dto.getCreditId()).orElse(null);
            remboursement.setCredit(credit);
        }
        Remboursement saved = remboursementRepository.save(remboursement);
        return clientMapper.remboursementToDTO(saved);
    }

    @Override
    public void deleteRemboursement(Long id) {
        remboursementRepository.deleteById(id);
    }

    @Override
    public List<RemboursementDTO> getRemboursementsByCreditId(Long creditId) {
        return remboursementRepository.findAll().stream()
                .filter(r -> r.getCredit() != null && r.getCredit().getId().equals(creditId))
                .map(clientMapper::remboursementToDTO)
                .collect(Collectors.toList());
    }
}
