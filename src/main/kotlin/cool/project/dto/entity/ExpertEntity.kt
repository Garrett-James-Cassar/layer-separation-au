package cool.project.dto.entity

import jakarta.persistence.*

@Entity
@Table(name = "expert")
class ExpertEntity(

    @Id
    @Column(name = "name", nullable = false)
    val name: String,

    @OneToOne
    @JoinColumn(name = "skill_name", nullable = false)
    val skill: SkillEntity,

//    TODO Figure out how to model this later
//    @OneToMany
//    @JoinColumn(columnDefinition = "verbal_feedback", name = "verbal_feedback_id", nullable = false)
//    val verbalFeedbackEntity: List<VerbalFeedbackEntity>

)

// Skill -* Subskills
// Candidate -* Skills: AverageSkillRating, List<SubSkillRating>
// Candidate -* SubSkills: SubSkillRating
