package cool.project.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Instant

@Configuration
public class OpenApiConfig {

    @Bean
    fun customOpenApi() = OpenAPI().info(Info().title("Assessment API ${Instant.now()}").version("V0"));

}