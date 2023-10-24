package cool.project.adaptor

import cool.project.domain.Candidate
import cool.project.dto.entity.CandidateEntity
import cool.project.dto.http.CandidateRequestResponse
import cool.project.domain.enums.Skill
import cool.project.domain.enums.SkillLevel
import cool.project.extensions.SkillMap
import cool.project.extensions.toStringMap
import org.springframework.stereotype.Component
import java.lang.Math.random

@Component
class CandidateAdaptor {
    fun toDomain(candidateRequestResponse: CandidateRequestResponse) =
        Candidate(candidateRequestResponse.name, candidateRequestResponse.age,
            candidateRequestResponse.skills.map { (key, value) -> Skill.valueOrUnknown(key) to SkillLevel.valueOf(value) }.toMap())

    fun toDomain(candidateEntity: CandidateEntity) =
        Candidate(candidateEntity.name, candidateEntity.age,
            candidateEntity.skills.map { (key, value) -> Skill.valueOrUnknown(key) to SkillLevel.valueOf(value) }.toMap())

    fun toDto(candidate: Candidate) =
        CandidateRequestResponse(candidate.name, candidate.age, candidate.skills.toStringMap())

    fun toEntity(candidate: Candidate) =
        CandidateEntity(id = random().toLong(), candidate.name, candidate.age, candidate.skills.toStringMap())

}

