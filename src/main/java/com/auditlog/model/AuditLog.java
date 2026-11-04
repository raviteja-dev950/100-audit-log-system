package com.auditlog.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "action", nullable = false)
    private String action;

    @Column(name = "resource", nullable = false)
    private String resource;

    @Column(name = "resource_id")
    private String resourceId;

    @Column(name = "details", length = 1000)
    private String details;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "severity")
    private String severity;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "view_count")
    private int viewCount;

    public AuditLog() {
        this.timestamp = LocalDateTime.now();
        this.viewCount = 0;
    }

    // Builder Pattern - Same as Project 99
    public static class Builder {
        private AuditLog auditLog = new AuditLog();

        public Builder userId(String userId) {
            auditLog.userId = userId != null ? userId.trim() : null;
            return this;
        }

        public Builder action(String action) {
            auditLog.action = action != null ? action.trim() : null;
            return this;
        }

        public Builder resource(String resource) {
            auditLog.resource = resource != null ? resource.trim() : null;
            return this;
        }

        public Builder resourceId(String resourceId) {
            auditLog.resourceId = resourceId != null ? resourceId.trim() : null;
            return this;
        }

        public Builder details(String details) {
            auditLog.details = details != null ? details.trim() : null;
            return this;
        }

        public Builder ipAddress(String ipAddress) {
            auditLog.ipAddress = ipAddress != null ? ipAddress.trim() : null;
            return this;
        }

        public Builder severity(String severity) {
            auditLog.severity = severity != null ? severity.trim() : null;
            return this;
        }

        public Builder viewCount(int viewCount) {
            auditLog.viewCount = viewCount;
            return this;
        }

        public AuditLog build() {
            if (auditLog.timestamp == null) {
                auditLog.timestamp = LocalDateTime.now();
            }
            return auditLog;
        }
    }

    // Getters and Setters with trim() fix - CRITICAL like Project 99 isolationLevel trim() fix
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId != null ? userId.trim() : null; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action != null ? action.trim() : null; }

    public String getResource() { return resource; }
    public void setResource(String resource) { this.resource = resource != null ? resource.trim() : null; }

    public String getResourceId() { return resourceId; }
    public void setResourceId(String resourceId) { this.resourceId = resourceId != null ? resourceId.trim() : null; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details != null ? details.trim() : null; }

    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress != null ? ipAddress.trim() : null; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity != null ? severity.trim() : null; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public int getViewCount() { return viewCount; }
    public void setViewCount(int viewCount) { this.viewCount = viewCount; }

    // Alias for backward compat
    public String getDataValue() { return details; }
}