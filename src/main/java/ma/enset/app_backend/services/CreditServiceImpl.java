package ma.enset.app_backend.services;

import ma.enset.app_backend.dtos.CreditDTO;
import ma.enset.app_backend.entities.*;
import ma.enset.app_backend.mappers.ClientMapper;
import ma.enset.app_backend.repositories.ClientRepository;
import ma.enset.app_backend.repositories.CreditRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CreditServiceImpl implements CreditService {
    private final CreditRepository creditRepository;
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public CreditServiceImpl(CreditRepository creditRepository, ClientRepository clientRepository,
            ClientMapper clientMapper) {
        this.creditRepository = creditRepository;
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public List<CreditDTO> getAllCredits() {
        return creditRepository.findAll().stream().map(clientMapper::creditToDTO).collect(Collectors.toList());
    }

    @Override
    public CreditDTO getCreditById(Long id) {
        return creditRepository.findById(id).map(clientMapper::creditToDTO).orElse(null);
    }

    @Override
    public CreditDTO saveCredit(CreditDTO creditDTO) {
        Credit credit;
        switch (creditDTO.getTypeCredit()) {
            case "PERSONNEL":
                CreditPersonnel cp = new CreditPersonnel();
                cp.setMotif(creditDTO.getMotif());
                credit = cp;
                break;
            case "IMMOBILIER":
                CreditImmobilier ci = new CreditImmobilier();
                ci.setTypeBien(creditDTO.getTypeBien());
                credit = ci;
                break;
            case "PROFESSIONNEL":
                CreditProfessionnel cpro = new CreditProfessionnel();
                cpro.setMotif(creditDTO.getMotif());
                cpro.setRaisonSociale(creditDTO.getRaisonSociale());
                credit = cpro;
                break;
            default:
                throw new IllegalArgumentException("Type de crédit inconnu");
        }
        credit.setId(creditDTO.getId());
        credit.setDateDemande(creditDTO.getDateDemande());
        credit.setStatut(creditDTO.getStatut());
        credit.setDateAcception(creditDTO.getDateAcception());
        credit.setMontant(creditDTO.getMontant());
        credit.setDureeRemboursement(creditDTO.getDureeRemboursement());
        credit.setTauxInteret(creditDTO.getTauxInteret());
        if (creditDTO.getClientId() != null) {
            clientRepository.findById(creditDTO.getClientId()).ifPresent(credit::setClient);
        }
        Credit saved = creditRepository.save(credit);
        return clientMapper.creditToDTO(saved);
    }

    @Override
    public void deleteCredit(Long id) {
        creditRepository.deleteById(id);
    }

    @Override
    public List<CreditDTO> getCreditsByClientId(Long clientId) {
        return creditRepository.findAll().stream()
                .filter(c -> c.getClient() != null && c.getClient().getId().equals(clientId))
                .map(clientMapper::creditToDTO)
                .collect(Collectors.toList());
    }
}
