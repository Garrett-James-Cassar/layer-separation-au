package cool.project.adaptor


import cool.project.domain.ExpertDomain
import cool.project.domain.enums.SubSkillLevel
import cool.project.dto.entity.ExpertEntity
import org.springframework.stereotype.Component

@Component
class ExpertAdaptor {
    fun toDomain(expertEntity: ExpertEntity) =
        ExpertDomain(
            expertEntity.name,
            expertEntity.skillQualifiedToAssess,
            expertEntity.verbalFeedbackMappingForGrownUps.map { (key, value) -> SubSkillLevel.valueOf(key) to value }.toMap(),
            expertEntity.verbalFeedbackMappingForChildren.map { (key, value) -> SubSkillLevel.valueOf(key) to value }.toMap()
        )

    fun toEntity(expertDomain: ExpertDomain) =
        ExpertEntity(expertDomain.name, expertDomain.skillQualifiedToAssess,
            expertDomain.verbalFeedbackMappingForGrownUps.map { (key, value) -> key.name to value }.toMap(),
            expertDomain.verbalFeedbackMappingForChildren.map { (key, value) -> key.name to value }.toMap())
}