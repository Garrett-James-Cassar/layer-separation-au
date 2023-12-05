package cool.project.service

import cool.project.adaptor.toDomain
import cool.project.adaptor.toEntity
import cool.project.connectors.repositories.CandidateRepository
import cool.project.connectors.repositories.ExpertRepository
import cool.project.connectors.repositories.CandidateSkillsRepository
import cool.project.dto.http.ExpertAssessment
import cool.project.dto.http.SubSkillAssessment
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class AssessmentService(
    private val candidateRepository: CandidateRepository,
    private val expertRepository: ExpertRepository,
    private val candidateSkillsRepository: CandidateSkillsRepository,
) {

    fun assess(id: String): List<ExpertAssessment> {
        val candidate = candidateRepository.findByIdOrNull(id)!!.toDomain()

        return candidate.skills.map { skill ->
            val subSkillRatings = skill.subskills.map { subSkill ->
                SubSkillAssessment(subSkill.name, Random.nextDouble(10.0))
            }
            val skillEntity = candidateSkillsRepository.findBySkill(skill.toEntity()).get()
            val expert = expertRepository.findBySkill(skillEntity).get().toDomain()

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