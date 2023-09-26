package cool.project.utility

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Beans {

    @Bean
    fun objectMapper() = ObjectMapper()
}