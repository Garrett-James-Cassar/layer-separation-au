package cool.project.connectors.resource

import cool.project.adaptor.toDomain
import cool.project.adaptor.toDto
import cool.project.dto.http.CandidateRequestResponse
import cool.project.service.CandidateService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/candidate")
@Tag(
    name = "Candidate CRUD API",
    description = """Create, update and delete candidates the would like to be assessed by an expert on particular skills. """
)
class CandidateController(val candidateService: CandidateService) {
    @Operation(summary = "Create a candidate")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Candidate created successfully", content = [
                    io.swagger.v3.oas.annotations.media.Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = CandidateRequestResponse::class)
                    )
                ]
            )
//            ,ApiResponse(
//                responseCode = "5xx", description = "Server error defined by the error response", content = [
//                    io.swagger.v3.oas.annotations.media.Content(
//                        mediaType = "application/json",
//                        schema = Schema(implementation = ApiException::class)
//                    )
//                ]
//            )
        ]
    )
    @PostMapping
    fun createCandidate(@RequestBody candidateRequestResponse: CandidateRequestResponse): ResponseEntity<CandidateRequestResponse> {
        val savedCandidate = candidateService.createCandidate(candidateRequestResponse.toDomain())
        return ResponseEntity.ok(savedCandidate.toDto())
    }

    @Operation(summary = "Get a candidate by ID")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Successful response", content = [
                    io.swagger.v3.oas.annotations.media.Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = CandidateRequestResponse::class)
                    )
                ]
            )
        ]
    )
    @GetMapping("/{id}")
    fun getCandidate(@PathVariable id: String): ResponseEntity<CandidateRequestResponse> {
        val candidate = candidateService.getCandidate(id)
        return ResponseEntity.ok(candidate.toDto())
    }

    @Operation(summary = "Get all candidates")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Successful response", content = [
                    io.swagger.v3.oas.annotations.media.Content(
                        mediaType = "application/json",
                        array = ArraySchema(schema = Schema(implementation = CandidateRequestResponse::class))
                    )
                ]
            )
        ]
    )
    @GetMapping
    fun getAllCandidates(): ResponseEntity<List<CandidateRequestResponse>> {
        val candidates = candidateService.getAllCandidates()
        return ResponseEntity.ok(candidates.map { it.toDto() })
    }

    @Operation(summary = "Delete a candidate by ID")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "Candidate deleted successfully",
                    content = [io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")]
            )
        ]
    )
    @DeleteMapping("/{id}")
    fun deleteCandidate(@PathVariable id: String): ResponseEntity<Void> {
        candidateService.deleteCandidate(id)
        return ResponseEntity.noContent().build()
    }
}
