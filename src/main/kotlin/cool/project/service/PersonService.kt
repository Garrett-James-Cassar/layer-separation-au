package cool.project.service

import cool.project.adaptor.PersonAdaptor
import cool.project.connectors.repositories.PersonRepository
import cool.project.domain.PersonDomain
import org.springframework.stereotype.Service

@Service
class PersonService(val personRepository: PersonRepository, val personAdaptor: PersonAdaptor) {

    fun createPerson(person: PersonDomain): PersonDomain {
        val entity = personAdaptor.toEntity(person)
        val savedEntity = personRepository.save(entity)
        return personAdaptor.toDomain(savedEntity)
    }

    fun getPerson(id: Long): PersonDomain {
        val entity = personRepository.findById(id).orElse(null)
            ?: throw IllegalArgumentException("Person with ID $id not found")
        return personAdaptor.toDomain(entity)
    }

    fun getAllPersons(): List<PersonDomain> {
        return personRepository.findAll().map { personAdaptor.toDomain(it) }
    }

    fun updatePerson(id: Long, personUpdate: PersonDomain) {

    }

    fun deletePerson(id: Long) {
        if (!personRepository.existsById(id)) {
            throw IllegalArgumentException("Person with ID $id not found")
        }
        personRepository.deleteById(id)
    }

}