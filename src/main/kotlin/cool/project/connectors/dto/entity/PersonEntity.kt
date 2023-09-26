package cool.project.connectors.dto.entity

import jakarta.persistence.Id
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "persons")
data class PersonEntity(

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    val id: Long,

    @Column(name = "name", nullable = false, length = 255)
    val name: String,

    @Column(name = "age", nullable = false)
    val age: Int,

    @Column(name = "skill", nullable = true)
    val skills: List<String>
)

