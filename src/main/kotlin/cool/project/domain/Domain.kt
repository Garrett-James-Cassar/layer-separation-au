package cool.project.domain

import cool.project.domain.enums.Skill
import cool.project.domain.enums.SkillLevel

data class Candidate(val name: String, val age : Int,  val skills: Map<Skill, SkillLevel>) {
    fun isAdult() = age > 18
}

data class ExpertDomain(
    val name: String,
    val skillQualifiedToAssess: Skill,
    val verbalFeedbackMappingForGrownUps : Map<SkillLevel, String>,
    val verbalFeedbackMappingForChildren : Map<SkillLevel, String>) {

    fun mapSkillLevelToVerbalFeedback(skillLevel: SkillLevel, isGrownup : Boolean) : String {
        return if (isGrownup) verbalFeedbackMappingForGrownUps[skillLevel]!!
        else verbalFeedbackMappingForChildren[skillLevel]!!
    }
}

data class Assessment(val skillAssessed: Skill, val skillLevel : SkillLevel)

