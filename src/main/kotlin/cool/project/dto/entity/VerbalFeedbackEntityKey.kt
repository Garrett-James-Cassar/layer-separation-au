package cool.project.dto.entity

import cool.project.domain.enums.SubSkillLevel
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class VerbalFeedbackEntityKey (
    val skillLevel: SubSkillLevel,
    val isAdult : Boolean,
    val expertName: String
) : Serializable


