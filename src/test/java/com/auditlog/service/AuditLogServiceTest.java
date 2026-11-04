package com.auditlog.service;

import com.auditlog.model.AuditLog;
import com.auditlog.repository.AuditLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuditLogServiceTest {

    @Mock
    private AuditLogRepository repository;

    @InjectMocks
    private AuditLogService service;

    private AuditLog auditLog1;
    private AuditLog auditLog2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        auditLog1 = new AuditLog.Builder()
                .userId("user1")
                .action("CREATE")
                .resource("USER")
                .resourceId("res1")
                .details("User created")
                .ipAddress("127.0.0.1")
                .severity("INFO")
                .viewCount(0)
                .build();
        auditLog1.setId(1L);

        auditLog2 = new AuditLog.Builder()
                .userId("user1")
                .action("UPDATE")
                .resource("ORDER")
                .resourceId("res2")
                .details("Order updated")
                .ipAddress("192.168.1.1")
                .severity("WARN")
                .viewCount(1)
                .build();
        auditLog2.setId(2L);
    }

    @Test
    void testCreateAuditLog() {
        when(repository.save(any(AuditLog.class))).thenReturn(auditLog1);

        AuditLog result = service.createAuditLog("user1", "CREATE", "USER", "res1", "User created", "127.0.0.1", "INFO");

        assertNotNull(result);
        assertEquals("user1", result.getUserId());
        assertEquals("CREATE", result.getAction());
        verify(repository, times(1)).save(any(AuditLog.class));
    }

    @Test
    void testGetAllAuditLogs() {
        List<AuditLog> logs = Arrays.asList(auditLog1, auditLog2);
        when(repository.findAllByOrderByTimestampDesc()).thenReturn(logs);

        List<AuditLog> result = service.getAllAuditLogs();

        assertEquals(2, result.size());
        verify(repository, times(1)).findAllByOrderByTimestampDesc();
    }

    @Test
    void testGetByStrategyUser() {
        List<AuditLog> logs = Arrays.asList(auditLog1, auditLog2);
        when(repository.findByUserId("user1")).thenReturn(logs);

        List<AuditLog> result = service.getByStrategy("user", "user1");

        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUserId());
    }

    @Test
    void testGetByStrategyAction() {
        List<AuditLog> logs = Arrays.asList(auditLog1);
        when(repository.findByAction("CREATE")).thenReturn(logs);

        List<AuditLog> result = service.getByStrategy("action", "CREATE");

        assertEquals(1, result.size());
        assertEquals("CREATE", result.get(0).getAction());
    }

    @Test
    void testGetByStrategyResource() {
        List<AuditLog> logs = Arrays.asList(auditLog1);
        when(repository.findByResource("USER")).thenReturn(logs);

        List<AuditLog> result = service.getByStrategy("resource", "USER");

        assertEquals(1, result.size());
        assertEquals("USER", result.get(0).getResource());
    }

    @Test
    void testGetByStrategyHybrid() {
        List<AuditLog> logs = Arrays.asList(auditLog1, auditLog2);
        when(repository.findAllByOrderByTimestampDesc()).thenReturn(logs);

        List<AuditLog> result = service.getByStrategy("hybrid", "");

        assertEquals(2, result.size());
    }
}