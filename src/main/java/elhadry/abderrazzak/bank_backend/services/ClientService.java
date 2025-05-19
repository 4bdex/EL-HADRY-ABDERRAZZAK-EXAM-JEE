package elhadry.abderrazzak.bank_backend.services;

import java.util.List;

import elhadry.abderrazzak.bank_backend.dtos.ClientDTO;

public interface ClientService {
    List<ClientDTO> getAllClients();

    ClientDTO getClientById(Long id);

    ClientDTO saveClient(ClientDTO clientDTO);

    void deleteClient(Long id);
}
