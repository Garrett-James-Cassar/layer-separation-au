package cool.project.dto.entity

import jakarta.persistence.*

@Entity
@Table(name = "skills_mapping")
data class SkillsMapping(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    val candidate: CandidateEntity,

    @Column(name = "skill_key", nullable = false)
    val skillKey: String,

    @Column(name = "skill_value", nullable = false)
    val skillValue: String
)