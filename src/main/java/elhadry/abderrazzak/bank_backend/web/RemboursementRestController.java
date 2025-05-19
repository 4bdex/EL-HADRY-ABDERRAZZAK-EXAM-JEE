package elhadry.abderrazzak.bank_backend.web;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import elhadry.abderrazzak.bank_backend.dtos.RemboursementDTO;
import elhadry.abderrazzak.bank_backend.services.RemboursementService;

import java.util.List;

@RestController
@RequestMapping("/api/remboursements")
@RequiredArgsConstructor
public class RemboursementRestController {
    private final RemboursementService remboursementService;

    @PreAuthorize("hasAuthority('SCOPE_ROLE_EMPLOYE') or hasAuthority('SCOPE_ROLE_ADMIN')")
    @GetMapping
    public List<RemboursementDTO> getAllRemboursements() {
        return remboursementService.getAllRemboursements();
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_CLIENT') or hasAuthority('SCOPE_ROLE_EMPLOYE') or hasAuthority('SCOPE_ROLE_ADMIN')")
    @GetMapping("/{id}")
    public RemboursementDTO getRemboursement(@PathVariable Long id) {
        return remboursementService.getRemboursementById(id);
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_EMPLOYE') or hasAuthority('SCOPE_ROLE_ADMIN')")
    @PostMapping
    public RemboursementDTO saveRemboursement(@RequestBody RemboursementDTO remboursementDTO) {
        return remboursementService.saveRemboursement(remboursementDTO);
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteRemboursement(@PathVariable Long id) {
        remboursementService.deleteRemboursement(id);
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_CLIENT') or hasAuthority('SCOPE_ROLE_EMPLOYE') or hasAuthority('SCOPE_ROLE_ADMIN')")
    @GetMapping("/credit/{creditId}")
    public List<RemboursementDTO> getRemboursementsByCredit(@PathVariable Long creditId) {
        return remboursementService.getRemboursementsByCreditId(creditId);
    }
}