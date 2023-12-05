package cool.project.adaptor


import cool.project.domain.Expert
import cool.project.domain.skillMap
import cool.project.dto.entity.ExpertEntity
import cool.project.dto.http.ExpertRequestResponse

fun ExpertEntity.toDomain() : Expert =
    Expert(name, skill.toDomain())

fun Expert.toEntity() : ExpertEntity =
    ExpertEntity(name, skillQualifiedToAssess.toEntity())

fun ExpertRequestResponse.toDomain() : Expert =
    Expert(name, skillMap[skillQualifiedToAssess.lowercase()]!!)

fun Expert.toDto() : ExpertRequestResponse =
    ExpertRequestResponse(name, skillQualifiedToAssess.name())