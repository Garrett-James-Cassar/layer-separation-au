package cool.project.service

import cool.project.adaptor.toDomain
import cool.project.adaptor.toEntity
import cool.project.connectors.repositories.CandidateRepository
import cool.project.connectors.repositories.CandidateSkillsRepository
import cool.project.connectors.repositories.SkillsRepository
import cool.project.domain.Candidate
import org.springframework.stereotype.Service

@Service
class CandidateService(
    val candidateRepository: CandidateRepository,
    val candidateSkillsRepository: CandidateSkillsRepository,
    val skillsRepository: SkillsRepository,
) {

    fun createCandidate(candidate: Candidate) : Candidate {
        skillsRepository.saveAll(candidate.skills.map { it.toEntity() })
        val candidateEntity = candidateRepository.save(candidate.toEntity())
        candidateSkillsRepository.saveAll(candidate.skills.map { it.toEntity(candidate = candidateEntity) })
        return candidateEntity.toDomain()
    }


    fun getCandidate(id: String): Candidate {
        val entity = candidateRepository.findById(id).orElse(null)
            ?: throw IllegalArgumentException("Person with ID $id not found")
        return entity.toDomain()
    }

    fun getAllCandidates() = candidateRepository.findAll().map { it.toDomain() }

    fun deleteCandidate(id: String) {
        if (!candidateRepository.existsById(id)) {
            throw IllegalArgumentException("Person with ID $id not found")
        }
        candidateRepository.deleteById(id)
    }

}