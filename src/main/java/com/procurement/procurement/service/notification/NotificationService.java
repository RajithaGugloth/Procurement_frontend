package com.procurement.procurement.service.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public void sendRequisitionAlert(String requisitionNumber, String requestedBy) {
        logger.info("=================================================");
        logger.info("📧 MOCK EMAIL SENT TO ADMIN");
        logger.info("Subject: New Requisition for Approval - {}", requisitionNumber);
        logger.info("Body: User {} has created a new requisition ({}). Please log in to approve/reject.", requestedBy,
                requisitionNumber);
        logger.info("=================================================");
    }
}