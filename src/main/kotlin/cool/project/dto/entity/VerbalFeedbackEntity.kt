package cool.project.dto.entity

import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "verbal_feedback")
data class VerbalFeedbackEntity(

    @EmbeddedId
    val verbalFeedbackEntityKey: VerbalFeedbackEntityKey,

    @Column(name = "skillQualifiedToAssess", nullable = false)
    val feedback : String

)