package cool.project.adaptor

import cool.project.domain.Candidate
import cool.project.domain.skillMap
import cool.project.dto.entity.CandidateEntity
import cool.project.dto.entity.CandidateSkillsEntity
import cool.project.dto.http.CandidateRequestResponse
import cool.project.error.NoSkillException
import java.util.UUID

fun CandidateRequestResponse.toDomain() = Candidate(name, age, skillsToAssesses.map { skillMap[it.lowercase()] ?: throw NoSkillException(it) })

fun CandidateEntity.toDomain() = Candidate(name, age, skills.map { it.toDomain() })

fun Candidate.toDto() = CandidateRequestResponse(name, age, skills.map { it.name() })

fun Candidate.toEntity(skillEntities: List<CandidateSkillsEntity> = emptyList()): CandidateEntity =
    CandidateEntity(name, age, skillEntities)

