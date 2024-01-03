package cool.project.service

import cool.project.adaptor.toDomain
import cool.project.adaptor.toEntity
import cool.project.connectors.repositories.ExpertRepository
import cool.project.connectors.repositories.SkillsRepository
import cool.project.domain.Expert
import cool.project.error.NoExpertException
import org.springframework.stereotype.Service

@Service
class ExpertService(
    val expertRepository: ExpertRepository,
    val skillsRepository: SkillsRepository,
) {

    fun registerExpert(expert: Expert) : Expert {
        skillsRepository.save(expert.skillQualifiedToAssess.toEntity())
        val expertEntity = expertRepository.save(expert.toEntity())
        return expertEntity.toDomain()
    }

    fun getExpert(id: String): Expert {
        val entity = expertRepository.findById(id).orElse(null) ?: throw NoExpertException(id)
        return entity.toDomain()
    }
    fun getAllExperts() = expertRepository.findAll().map { it.toDomain() }

    fun unregisterExpert(id: String) {
        if (!expertRepository.existsById(id)) {
            throw NoExpertException(id)
        }
        expertRepository.deleteById(id)
    }
}