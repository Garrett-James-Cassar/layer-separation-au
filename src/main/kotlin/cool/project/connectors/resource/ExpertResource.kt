package cool.project.connectors.resource

import cool.project.MyApplication
import cool.project.adaptor.toDomain
import cool.project.adaptor.toDto
import cool.project.dto.http.ExpertRequestResponse
import cool.project.error.ApiException
import cool.project.service.ExpertService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/expert")
@Tag(
    name = "expert CRUD API",
    description = """Register Experts who can assess particular skills when requested for experts through the assessment API. """
)
class ExpertController(val expertService: ExpertService) {
    private val logger: Log = LogFactory.getLog(ExpertController::class.java)

    @Operation(summary = "Register an expert")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Expert Sucessfully registered4", content = [
                    io.swagger.v3.oas.annotations.media.Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = ExpertRequestResponse::class)
                    )
                ]
            )
        ]
    )
    @PostMapping
    fun createCandidate(@RequestBody expertRequestResponse: ExpertRequestResponse): ResponseEntity<ExpertRequestResponse> {
        logger.info("anything")
        val registeredExpert = expertService.registerExpert(expertRequestResponse.toDomain())
        return ResponseEntity.ok(registeredExpert.toDto())
    }

    @Operation(summary = "Get a candidate by ID")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Successful response", content = [
                    io.swagger.v3.oas.annotations.media.Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = ExpertRequestResponse::class)
                    )
                ]
            ),
            ApiResponse(
                responseCode = "404", description = "Something Not Found", content = [
                    io.swagger.v3.oas.annotations.media.Content(
                        mediaType = "application/json",
                        array = ArraySchema(schema = Schema(implementation = ApiException::class))
                    )
                ]
            )
        ]
    )
    @GetMapping("/{id}")
    fun getExpert(@PathVariable id: String): ResponseEntity<ExpertRequestResponse> {
        logger.info("anything")
        val expert = expertService.getExpert(id)
        return ResponseEntity.ok(expert.toDto())
    }

    @Operation(summary = "Get all experts")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Successful response", content = [
                    io.swagger.v3.oas.annotations.media.Content(
                        mediaType = "application/json",
                        array = ArraySchema(schema = Schema(implementation = ExpertRequestResponse::class))
                    )
                ]
            )
        ]
    )
    @GetMapping
    fun getAllCandidates(): ResponseEntity<List<ExpertRequestResponse>> {
        logger.info("anything")
        val experts = expertService.getAllExperts()
        return ResponseEntity.ok(experts.map { it.toDto() })
    }

    @Operation(summary = "Delete a candidate by ID")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "Candidate deleted successfully",
                content = [
                    io.swagger.v3.oas.annotations.media.Content(
                    mediaType = "application/json"
                    )
                ]
            ),
            ApiResponse(
                responseCode = "404", description = "Something Not Found", content = [
                    io.swagger.v3.oas.annotations.media.Content(
                        mediaType = "application/json",
                        array = ArraySchema(schema = Schema(implementation = ApiException::class))
                    )
                ]
            )
        ]
    )
    @DeleteMapping("/{id}")
    fun deleteCandidate(@PathVariable id: String): ResponseEntity<Void> {
        logger.info("anything")
        expertService.unregisterExpert(id)
        return ResponseEntity.noContent().build()
    }
}
