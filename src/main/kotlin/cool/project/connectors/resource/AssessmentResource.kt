package cool.project.connectors.resource

import cool.project.dto.http.AssessmentResponse
import cool.project.error.*
import cool.project.service.AssessmentService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.links.Link
import io.swagger.v3.oas.annotations.links.LinkParameter
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import mu.KotlinLogging

@RestController
@RequestMapping("/assess")
@Tag(
    name = "Assessment API",
    description = """
        Used to call upon an expert to assess these skills of a candidate. 
        This is done by give a rating to each subskill (Random number generator at the moment).
        Averaging those subskill ratings will provide an overall skill level. 
        Based on the overall skill level the expert will provide verbal feedback and make recommendations 
        to the course recommendation service using the ratings of the subskill levels.  
        """
)
class AssessmentResource(val assessmentService: AssessmentService) {
    @Operation(summary = "Assess a candidates skills for skills registered in the  candidate  database")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Candidate Successfully Assessed",
                content = [(Content(mediaType = "application/json", array = (ArraySchema(schema = Schema(implementation = AssessmentResponse::class)))))]),

            ApiResponse(responseCode = "404",
                description = "Something has not been found.",
                content = [Content(mediaType = "application/json", array = (ArraySchema(schema = Schema(implementation = ApiException::class))))]),
        ]
    )
    @GetMapping("/{candidateName}/")
    fun getAssessment(@PathVariable candidateName: String): ResponseEntity<AssessmentResponse> {
        val expertFeedback = assessmentService.assess(candidateName)
        return ResponseEntity.ok(AssessmentResponse(expertFeedback))
    }

}





//            ApiResponse(responseCode = "404",
//                description = "An expert to serve that skill has not been found.",
//                content = [Content(mediaType = "application/json", array = (ArraySchema(schema = Schema(implementation = ApiException::class))))],
//                links=[Link(description = "google.com", name = "google.com", operationRef = "google", operationId = "google", parameters = [LinkParameter(name = "goog", expression = "googex" )])]
//
//            ),
//
//            ApiResponse(responseCode = "404",
//                description = "Skill specified has not been found.",
//                content = [Content(mediaType = "application/json", array = (ArraySchema(schema = Schema(oneOf = [ApiException::class], implementation = ApiException::class))))]),
