package elhadry.abderrazzak.bank_backend.web;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import elhadry.abderrazzak.bank_backend.dtos.ClientDTO;
import elhadry.abderrazzak.bank_backend.services.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientRestController {
    private final ClientService clientService;

    @PreAuthorize("hasAuthority('SCOPE_ROLE_EMPLOYE') or hasAuthority('SCOPE_ROLE_ADMIN')")
    @GetMapping
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_CLIENT') or hasAuthority('SCOPE_ROLE_EMPLOYE') or hasAuthority('SCOPE_ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_EMPLOYE') or hasAuthority('SCOPE_ROLE_ADMIN')")
    @PostMapping
    public ClientDTO saveClient(@RequestBody ClientDTO clientDTO) {
        return clientService.saveClient(clientDTO);
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
