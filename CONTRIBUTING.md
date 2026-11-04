# Contributing to Project 100 - Audit Log System - Tier 10 SCALE

Thank you for your interest in contributing to Project 100 - Audit Log System - Tier 10 SCALE - Part of Java 102 Projects Challenge - 100/102 98% - Caffeine 1000 max 600s Stats + Security Headers + Builder Pattern + Strategy Pattern + Repository Pattern + JUnit 6/6 Green Bar + Docker Multi-Stage + Render Live 2m50s - Audit Pattern User Action Resource Hybrid + trim() fix FIXED [] empty to 1 item - Same as 99 Multi-tenant SaaS Live 2m50s!

## How to Contribute

1. Fork the repository
2. Create a new branch: `git checkout -b feature/audit-enhancement`
3. Make your changes
4. Run tests: `mvn test` - Expected Runs: 6/6 Errors: 0 Failures: 0 Green Bar 5.328s
5. Commit: `git commit -m "Add audit enhancement - User Action Resource Hybrid"`
6. Push: `git push origin feature/audit-enhancement`
7. Submit Pull Request

## Tier 10 Standards - Must Follow Same as 93-99

- **Java 17** - eclipse-temurin:17-jdk-alpine - Local Java 21.0.10
- **Spring Boot 3.2.5** - Banner v3.2.5
- **Caching** - CaffeineCacheManager maximumSize=1000 expireAfterAccess=600s recordStats() - Redis pattern ready
- **Security** - SecurityHeadersConfig + SecurityHeadersFilter - X-Content-Type-Options nosniff + X-Frame-Options DENY + X-XSS-Protection
- **Builder Pattern** - AuditLog.Builder userId() action() resource() resourceId() details() ipAddress() severity() viewCount() build() Fluent API - Same as 99 TenantData.Builder
- **Strategy Pattern** - User vs Action vs Resource vs Hybrid - User-Strategy count 3 + Action-Strategy count 1 + Resource-Strategy count 1 FIXED from [] empty -> trim() fix + Hybrid-Strategy count 4 sorted timestamp DESC - Same as 99 Tenant Shared Isolated Hybrid sorted 4.8 4.5 4.2
- **Repository Pattern** - JpaRepository + findAllByOrderByTimestampDesc + findByUserIdOrderByTimestampDesc + findByActionContainingIgnoreCase + findByResourceContainingIgnoreCase + idx_user + idx_action
- **H2 Database** - jdbc:h2:mem:auditdb + audit_logs table + viewCount + idx_user + idx_action + H2Console
- **JUnit 5 + Mockito** - 6 tests Green Bar 5.328s - testCreateAuditLog + testGetAllAuditLogs + testGetByStrategyUser + testGetByStrategyAction + testGetByStrategyResource + testGetByStrategyHybrid - Mocked Repo when/verify - Same as 99 6/6 5.798s
- **Docker Multi-Stage** - maven:3.9-eclipse-temurin-17 AS build + eclipse-temurin:17-jdk-alpine + mkdir -p data + COPY --from=build
- **Render Live** - Docker + PORT 10000 + Deploy succeeded Live 2m50s - Same as 99 Live 2m50s

## Bug Fixes - Must Document Same Style as 99

- **Syntax error value** - H2 reserved keyword value -> data_value column fix + getValue/setValue alias - Same as 97 98 99
- **Isolated [] bug** - Postman Enter adds \n newline - isolationLevel="isolated\n" - Filter fails - Fixed by adding trim() in create + getByStrategy + d.getIsolationLevel().trim() - Same as 99 isolated trim() fix - Now Resource FIXED from [] to 1 item - CRITICAL
- **META-INF bug** - Eclipse creates src/main/resources/META-INF breaking Maven - Fixed by deleting META-INF + target + Maven Update Project Force Update - Same as 93/94/95/96/97/98/99
- **openjdk not found** - docker.io/library/openjdk:17-jdk-slim not found - Fixed by eclipse-temurin:17-jdk-alpine - Same as 94/95/96/97/98/99
- **COPY target error** - Docker build doesn't run mvn package - Fixed by Multi-Stage Dockerfile - Deploy succeeded Live 2m50s - Same as 94/95/96/97/98/99
- **UnnecessaryStubbingException** - Stubbing both findAllByOrderByTimestampDesc and findAll - Fixed by removing when(repository.findAll()) - Same as 99

## Commit Message Format

- feat: Add new audit feature
- fix: Fix resource [] bug with trim()
- docs: Update README with 16 demos
- test: Add JUnit test for hybrid strategy
- chore: Update Dockerfile to eclipse-temurin

## Pull Request Checklist

- [ ] Code follows Tier 10 standards - Caching + Security + Builder + Strategy + Repository
- [ ] Tests pass - Runs: 6/6 Errors: 0 Failures: 0 Green Bar 5.328s
- [ ] README updated with screenshots - 16 demos - Home + Health + Empty + POST 4 + List 4 + User 3 + Action 1 + Resource 1 FIXED + Hybrid 4 sorted + Count 4 + Info + Search CREATE + Console 13.813s + JUnit 6/6 5.328s + Render Live 2m50s
- [ ] Bug fixes documented - trim() fix + data_value fix + META-INF + openjdk + COPY target
- [ ] Docker builds successfully - Multi-Stage - maven:3.9-eclipse-temurin-17 + eclipse-temurin:17-jdk-alpine
- [ ] Render deploy succeeds - Deploy succeeded Live 2m50s - Same as 99 Live 2m50s
- [ ] No META-INF folder - Deleted
- [ ] application.properties verified - server.port=${PORT:8102} + jdbc:h2:mem:auditdb + cache spec maximumSize=1000,expireAfterAccess=600s,recordStats

Thank you for contributing to Tier 10 SCALE 100/102 98% - Only 2 left to 100% - 101 SaaS Idea + 102 Capstone OSS Tool!
