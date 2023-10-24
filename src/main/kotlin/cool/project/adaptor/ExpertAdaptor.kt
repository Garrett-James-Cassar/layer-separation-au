package cool.project.adaptor


import cool.project.dto.entity.ExpertEntity
import cool.project.domain.ExpertDomain
import cool.project.domain.enums.Skill
import cool.project.domain.enums.SkillLevel
import org.springframework.stereotype.Component

@Component
class ExpertAdaptor {
    fun toDomain(expertEntity: ExpertEntity) =
        ExpertDomain(
            expertEntity.name,
            Skill.valueOf(expertEntity.skillQualifiedToAssess),
            expertEntity.verbalFeedbackMappingForGrownUps.map { (key, value) -> SkillLevel.valueOf(key) to value }.toMap(),
            expertEntity.verbalFeedbackMappingForChildren.map { (key, value) -> SkillLevel.valueOf(key) to value }.toMap()
        )

    fun toEntity(expertDomain: ExpertDomain) =
        ExpertEntity(expertDomain.name, expertDomain.skillQualifiedToAssess.name,
            expertDomain.verbalFeedbackMappingForGrownUps.map { (key, value) -> key.name to value }.toMap(),
            expertDomain.verbalFeedbackMappingForChildren.map { (key, value) -> key.name to value }.toMap())
}