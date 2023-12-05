package cool.project.dto.http

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import cool.project.domain.Coding
import cool.project.domain.Cooking
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Candidate Request Response")
data class CandidateRequestResponse
@JsonCreator // Specify a JSON creator (constructor) for deserialization
constructor(
    @JsonProperty("name")
    @Schema(description = "Candidate's full name", example = "John Doe", required = true)
    val name: String,

    @JsonProperty("age")
    @Schema(description = "Candidate's age", example = "25", required = true)
    val age: Int,

    @JsonProperty("skillsToAssesses")
    @Schema(
        description = "List of skills that a candidate would like assessed",
        example = """["Coding", "Cooking"]""",
        allowableValues = ["Coding", "Cooking", "DancingTheFlamenco"]
    )
    val skillsToAssesses: List<String>
)