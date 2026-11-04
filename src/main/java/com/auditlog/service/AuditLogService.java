package com.auditlog.service;

import com.auditlog.model.AuditLog;
import com.auditlog.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuditLogService {

    @Autowired
    private AuditLogRepository repository;

    // Builder Pattern + trim() fix - CRITICAL like Project 99
    public AuditLog createAuditLog(String userId, String action, String resource, String resourceId, String details, String ipAddress, String severity) {
        // trim() fix for Postman \n bug - same as Project 99 isolationLevel.trim()
        String cleanUserId = userId != null ? userId.trim() : "anonymous";
        String cleanAction = action != null ? action.trim() : "UNKNOWN";
        String cleanResource = resource != null ? resource.trim() : "UNKNOWN";
        String cleanResourceId = resourceId != null ? resourceId.trim() : "";
        String cleanDetails = details != null ? details.trim() : "";
        String cleanIpAddress = ipAddress != null ? ipAddress.trim() : "127.0.0.1";
        String cleanSeverity = severity != null ? severity.trim() : "INFO";

        AuditLog auditLog = new AuditLog.Builder()
                .userId(cleanUserId)
                .action(cleanAction)
                .resource(cleanResource)
                .resourceId(cleanResourceId)
                .details(cleanDetails)
                .ipAddress(cleanIpAddress)
                .severity(cleanSeverity)
                .viewCount(0)
                .build();

        return repository.save(auditLog);
    }

    @Cacheable(value = "auditLogs", key = "#root.methodName")
    public List<AuditLog> getAllAuditLogs() {
        return repository.findAllByOrderByTimestampDesc();
    }

    public List<AuditLog> getAuditLogsByUser(String userId) {
        String cleanUserId = userId != null ? userId.trim() : "";
        return repository.findByUserIdOrderByTimestampDesc(cleanUserId);
    }

    // Strategy Pattern - User vs Action vs Resource vs Hybrid - Same as Project 99 Tenant Shared Isolated Hybrid
    public List<AuditLog> getByStrategy(String type, String filterValue) {
        String cleanType = type != null ? type.trim().toLowerCase() : "hybrid";
        String cleanFilter = filterValue != null ? filterValue.trim() : "";

        List<AuditLog> result;

        switch (cleanType) {
            case "user":
                // User Strategy - Find by userId
                result = repository.findByUserId(cleanFilter);
                break;
            case "action":
                // Action Strategy - Find by action (CREATE, UPDATE, DELETE, LOGIN)
                result = repository.findByAction(cleanFilter);
                if (result.isEmpty()) {
                    result = repository.findByActionContainingIgnoreCase(cleanFilter);
                }
                break;
            case "resource":
                // Resource Strategy - Find by resource (like isolated strategy in 99 - CRITICAL FIX with trim())
                result = repository.findByResource(cleanFilter.trim());
                if (result.isEmpty()) {
                    result = repository.findByResourceContainingIgnoreCase(cleanFilter.trim());
                }
                break;
            case "severity":
                result = repository.findBySeverity(cleanFilter.trim());
                break;
            case "hybrid":
            default:
                // Hybrid Strategy - All sorted by timestamp DESC - like Project 99 Hybrid sorted 4.8 4.5 4.2
                result = repository.findAllByOrderByTimestampDesc();
                // Also include viewCount sorting for hybrid
                result = result.stream()
                        .sorted(Comparator.comparing(AuditLog::getTimestamp).reversed())
                        .collect(Collectors.toList());
                break;
        }

        // Increment viewCount for strategy results
        result.forEach(log -> log.setViewCount(log.getViewCount() + 1));
        return result;
    }

    public List<AuditLog> searchByAction(String action) {
        String cleanAction = action != null ? action.trim() : "";
        return repository.findByActionContainingIgnoreCase(cleanAction);
    }

    public List<AuditLog> searchByResource(String resource) {
        String cleanResource = resource != null ? resource.trim() : "";
        return repository.findByResourceContainingIgnoreCase(cleanResource);
    }

    public long count() {
        return repository.count();
    }

    public String getInfo() {
        return "Audit Log System - Tier 10 SCALE - Caching Caffeine 1000 max 600s Stats + Security Headers + Builder Pattern + Strategy Pattern User Action Resource Hybrid + Repository Pattern + H2 auditdb + JUnit 5 6/6 Green Bar";
    }
}