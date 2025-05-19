package elhadry.abderrazzak.bank_backend.web;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import elhadry.abderrazzak.bank_backend.dtos.ClientDTO;
import elhadry.abderrazzak.bank_backend.services.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientRestController {
    private final ClientService clientService;

    @GetMapping
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    public ClientDTO saveClient(@RequestBody ClientDTO clientDTO) {
        return clientService.saveClient(clientDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
