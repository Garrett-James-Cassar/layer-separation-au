package cool.project.domain

import cool.project.domain.enums.SubSkill
import cool.project.domain.enums.SubSkillLevel

data class Candidate(val name: String, val age : Int, val skills: List<Skill>, val subSkillLevels: Map<SubSkill, SubSkillLevel> = emptyMap()) {
    fun isAdult() = age > 18
}

data class Expert(
    val name: String,
    val skillQualifiedToAssess: Skill
)


data class Assessment(val skillAssessed: Skill, val subSkillLevel : SubSkillLevel)

