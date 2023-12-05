package cool.project.dto.entity

import jakarta.persistence.*

@Entity
@Table(name = "sub_skills_mapping")
data class SubSkillEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "sub_skill_name", nullable = false)
    val subSkillName: String,

    @Column(name = "sub_skill_value", nullable = true)
    val subSkillValue: Double,

    @Column(name = "skill_id", nullable = false)
    val skillId: Long?
)