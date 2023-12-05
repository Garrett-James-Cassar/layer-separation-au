package cool.project.dto.entity

import jakarta.persistence.*

@Entity
@Table(name = "candidate")
data class CandidateEntity(

    @Id
    @Column(name = "name", nullable = false, length = 255)
    val name: String,

    @Column(name = "age", nullable = false)
    val age: Int,

    @OneToMany(mappedBy = "candidate", cascade = [CascadeType.ALL], orphanRemoval = true)
    val skills: List<CandidateSkillsEntity>,
)

