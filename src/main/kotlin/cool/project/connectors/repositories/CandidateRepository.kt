package cool.project.connectors.repositories

import cool.project.dto.entity.CandidateEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CandidateRepository : JpaRepository<CandidateEntity, String> {
    @Query("select c from CandidateEntity c where c.name = ?1")
    fun findByName(name: String): CandidateEntity
}
