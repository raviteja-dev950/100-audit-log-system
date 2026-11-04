package com.auditlog.repository;

import com.auditlog.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    List<AuditLog> findByUserId(String userId);

    List<AuditLog> findByAction(String action);

    List<AuditLog> findByResource(String resource);

    List<AuditLog> findBySeverity(String severity);

    List<AuditLog> findByUserIdOrderByTimestampDesc(String userId);

    List<AuditLog> findByActionContainingIgnoreCase(String action);

    List<AuditLog> findByResourceContainingIgnoreCase(String resource);

    List<AuditLog> findAllByOrderByTimestampDesc();

    List<AuditLog> findByUserIdOrderByViewCountDesc(String userId);
}