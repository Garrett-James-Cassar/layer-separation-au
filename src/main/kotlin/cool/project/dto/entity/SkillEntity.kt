package cool.project.dto.entity

import jakarta.persistence.*

@Entity
@Table(name = "skill")
data class SkillEntity(

    @Id
    @Column(name = "skill_name", nullable = false)
    val skillName: String,

)