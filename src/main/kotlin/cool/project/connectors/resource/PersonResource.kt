package cool.project.connectors.resource

import cool.project.adaptor.PersonAdaptor
import cool.project.connectors.dto.http.PersonRequestResponse
import cool.project.service.PersonService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/persons")
class PersonController(val personService: PersonService, val personAdapter: PersonAdaptor) {

    @PostMapping
    fun createPerson(@RequestBody personRequestResponse: PersonRequestResponse): ResponseEntity<PersonRequestResponse> {
        val savedPerson = personService.createPerson(personAdapter.toDomain(personRequestResponse))
        return ResponseEntity.ok(personAdapter.toDto(savedPerson))
    }

    @GetMapping("/{id}")
    fun getPerson(@PathVariable id: Long): ResponseEntity<PersonRequestResponse> {
        val person = personService.getPerson(id)
        return ResponseEntity.ok(personAdapter.toDto(person))
    }

    @GetMapping
    fun getAllPersons(): ResponseEntity<List<PersonRequestResponse>> {
        val persons = personService.getAllPersons()
        return ResponseEntity.ok(persons.map(personAdapter::toDto))
    }

    @PutMapping("/{id}")
    fun updatePerson(@PathVariable id: Long, @RequestBody personRequestResponse: PersonRequestResponse): ResponseEntity<PersonRequestResponse> {
        val personDomain = personAdapter.toDomain(personRequestResponse)
        personService.updatePerson(id, personDomain)
        return ResponseEntity.ok(personAdapter.toDto(personDomain))
    }

    @DeleteMapping("/{id}")
    fun deletePerson(@PathVariable id: Long): ResponseEntity<Void> {
        personService.deletePerson(id)
        return ResponseEntity.noContent().build()
    }
}