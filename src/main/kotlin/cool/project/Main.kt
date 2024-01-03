package cool.project

import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = ["cool.project.connectors.repositories"])
class MyApplication

fun main(args: Array<String>) {
    SpringApplication.run(MyApplication::class.java, *args)
}