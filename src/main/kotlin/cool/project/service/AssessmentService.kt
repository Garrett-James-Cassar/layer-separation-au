package cool.project.service

import cool.project.adaptor.CandidateAdaptor
import cool.project.adaptor.ExpertAdaptor
import cool.project.connectors.repositories.CandidateRepository
import cool.project.stub.database.HardCodedExpertRepository
import cool.project.domain.enums.SubSkill
import cool.project.dto.entity.CandidateEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AssessmentService(
    private val candidateRepository: CandidateRepository,
    private val candidateAdaptor: CandidateAdaptor,
    private val expertRepository: HardCodedExpertRepository,
    private val expertAdaptor: ExpertAdaptor,
) {

    fun giveVerbalFeedbackToCandidate(id: Long, skillUnderAssessment: String) : String {
        val candidateEntity : CandidateEntity = candidateRepository.findByIdOrNull(id)!!
        val candidate = candidateAdaptor.toDomain(candidateEntity)
        val expert = expertAdaptor.toDomain(expertRepository.findBySkill(skillUnderAssessment))
        val typedSkillUnderAssessment = SubSkill.valueOf(skillUnderAssessment)

        return if(candidate.isAdult()) {
            expert.verbalFeedbackMappingForGrownUps[candidate.subSkillLevels[typedSkillUnderAssessment]]!!
        } else {
            expert.verbalFeedbackMappingForChildren[candidate.subSkillLevels[typedSkillUnderAssessment]]!!
        }
    }

}