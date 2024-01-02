package cool.project.error

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler(){

    @ExceptionHandler(ApiException::class)
    fun handle (exception: ApiException): ErrorResponse {
        val errorResponseBuilder = ErrorResponse
            .builder(exception, exception.httpStatus, exception.message)

        if(exception.titleMessageCode != null) errorResponseBuilder.titleMessageCode(exception.titleMessageCode)
        if(exception.properties.isNotEmpty()) exception.properties.forEach { errorResponseBuilder.property(it.key, it.value)}

        return errorResponseBuilder.build()
    }

}

abstract class ApiException(
    override val message : String,
    val httpStatus: HttpStatusCode,
    val titleMessageCode : String? = null,
    open val properties : Map<String, Any> = standardExceptionProperties
) : RuntimeException(message)

abstract class NotFoundException(override val message : String, notFoundProperties : Map<String, Any>) : ApiException(message, HttpStatus.NOT_FOUND, properties = standardExceptionProperties + notFoundProperties)

data class NoSkillException(val skill : String) : NotFoundException("Skill Not Found $skill", mapOf("statusCode" to "Skill-404"))
data class NoCandidateException(val candidate : String) : NotFoundException("Candidate Not Found $candidate", mapOf("statusCode" to "Candidate-404"))
data class NoExpertException(val expert : String) : NotFoundException("Expert Not Found $expert", mapOf("statusCode" to "Expert-404"))
data class NoExpertForSkillException(val skill : String) : NotFoundException("No Expert found to assess $skill", mapOf("statusCode" to "Expert-Skill-404"))

val standardExceptionProperties = mapOf("SourceSystem" to "AssessmentService")
