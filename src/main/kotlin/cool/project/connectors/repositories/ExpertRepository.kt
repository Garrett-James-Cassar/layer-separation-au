package cool.project.connectors.repositories

import cool.project.dto.entity.ExpertEntity
import cool.project.dto.entity.CandidateSkillsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ExpertRepository : JpaRepository<ExpertEntity, Long> {
    fun findBySkill(@Param("skill") skill: CandidateSkillsEntity): Optional<ExpertEntity>
}
