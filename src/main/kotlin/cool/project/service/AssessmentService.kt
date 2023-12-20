package cool.project.service

import cool.project.adaptor.toDomain
import cool.project.adaptor.toEntity
import cool.project.connectors.repositories.CandidateRepository
import cool.project.connectors.repositories.ExpertRepository
import cool.project.connectors.repositories.CandidateSkillsRepository
import cool.project.dto.http.ExpertAssessment
import cool.project.dto.http.SubSkillAssessment
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import kotlin.random.Random

@Service
class AssessmentService(
    private val candidateRepository: CandidateRepository,
    private val expertRepository: ExpertRepository,
    private val candidateSkillsRepository: CandidateSkillsRepository,
) {

    fun assess(name: String): List<ExpertAssessment> {
        val candidate = candidateRepository.findByName(name).toDomain()

        return candidate.skills.map { skill ->
            val subSkillRatings = skill.subskills.map { subSkill ->
                SubSkillAssessment(subSkill.name, Random.nextDouble(10.0))
            }
            val expertEntity = expertRepository.findBySkill(skill.toEntity()) ?: throw IllegalArgumentException("No expert to assess ${skill.name()}")
            val expert = expertEntity.toDomain()

            ExpertAssessment(
                expert.name,
                expert.skillQualifiedToAssess.name(),
                subSkillRatings.map { it.subSkillRating }.average(),
                "",
                subSkillRatings
            )
        }

    }
}