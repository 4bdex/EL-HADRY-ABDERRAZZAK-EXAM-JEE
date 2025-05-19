package ma.enset.app_backend.services;

import ma.enset.app_backend.dtos.ClientDTO;
import java.util.List;

public interface ClientService {
    List<ClientDTO> getAllClients();

    ClientDTO getClientById(Long id);

    ClientDTO saveClient(ClientDTO clientDTO);

    void deleteClient(Long id);
}
