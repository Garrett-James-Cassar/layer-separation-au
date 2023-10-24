package cool.project.service

import cool.project.adaptor.CandidateAdaptor
import cool.project.connectors.repositories.CandidateRepository
import cool.project.domain.Candidate
import org.springframework.stereotype.Service

@Service
class CanidateService(val candidateRepository: CandidateRepository, val candidateAdaptor: CandidateAdaptor) {

    fun createPerson(candidate: Candidate): Candidate {
        val entity = candidateAdaptor.toEntity(candidate)
        val savedEntity = candidateRepository.save(entity)
        return candidateAdaptor.toDomain(savedEntity)
    }

    fun getPerson(id: Long): Candidate {
        val entity = candidateRepository.findById(id).orElse(null)
            ?: throw IllegalArgumentException("Person with ID $id not found")
        return candidateAdaptor.toDomain(entity)
    }

    fun getAllPersons(): List<Candidate> {
        return candidateRepository.findAll().map { candidateAdaptor.toDomain(it) }
    }

    fun deletePerson(id: Long) {
        if (!candidateRepository.existsById(id)) {
            throw IllegalArgumentException("Person with ID $id not found")
        }
        candidateRepository.deleteById(id)
    }

}