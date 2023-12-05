package cool.project.adaptor


import cool.project.domain.ExpertDomain
import cool.project.domain.enums.SubSkillLevel
import cool.project.dto.entity.ExpertEntity
import org.springframework.stereotype.Component

fun ExpertEntity.toDomain() : ExpertDomain =
    ExpertDomain(name, skill.toDomain())

//fun ExpertDomain.toEntity() : ExpertEntity =
//    ExpertEntity(name, skillQualifiedToAssess.toEntity())