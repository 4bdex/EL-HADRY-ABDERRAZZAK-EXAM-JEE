package elhadry.abderrazzak.bank_backend.web;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import elhadry.abderrazzak.bank_backend.dtos.CreditDTO;
import elhadry.abderrazzak.bank_backend.services.CreditService;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
@RequiredArgsConstructor
public class CreditRestController {
    private final CreditService creditService;

    @GetMapping
    public List<CreditDTO> getAllCredits() {
        return creditService.getAllCredits();
    }

    @GetMapping("/{id}")
    public CreditDTO getCredit(@PathVariable Long id) {
        return creditService.getCreditById(id);
    }

    @PostMapping
    public CreditDTO saveCredit(@RequestBody CreditDTO creditDTO) {
        return creditService.saveCredit(creditDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCredit(@PathVariable Long id) {
        creditService.deleteCredit(id);
    }

    @GetMapping("/client/{clientId}")
    public List<CreditDTO> getCreditsByClient(@PathVariable Long clientId) {
        return creditService.getCreditsByClientId(clientId);
    }
}
