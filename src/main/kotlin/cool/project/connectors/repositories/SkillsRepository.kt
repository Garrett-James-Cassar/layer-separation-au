package cool.project.connectors.repositories

import cool.project.dto.entity.SkillEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SkillsRepository : JpaRepository<SkillEntity, Long>
