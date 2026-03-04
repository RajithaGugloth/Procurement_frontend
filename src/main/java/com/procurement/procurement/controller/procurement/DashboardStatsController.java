package com.procurement.procurement.controller.procurement;

import com.procurement.procurement.dto.procurement.DashboardStatsDTO;
import com.procurement.procurement.repository.procurement.ApprovalRepository;
import com.procurement.procurement.repository.procurement.RequisitionRepository;
import com.procurement.procurement.repository.vendor.VendorRepository;
import com.procurement.procurement.repository.audit.AuditLogRepository;
import com.procurement.procurement.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/procurement/stats")
public class DashboardStatsController {

    private final VendorRepository vendorRepository;
    private final RequisitionRepository requisitionRepository;
    private final ApprovalRepository approvalRepository;
    private final UserRepository userRepository;
    private final AuditLogRepository auditLogRepository;

    public DashboardStatsController(
            VendorRepository vendorRepository,
            RequisitionRepository requisitionRepository,
            ApprovalRepository approvalRepository,
            UserRepository userRepository,
            AuditLogRepository auditLogRepository) {
        this.vendorRepository = vendorRepository;
        this.requisitionRepository = requisitionRepository;
        this.approvalRepository = approvalRepository;
        this.userRepository = userRepository;
        this.auditLogRepository = auditLogRepository;
    }

    @GetMapping
    public ResponseEntity<DashboardStatsDTO> getStats() {
        long totalVendors = vendorRepository.countByStatus("ACTIVE");
        long activeRequisitions = requisitionRepository.countByStatus("PENDING");
        long pendingApprovals = approvalRepository.countByStatus("PENDING");

        DashboardStatsDTO stats = new DashboardStatsDTO(totalVendors, activeRequisitions, pendingApprovals);

        // Populate Admin-only stats
        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            stats.setTotalUsers(userRepository.count());
            stats.setTotalAuditLogs(auditLogRepository.count());
        }

        return ResponseEntity.ok(stats);
    }
}