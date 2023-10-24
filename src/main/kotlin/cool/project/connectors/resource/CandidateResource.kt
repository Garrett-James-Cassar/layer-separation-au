package cool.project.connectors.resource

import cool.project.adaptor.CandidateAdaptor
import cool.project.dto.http.CandidateRequestResponse
import cool.project.service.CanidateService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/persons")
class PersonController(val canidateService: CanidateService, val personAdapter: CandidateAdaptor) {

    @PostMapping
    fun createPerson(@RequestBody candidateRequestResponse: CandidateRequestResponse): ResponseEntity<CandidateRequestResponse> {
        val savedPerson = canidateService.createPerson(personAdapter.toDomain(candidateRequestResponse))
        return ResponseEntity.ok(personAdapter.toDto(savedPerson))
    }

    @GetMapping("/{id}")
    fun getPerson(@PathVariable id: Long): ResponseEntity<CandidateRequestResponse> {
        val person = canidateService.getPerson(id)
        return ResponseEntity.ok(personAdapter.toDto(person))
    }

    @GetMapping
    fun getAllPersons(): ResponseEntity<List<CandidateRequestResponse>> {
        val persons = canidateService.getAllPersons()
        return ResponseEntity.ok(persons.map(personAdapter::toDto))
    }

    @DeleteMapping("/{id}")
    fun deletePerson(@PathVariable id: Long): ResponseEntity<Void> {
        canidateService.deletePerson(id)
        return ResponseEntity.noContent().build()
    }
}