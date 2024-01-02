package cool.project.error

import com.fasterxml.jackson.annotation.JsonIgnore
import cool.project.domain.Skill
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.HttpStatus

data class NoExpert(@JsonIgnore private val skill : Skill) {
    @Schema(example = "No Expert found qualified to assess the skill: 'Singing'!")
    val message = "No Expert found qualified to assess the skill: '$skill'!"
    @Schema(example = "404 NOT_FOUND")
    val httpStatus = HttpStatus.NOT_FOUND
}
data class NoCandidate(@JsonIgnore private val candidate : String) {
    @Schema(example = "No candidate found 'John Doe'!")
    val message = "No Candidate found $candidate!"
    @Schema(example = "404 NOT_FOUND")
    val httpStatus = HttpStatus.NOT_FOUND
}
data class NoSkill(@JsonIgnore private val skill : String){
    @Schema(example = "No skill found 'Singing'!")
    val message = "No Skill found $skill!"
    @Schema(example = "404 NOT_FOUND")
    val httpStatus = HttpStatus.NOT_FOUND
}