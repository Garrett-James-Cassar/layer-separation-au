package cool.project.dto.entity

import cool.project.domain.Skill

data class ExpertEntity(
    val name: String,
    val skillQualifiedToAssess: Skill,
    val verbalFeedbackMappingForGrownUps : Map<String, String>,
    val verbalFeedbackMappingForChildren : Map<String, String>)