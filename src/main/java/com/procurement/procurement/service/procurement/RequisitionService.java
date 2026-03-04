package com.procurement.procurement.service.procurement;

import com.procurement.procurement.dto.procurement.RequisitionRequestDTO;
import com.procurement.procurement.entity.procurement.Requisition;
import com.procurement.procurement.entity.user.User;
import com.procurement.procurement.entity.vendor.Vendor;
import com.procurement.procurement.mapper.RequisitionMapper;
import com.procurement.procurement.repository.procurement.RequisitionRepository;
import com.procurement.procurement.repository.user.UserRepository;
import com.procurement.procurement.repository.vendor.VendorRepository;
import com.procurement.procurement.service.audit.AuditService;
import com.procurement.procurement.service.notification.NotificationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class RequisitionService {

    private final RequisitionRepository requisitionRepository;
    private final UserRepository userRepository;
    private final VendorRepository vendorRepository;
    private final AuditService auditService;
    private final NotificationService notificationService;

    public RequisitionService(RequisitionRepository requisitionRepository,
                              UserRepository userRepository,
                              VendorRepository vendorRepository,
                              AuditService auditService,
                              NotificationService notificationService) {
        this.requisitionRepository = requisitionRepository;
        this.userRepository = userRepository;
        this.vendorRepository = vendorRepository;
        this.auditService = auditService;
        this.notificationService = notificationService;
    }

    // ===================== CREATE =====================
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE','PROCUREMENT_MANAGER')")
    public Requisition createRequisition(RequisitionRequestDTO dto) {

        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Vendor vendor = null;
        if (dto.getVendorId() != null) {
            vendor = vendorRepository.findById(dto.getVendorId()).orElse(null);
        }

        Requisition requisition = RequisitionMapper.toEntity(dto, currentUser, vendor);

        // ✅ Auto-generate requisition number if not provided
        if (requisition.getRequisitionNumber() == null || requisition.getRequisitionNumber().trim().isEmpty()
                || requisition.getRequisitionNumber().equalsIgnoreCase("null")) {
            requisition.setRequisitionNumber(generateRequisitionNumber());
        }

        Requisition saved = requisitionRepository.save(requisition);

        // ✅ Trigger alert to Admin
        notificationService.sendRequisitionAlert(saved.getRequisitionNumber(), username);

        // ✅ CORRECT AUDIT CALL
        auditService.log(
                "Requisition",
                saved.getId(),
                "CREATE",
                "Requisition created successfully");

        return saved;
    }

    private String generateRequisitionNumber() {
        java.time.format.DateTimeFormatter dtf = java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd");
        return "REQ-" + LocalDateTime.now().format(dtf) + "-" + (System.currentTimeMillis() % 10000);
    }

    // ===================== DELETE =====================
    @PreAuthorize("hasAnyRole('ADMIN','PROCUREMENT_MANAGER')")
    public void deleteRequisition(Long id) {
        Requisition req = requisitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Requisition not found with id: " + id));
        requisitionRepository.delete(req);

        auditService.log(
                "Requisition",
                id,
                "DELETE",
                "Requisition deleted successfully");
    }

    // ===================== UPDATE =====================
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE','PROCUREMENT_MANAGER')")
    public Requisition updateRequisition(Long id, Requisition updatedReq) {

        Requisition req = requisitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Requisition not found with id: " + id));

        if (updatedReq.getRequisitionNumber() != null && !updatedReq.getRequisitionNumber().trim().isEmpty()
                && !updatedReq.getRequisitionNumber().equalsIgnoreCase("null")) {
            req.setRequisitionNumber(updatedReq.getRequisitionNumber());
        }

        if (updatedReq.getItems() != null) {
            req.setItems(updatedReq.getItems());
        }

        if (updatedReq.getStatus() != null) {
            req.setStatus(updatedReq.getStatus());
        }

        if (updatedReq.getVendor() != null) {
            req.setVendor(updatedReq.getVendor());
        }

        req.setUpdatedAt(LocalDateTime.now());

        Requisition saved = requisitionRepository.save(req);

        // ✅ CORRECT AUDIT CALL
        auditService.log(
                "Requisition",
                saved.getId(),
                "UPDATE",
                "Requisition updated successfully");

        return saved;
    }

    @PreAuthorize("hasAnyRole('ADMIN','PROCUREMENT_MANAGER')")
    public List<Requisition> getAllRequisitions() {
        List<Requisition> all = requisitionRepository.findAll();
        // Fix any legacy null numbers
        all.forEach(r -> {
            if (r.getRequisitionNumber() == null || r.getRequisitionNumber().equalsIgnoreCase("null")) {
                r.setRequisitionNumber(generateRequisitionNumber());
                requisitionRepository.save(r);
            }
        });
        return all;
    }

    // ===================== GET MY =====================
    @PreAuthorize("hasRole('EMPLOYEE')")
    public List<Requisition> getMyRequisitions() {

        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return requisitionRepository.findByRequestedBy(currentUser);
    }

    // ===================== FILTER BY STATUS =====================
    @PreAuthorize("hasAnyRole('ADMIN','PROCUREMENT_MANAGER')")
    public List<Requisition> getRequisitionsByStatus(String status) {
        return requisitionRepository.findByStatus(status);
    }
}