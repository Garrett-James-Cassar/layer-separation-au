package cool.project.dto.http

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Assessment Response")
data class AssessmentResponse(
    val assessments : List<ExpertAssessment>
)


data class ExpertAssessment (

    @Schema(
        description = "Expert who assessed the skill",
        example = "Shmordon",
        required = true)
    val expertName: String,

    @Schema(description = "Skill Assessed", example = "Cooking", allowableValues = ["Coding", "cooking", "dancingTheFlamenco"], required = true)
    val skillAssessed: String,

    @Schema(
        description = """ Aggregated score of all the subskill scores """",
        example = "2.4"
    )
    val overallRating: Double,

    @Schema(
        description = """ Verbal Feedback from the expert based on the candidate overall ability"""",
        example = "Call that a Carbonara? I think your a Carbonassh***"
    )
    val verbalFeedback: String,

    @Schema(
        description = """ Verbal Feedback from the expert based on the candidate overall ability"""",
        example = "Call that a Carbonara? I think your a Carbonassh***"
    )
    val subSkillRatings: List<SubSkillAssessment>
)

data class SubSkillAssessment (
    @Schema(description = "Subskill Assessed", example = "Knife Skills", required = true)
    val subSkillAssessed: String,

    @Schema(
        description = """ Subskill Score """",
        example = "2.8"
    )
    val subSkillRating: Double,

    )



