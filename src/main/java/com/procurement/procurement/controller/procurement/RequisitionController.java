package com.procurement.procurement.controller.procurement;

import com.procurement.procurement.dto.procurement.RequisitionRequestDTO;
import com.procurement.procurement.entity.procurement.Requisition;
import com.procurement.procurement.service.procurement.RequisitionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/procurement/requisition")
public class RequisitionController {

    private final RequisitionService requisitionService;

    public RequisitionController(RequisitionService requisitionService) {
        this.requisitionService = requisitionService;
    }

    @PostMapping
    public ResponseEntity<Requisition> create(@RequestBody RequisitionRequestDTO requisitionDTO) {
        return ResponseEntity.ok(requisitionService.createRequisition(requisitionDTO));
    }

    @GetMapping
    public ResponseEntity<List<Requisition>> getAll() {
        if (org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")
                        || a.getAuthority().equals("ROLE_PROCUREMENT_MANAGER"))) {
            return ResponseEntity.ok(requisitionService.getAllRequisitions());
        }
        return ResponseEntity.ok(requisitionService.getMyRequisitions());
    }

    @GetMapping("/my")
    public ResponseEntity<List<Requisition>> getMy() {
        return ResponseEntity.ok(requisitionService.getMyRequisitions());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Requisition> update(@PathVariable Long id, @RequestBody Requisition updatedReq) {
        return ResponseEntity.ok(requisitionService.updateRequisition(id, updatedReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        requisitionService.deleteRequisition(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Requisition> updateStatus(@PathVariable Long id,
                                                    @RequestParam String status) {
        Requisition req = new Requisition();
        req.setStatus(status);
        return ResponseEntity.ok(requisitionService.updateRequisition(id, req));
    }
}