package com.auditlog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("project", "100-audit-log-system");
        response.put("message", "Project 100 Audit Log System Tier 10 SCALE Live - 100/102 98%");
        response.put("version", "1.0.0");
        response.put("tier", "Tier 10 System Design Level");
        response.put("port", "8102 local, 10000 Render");
        response.put("features", new String[]{
                "Caching Caffeine 1000 max 600s recordStats",
                "Security Headers X-Content-Type-Options nosniff X-Frame-Options DENY X-XSS-Protection",
                "Builder Pattern AuditLog.Builder",
                "Strategy Pattern User vs Action vs Resource vs Hybrid sorted by timestamp",
                "Repository Pattern JpaRepository",
                "H2 Database auditdb",
                "JUnit 5 6/6 Green Bar",
                "Docker Multi-Stage",
                "Render Deploy Live"
        });
        response.put("endpoints", new String[]{
                "GET / - Home Live JSON",
                "GET /health - Health UP",
                "POST /api/audit?userId=&action=&resource=&resourceId=&details=&ipAddress=&severity= - Create Audit Log",
                "GET /api/audit - List All Audit Logs",
                "GET /api/audit/user/{userId} - Get by User",
                "GET /api/audit/action/{action} - Get by Action",
                "GET /api/audit/resource/{resource} - Get by Resource",
                "GET /api/audit/strategy?type=user|action|resource|hybrid&filter= - Strategy Pattern",
                "GET /api/audit/search?action= - Search by Action",
                "GET /api/audit/search/resource?resource= - Search by Resource",
                "GET /api/audit/count - Count",
                "GET /api/audit/info - Info"
        });
        return response;
    }

    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("database", "H2 auditdb");
        response.put("cache", "Caffeine 1000 max 600s Stats");
        response.put("security", "Headers nosniff DENY XSS");
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        return response;
    }
}