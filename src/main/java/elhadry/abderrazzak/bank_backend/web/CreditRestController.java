package elhadry.abderrazzak.bank_backend.web;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import elhadry.abderrazzak.bank_backend.dtos.CreditDTO;
import elhadry.abderrazzak.bank_backend.services.CreditService;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
@RequiredArgsConstructor
public class CreditRestController {
    private final CreditService creditService;

    @PreAuthorize("hasAuthority('SCOPE_ROLE_EMPLOYE') or hasAuthority('SCOPE_ROLE_ADMIN')")
    @GetMapping
    public List<CreditDTO> getAllCredits() {
        return creditService.getAllCredits();
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_CLIENT') or hasAuthority('SCOPE_ROLE_EMPLOYE') or hasAuthority('SCOPE_ROLE_ADMIN')")
    @GetMapping("/{id}")
    public CreditDTO getCredit(@PathVariable Long id) {
        return creditService.getCreditById(id);
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_CLIENT') or hasAuthority('SCOPE_ROLE_EMPLOYE') or hasAuthority('SCOPE_ROLE_ADMIN')")
    @PostMapping
    public CreditDTO saveCredit(@RequestBody CreditDTO creditDTO) {
        return creditService.saveCredit(creditDTO);
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteCredit(@PathVariable Long id) {
        creditService.deleteCredit(id);
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_CLIENT') or hasAuthority('SCOPE_ROLE_EMPLOYE') or hasAuthority('SCOPE_ROLE_ADMIN')")
    @GetMapping("/client/{clientId}")
    public List<CreditDTO> getCreditsByClient(@PathVariable Long clientId) {
        return creditService.getCreditsByClientId(clientId);
    }
}