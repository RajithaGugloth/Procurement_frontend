#Java Based Smart Procurement & Vendor Management System
## Agile Documentation


## 1. Title
Java Based Smart Procurement & Vendor Management System

--------------------------------------------------------------

## 2. Problem Statement
Manual procurement processes cause delays
Lack of vendor performance tracking
No centralized purchase approval workflow
Poor transparency in quotation comparison
Risk of unauthorized approvals
No audit trail for compliance

------------------------------------------------------------

## 3. Solution
Centralized procurement management system
Automated purchase request workflow
Vendor onboarding & rating system
Smart quotation comparison
Role-based approval system
Secure JWT-based authentication
Real-time reporting & audit logs

----------------------------------------------------------

## 4. Technology Stack
Backend: Java, Spring Boot, Spring Security, JWT, Hibernate  
Database: MySQL  
Reporting: JasperReports  
Frontend: React  
Tools: IntelliJ, VS Code, Postman

-------------------------------------------------------------

## 5. Project Architecture
Architecture Pattern: Layered Architecture
Controller Layer (REST APIs)
Service Layer (Business Logic)
Repository Layer (Data Access)
Database Layer (MySQL)
Flow:
Client → Controller → Service → Repository → Database

--------------------------------------------------------------------------

## 6. Database Design
Core Tables:
users
roles
vendors
purchase_requests
quotations
purchase_orders
approvals
audit_logs
Relationships:
One User → Many Requests
One Request → Many Quotations
One Vendor → Many Quotations

---------------------------------------------------------------------------

## 7. Authentication Flow (JWT)
1. User login
2. JWT token generated
3. Token sent in Authorization header
4. Request validated using JWT filter
5. Access granted based on role

---------------------------------------------------

## 8: Complete Workflow
Employee submits Purchase Request
Manager reviews request
Approved request sent to Procurement
RFQ sent to Vendors
Vendors submit quotations
System compares quotations
Best vendor selected
Purchase Order generated
Order tracked until completion

--------------------------------------------------------

## 9. Role Responsibility

Admin
1.Manage users & roles
2.System monitoring

Employee
1.Submit purchase requests
2.Track request status

Manager
1.Approve / Reject requests
2.Procurement Officer

Send RFQ
1.Compare quotations
2.Generate PO

Vendor
1.Submit quotations
2.Upload documents

-------------------------------------------------------------

## 10. Requisition Lifecycle

Draft → Submitted → Manager Review → Approved
→ RFQ Sent → Quotation Received → Vendor Selected
→ PO Generated → Order Completed → Closed

------------------------------------------------------------

## 11. API Structure
Authentication APIs
1.POST /login
2.POST /register

Vendor APIs
1.POST /vendors
2.GET /vendors

Procurement APIs
1.POST /purchase-request
2.POST /quotation
3.POST /approve-request
4.POST /generate-po

--------------------------------------------------------------

## 12. Security Implementation
1.JWT-based authentication
2.Role-based authorization
3.Password encryption (BCrypt)
4.Secure API endpoints
5.Input validation
6.Audit logging
7.Exception handling

---------------------------------------------------------------

## 13. Error Handling
- Global exception handling
- Custom error messages
- Proper HTTP status codes

--------------------------------------------------------

## 14. Deployment Flow

1. Configure MySQL
2. Run mvn clean install
3. Run mvn spring-boot:run
4. Access at localhost:8082

----------------------------------------------------------

## 15. Key Features
- Role-Based Access Control
- Vendor Lifecycle Management
- Automated Approval Workflow
- Quotation Comparison Engine
- Purchase Order Generation
- Audit Trail System
- Dashboard & Reports
- Secure JWT Authentication

---------------------------------------------------------

## 16. Limitations
- No payment integration
- No email notification

--------------------------------------------------------------

## 17. Future Enhancements
- AI-based vendor recommendation
- Automated tender management
- Real-time email/SMS notifications
- ERP integration
- Multi-tenant architecture
- Microservices architecture
- Advanced analytics dashboard

--------------------------------------------------------------------

## 18. Conclusion
The Java Based Smart Procurement & Vendor Management System:
- Automates procurement lifecycle,Improves transparency,Reduces manual errors,Enhances vendor management,Ensures secure and scalable architecture,Built using Agile methodology for continuous improvement and structured development.

---------------------------------------------------------------------------

## 19. Demo
Demonstration includes:
- Login
- Create vendor
- Create requisition
- Approve requisition
- Create purchase order
- Approve purchase order
- Generate report

- Thank You


