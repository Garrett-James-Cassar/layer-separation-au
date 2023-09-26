package cool.project.adaptor

import cool.project.domain.PersonDomain
import cool.project.connectors.dto.entity.PersonEntity
import cool.project.connectors.dto.http.PersonRequestResponse
import cool.project.domain.enums.Skills
import org.springframework.stereotype.Component
import java.lang.Math.random

@Component
class PersonAdaptor {
    fun toDomain(personRequestResponse: PersonRequestResponse) =
        PersonDomain(personRequestResponse.name, personRequestResponse.age, personRequestResponse.skills.map { Skills.valueOrUnknown(it) })

    fun toDomain(personEntity: PersonEntity) =
        PersonDomain(personEntity.name, personEntity.age, convertToSkills(personEntity.skills))

    fun toDto(personDomain: PersonDomain) =
        PersonRequestResponse(personDomain.name, personDomain.age, personDomain.skills.map { it.name })

    fun toEntity(personDomain: PersonDomain) =
        PersonEntity(id = random().toLong(), personDomain.name, personDomain.age, personDomain.skills.map { it.name })

    fun convertToSkills(skillStrings: List<String>) = skillStrings.map { Skills.valueOrUnknown(it) }
}