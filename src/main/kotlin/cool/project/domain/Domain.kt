package cool.project.domain

import cool.project.domain.enums.SubSkill
import cool.project.domain.enums.SubSkillLevel

data class Candidate(val name: String, val age : Int, val skills: List<Skill>, val subSkillLevels: Map<SubSkill, SubSkillLevel> = emptyMap()) {
    fun isAdult() = age > 18
}

data class ExpertDomain(
    val name: String,
    val skillQualifiedToAssess: Skill,
    val verbalFeedbackMappingForGrownUps : Map<SubSkillLevel, String>,
    val verbalFeedbackMappingForChildren : Map<SubSkillLevel, String>) {

    fun mapSkillLevelToVerbalFeedback(subSkillLevel: SubSkillLevel, isGrownup : Boolean) : String {
        return if (isGrownup) verbalFeedbackMappingForGrownUps[subSkillLevel]!!
        else verbalFeedbackMappingForChildren[subSkillLevel]!!
    }
}

data class Assessment(val skillAssessed: Skill, val subSkillLevel : SubSkillLevel)

