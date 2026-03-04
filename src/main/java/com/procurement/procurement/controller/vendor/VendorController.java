package com.procurement.procurement.controller.vendor;

import com.procurement.procurement.entity.vendor.Vendor;
import com.procurement.procurement.service.vendor.VendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    // ===================== Create Vendor =====================
    @PostMapping("/create")
    public ResponseEntity<Vendor> createVendor(@RequestBody Vendor vendor) {
        vendor.setStatus("ACTIVE");
        return ResponseEntity.ok(vendorService.createVendor(vendor));
    }

    // ===================== Get All Vendors =====================
    @GetMapping("/all")
    public ResponseEntity<List<Vendor>> getAllVendors() {
        return ResponseEntity.ok(vendorService.getAllVendors());
    }

    @GetMapping("/active")
    public ResponseEntity<List<Vendor>> getActiveVendors() {
        return ResponseEntity.ok(vendorService.getActiveVendors());
    }

    // ===================== Get Vendor By ID =====================
    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable Long id) {
        return ResponseEntity.ok(vendorService.getVendorById(id));
    }

    // ===================== Update Vendor =====================
    @PutMapping("/update/{id}")
    public ResponseEntity<Vendor> updateVendor(@PathVariable Long id,
                                               @RequestBody Vendor vendor) {
        return ResponseEntity.ok(vendorService.updateVendor(id, vendor));
    }

    // ===================== Delete Vendor =====================
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
        return ResponseEntity.ok(Map.of("message", "Vendor deleted successfully"));
    }
}