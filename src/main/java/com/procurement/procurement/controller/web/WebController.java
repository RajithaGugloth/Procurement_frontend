package com.procurement.procurement.controller.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/vendor")
    public String vendor() {
        return "vendor";
    }

    @GetMapping("/requisition")
    public String requisition() {
        return "requisition";
    }

    @GetMapping("/purchase-order")
    public String purchaseOrder() {
        return "purchase-order";
    }

    @GetMapping("/approval")
    public String approvalPage() {
        return "approval";
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public String usersPage() {
        return "users";
    }

    @GetMapping("/audit-logs")
    @PreAuthorize("hasRole('ADMIN')")
    public String auditLogsPage() {
        return "audit-logs";
    }
}