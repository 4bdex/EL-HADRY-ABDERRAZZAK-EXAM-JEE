package elhadry.abderrazzak.bank_backend.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import elhadry.abderrazzak.bank_backend.dtos.ClientDTO;
import elhadry.abderrazzak.bank_backend.entities.Client;
import elhadry.abderrazzak.bank_backend.mappers.ClientMapper;
import elhadry.abderrazzak.bank_backend.repositories.ClientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream().map(clientMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClientById(Long id) {
        return clientRepository.findById(id).map(clientMapper::toDTO).orElse(null);
    }

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);
        Client saved = clientRepository.save(client);
        return clientMapper.toDTO(saved);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
