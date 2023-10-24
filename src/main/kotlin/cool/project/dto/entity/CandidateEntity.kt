package cool.project.dto.entity

import jakarta.persistence.*

@Entity
@Table(name = "persons")
data class CandidateEntity(

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    val id: Long,

    @Column(name = "name", nullable = false, length = 255)
    val name: String,

    @Column(name = "age", nullable = false)
    val age: Int,

    @ElementCollection
    @CollectionTable(name = "skills_mapping", joinColumns = [JoinColumn(name = "candidate_id")])
    @MapKeyColumn(name = "skill_key")
    @Column(name = "skill_value")
    val skills: Map<String, String>
)

