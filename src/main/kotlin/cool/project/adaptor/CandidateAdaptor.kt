package cool.project.adaptor

import cool.project.domain.Candidate
import cool.project.dto.entity.CandidateEntity
import cool.project.dto.http.CandidateRequestResponse
import org.springframework.stereotype.Component
import java.lang.Math.random

@Component
class CandidateAdaptor {
    fun toDomain(candidateRequestResponse: CandidateRequestResponse) =
        Candidate(candidateRequestResponse.name, candidateRequestResponse.age,
            candidateRequestResponse.skills)

    fun toDomain(candidateEntity: CandidateEntity) =
        Candidate(candidateEntity.name, candidateEntity.age,
            candidateEntity.skills)

    fun toDto(candidate: Candidate) =
        CandidateRequestResponse(candidate.name, candidate.age, candidate.skills)

    fun toEntity(candidate: Candidate) =
        CandidateEntity(id = random().toLong(), candidate.name, candidate.age, candidate.skills, emptyMap())

}

