package com.auditlog.controller;

import com.auditlog.model.AuditLog;
import com.auditlog.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
public class AuditLogController {

    @Autowired
    private AuditLogService service;

    // POST /api/audit?userId=...&action=...&resource=...&resourceId=...&details=...&ipAddress=...&severity=...
    @PostMapping
    public ResponseEntity<AuditLog> createAuditLog(
            @RequestParam String userId,
            @RequestParam String action,
            @RequestParam String resource,
            @RequestParam(required = false) String resourceId,
            @RequestParam(required = false) String details,
            @RequestParam(required = false) String ipAddress,
            @RequestParam(required = false) String severity) {

        // trim() fix - CRITICAL like Project 99
        String cleanUserId = userId != null ? userId.trim() : "";
        String cleanAction = action != null ? action.trim() : "";
        String cleanResource = resource != null ? resource.trim() : "";

        AuditLog auditLog = service.createAuditLog(cleanUserId, cleanAction, cleanResource, resourceId, details, ipAddress, severity);
        return ResponseEntity.ok(auditLog);
    }

    // GET /api/audit - List All
    @GetMapping
    public ResponseEntity<List<AuditLog>> getAllAuditLogs() {
        return ResponseEntity.ok(service.getAllAuditLogs());
    }

    // GET /api/audit/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AuditLog>> getByUser(@PathVariable String userId) {
        String cleanUserId = userId != null ? userId.trim() : "";
        return ResponseEntity.ok(service.getAuditLogsByUser(cleanUserId));
    }

    // GET /api/audit/action/{action}
    @GetMapping("/action/{action}")
    public ResponseEntity<List<AuditLog>> getByAction(@PathVariable String action) {
        String cleanAction = action != null ? action.trim() : "";
        return ResponseEntity.ok(service.getByStrategy("action", cleanAction));
    }

    // GET /api/audit/resource/{resource}
    @GetMapping("/resource/{resource}")
    public ResponseEntity<List<AuditLog>> getByResource(@PathVariable String resource) {
        String cleanResource = resource != null ? resource.trim() : "";
        return ResponseEntity.ok(service.getByStrategy("resource", cleanResource));
    }

    // GET /api/audit/strategy?type=user|action|resource|hybrid&filter=
    @GetMapping("/strategy")
    public ResponseEntity<List<AuditLog>> getByStrategy(
            @RequestParam String type,
            @RequestParam(required = false) String filter) {

        String cleanType = type != null ? type.trim() : "hybrid";
        String cleanFilter = filter != null ? filter.trim() : "";

        // For hybrid, filter can be empty - return all sorted
        if (cleanType.equalsIgnoreCase("hybrid") && cleanFilter.isEmpty()) {
            return ResponseEntity.ok(service.getByStrategy("hybrid", ""));
        }

        // For other types, use filter
        String filterToUse = cleanFilter.isEmpty() ? "user1" : cleanFilter;
        if (cleanType.equalsIgnoreCase("hybrid")) {
            filterToUse = "";
        }

        return ResponseEntity.ok(service.getByStrategy(cleanType, filterToUse));
    }

    // GET /api/audit/search?action=CREATE
    @GetMapping("/search")
    public ResponseEntity<List<AuditLog>> searchByAction(@RequestParam String action) {
        String cleanAction = action != null ? action.trim() : "";
        return ResponseEntity.ok(service.searchByAction(cleanAction));
    }

    // GET /api/audit/search/resource?resource=USER
    @GetMapping("/search/resource")
    public ResponseEntity<List<AuditLog>> searchByResource(@RequestParam String resource) {
        String cleanResource = resource != null ? resource.trim() : "";
        return ResponseEntity.ok(service.searchByResource(cleanResource));
    }

    // GET /api/audit/count
    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(service.count());
    }

    // GET /api/audit/info
    @GetMapping("/info")
    public ResponseEntity<String> info() {
        return ResponseEntity.ok(service.getInfo());
    }
}