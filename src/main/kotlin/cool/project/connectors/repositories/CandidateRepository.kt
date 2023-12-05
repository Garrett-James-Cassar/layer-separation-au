package cool.project.connectors.repositories

import cool.project.dto.entity.CandidateEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CandidateRepository : JpaRepository<CandidateEntity, String>
