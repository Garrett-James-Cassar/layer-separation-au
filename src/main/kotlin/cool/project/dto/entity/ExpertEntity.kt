package cool.project.dto.entity

data class ExpertEntity(
    val name: String,
    val skillQualifiedToAssess: String,
    val verbalFeedbackMappingForGrownUps : Map<String, String>,
    val verbalFeedbackMappingForChildren : Map<String, String>)