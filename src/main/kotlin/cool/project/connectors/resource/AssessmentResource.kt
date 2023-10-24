package cool.project.connectors.resource

import cool.project.domain.enums.Skill
import cool.project.service.AssessmentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/persons")
class AssessmentResource(val assessmentService: AssessmentService) {

    @GetMapping("/{id}/{skill}")
    fun getAssessment(@PathVariable id: Long, skill : String): ResponseEntity<String> {
        return ResponseEntity.ok(assessmentService.giveVerbalFeedbackToCandidate(id, skill))
    }

}