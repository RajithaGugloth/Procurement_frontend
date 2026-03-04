package com.procurement.procurement.dto.procurement;

public class DashboardStatsDTO {
    private long totalVendors;
    private long activeRequisitions;
    private long pendingApprovals;
    private Long totalUsers; // Admin only
    private Long totalAuditLogs; // Admin only

    public DashboardStatsDTO() {
    }

    public DashboardStatsDTO(long totalVendors, long activeRequisitions, long pendingApprovals) {
        this.totalVendors = totalVendors;
        this.activeRequisitions = activeRequisitions;
        this.pendingApprovals = pendingApprovals;
    }

    public DashboardStatsDTO(long totalVendors, long activeRequisitions, long pendingApprovals, Long totalUsers,
                             Long totalAuditLogs) {
        this.totalVendors = totalVendors;
        this.activeRequisitions = activeRequisitions;
        this.pendingApprovals = pendingApprovals;
        this.totalUsers = totalUsers;
        this.totalAuditLogs = totalAuditLogs;
    }

    public long getTotalVendors() {
        return totalVendors;
    }

    public void setTotalVendors(long totalVendors) {
        this.totalVendors = totalVendors;
    }

    public long getActiveRequisitions() {
        return activeRequisitions;
    }

    public void setActiveRequisitions(long activeRequisitions) {
        this.activeRequisitions = activeRequisitions;
    }

    public long getPendingApprovals() {
        return pendingApprovals;
    }

    public void setPendingApprovals(long pendingApprovals) {
        this.pendingApprovals = pendingApprovals;
    }

    public Long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(Long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public Long getTotalAuditLogs() {
        return totalAuditLogs;
    }

    public void setTotalAuditLogs(Long totalAuditLogs) {
        this.totalAuditLogs = totalAuditLogs;
    }
}