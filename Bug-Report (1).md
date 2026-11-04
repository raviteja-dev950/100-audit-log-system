---
name: Bug report
about: Create a report to help us improve Project 100 Audit Log System Tier 10 SCALE
title: '[BUG] Resource strategy returns [] empty - trim() fix needed'
labels: bug, Tier 10, System Design, Audit Log, resource \n trim fix
assignees: ''
---

**Describe the bug**
GET /api/audit/strategy?type=resource&filter=USER returns [] empty but DB has USER resource. Similar to Project 99 isolated [] bug - Postman Enter adds \n newline.

**To Reproduce**
Steps to reproduce the behavior:
1. POST /api/audit?userId=user1&action=CREATE&resource=USER&resourceId=res1&details=User created&ipAddress=127.0.0.1&severity=INFO - 200 OK id 1 CLEAN
2. GET /api/audit/strategy?type=resource&filter=USER
3. See [] empty - Should return 1 item id 1 CREATE USER - FIXED from [] empty - CRITICAL

**Expected behavior**
Should return 1 item id 1 CREATE USER - Resource-Strategy count 1 FIXED from [] empty - trim() fix - Same as 99 isolated fix.

**Screenshots**
If applicable, add screenshots to help explain your problem:
- demo11 Resource FIXED 1 item - [{"id":1,"userId":"user1","action":"CREATE","resource":"USER","resourceId":"res1","details":"User created","ipAddress":"127.0.0.1","severity":"INFO","timestamp":"2026-11-04T22:34:39.444317","viewCount":1,"dataValue":"User created"}] - FIXED FROM [] EMPTY - CRITICAL PROOF trim() fix
- demo13 Count 4
- demo14 Info Audit Log System Tier 10 SCALE
- demo15 Search CREATE 1 item

**Environment:**
 - Java 17 - eclipse-temurin:17-jdk-alpine
 - Spring Boot 3.2.5
 - Caching Caffeine 1000 max 600s Stats recordStats enabled
 - Security Headers nosniff DENY XSS
 - Builder Pattern AuditLog.Builder
 - Strategy Pattern User vs Action vs Resource vs Hybrid
 - H2 auditdb
 - Port 8102 local, 10000 Render
 - JUnit 6/6 Green Bar 5.328s

**Additional context**
Fix: Add trim() in createAuditLog() cleanResource=resource.trim() + getByStrategy() cleanType=type.trim() + cleanFilter=filter.trim() + d.getResource().trim() - BUILD SUCCESS 13.813 sec - Now demo11 returns 1 item CLEAN not [] - CRITICAL FIX - Same as 99 isolated trim() fix count 0->1.
