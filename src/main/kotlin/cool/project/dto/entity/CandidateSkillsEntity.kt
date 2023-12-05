package cool.project.dto.entity

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "candidate_skills")
@IdClass(CandidateSkillsId::class)
data class CandidateSkillsEntity(

    @Id
    @ManyToOne
    @JoinColumn(name = "skill", nullable = false)
    val skill: SkillEntity,

    @Id
    @ManyToOne
    @JoinColumn(name = "candidate")
    val candidate: CandidateEntity,

    @Column(name = "avg_skill_value", nullable = true)
    val averageSkillLevel: Double?,
)

@Embeddable
data class CandidateSkillsId(
    @ManyToOne
    @JoinColumn(name = "skill", nullable = false)
    val skill: SkillEntity,

    @ManyToOne
    @JoinColumn(name = "candidate")
    val candidate: CandidateEntity
) : Serializable

