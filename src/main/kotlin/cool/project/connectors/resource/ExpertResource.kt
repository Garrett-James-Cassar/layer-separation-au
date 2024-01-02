package cool.project.connectors.resource

import cool.project.adaptor.toDomain
import cool.project.adaptor.toDto
import cool.project.dto.http.ExpertRequestResponse
import cool.project.service.ExpertService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/expert")
@Tag(
    name = "expert CRUD API",
    description = """Register Experts who can assess particular skills when requested for experts through the assessment API. """
)
class ExpertController(val expertService: ExpertService) {
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
            )
        ]
    )
    @GetMapping("/{id}")
    fun getExpert(@PathVariable id: String): ResponseEntity<ExpertRequestResponse> {
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
            )
        ]
    )
    @DeleteMapping("/{id}")
    fun deleteCandidate(@PathVariable id: String): ResponseEntity<Void> {
        expertService.unregisterExpert(id)
        return ResponseEntity.noContent().build()
    }
}
