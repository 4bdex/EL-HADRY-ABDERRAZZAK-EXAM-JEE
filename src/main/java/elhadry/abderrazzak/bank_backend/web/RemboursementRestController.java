package elhadry.abderrazzak.bank_backend.web;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import elhadry.abderrazzak.bank_backend.dtos.RemboursementDTO;
import elhadry.abderrazzak.bank_backend.services.RemboursementService;

import java.util.List;

@RestController
@RequestMapping("/api/remboursements")
@RequiredArgsConstructor
public class RemboursementRestController {
    private final RemboursementService remboursementService;

    @GetMapping
    public List<RemboursementDTO> getAllRemboursements() {
        return remboursementService.getAllRemboursements();
    }

    @GetMapping("/{id}")
    public RemboursementDTO getRemboursement(@PathVariable Long id) {
        return remboursementService.getRemboursementById(id);
    }

    @PostMapping
    public RemboursementDTO saveRemboursement(@RequestBody RemboursementDTO remboursementDTO) {
        return remboursementService.saveRemboursement(remboursementDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRemboursement(@PathVariable Long id) {
        remboursementService.deleteRemboursement(id);
    }

    @GetMapping("/credit/{creditId}")
    public List<RemboursementDTO> getRemboursementsByCredit(@PathVariable Long creditId) {
        return remboursementService.getRemboursementsByCreditId(creditId);
    }
}
