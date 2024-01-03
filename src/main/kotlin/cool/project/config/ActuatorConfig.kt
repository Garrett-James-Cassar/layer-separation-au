package cool.project.config

import org.springframework.boot.actuate.audit.AuditEventRepository
import org.springframework.boot.actuate.audit.InMemoryAuditEventRepository
import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class ActuatorConfig {
    @Bean
    fun auditEventRepository() : AuditEventRepository  = InMemoryAuditEventRepository()

    @Bean
    fun createTraceRepository() : HttpExchangeRepository = InMemoryHttpExchangeRepository()
}