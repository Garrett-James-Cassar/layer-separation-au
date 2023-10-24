package cool.project.connectors.repositories

import cool.project.dto.entity.CandidateEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CandidateRepository : CrudRepository<CandidateEntity, Long>
