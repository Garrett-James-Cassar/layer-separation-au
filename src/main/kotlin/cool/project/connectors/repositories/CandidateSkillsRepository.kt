package cool.project.connectors.repositories

import cool.project.dto.entity.CandidateSkillsEntity
import cool.project.dto.entity.SkillEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CandidateSkillsRepository : JpaRepository<CandidateSkillsEntity, Long> {
    fun findBySkill(skill: SkillEntity): Optional<CandidateSkillsEntity>
}
