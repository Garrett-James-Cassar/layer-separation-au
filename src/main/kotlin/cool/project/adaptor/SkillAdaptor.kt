package cool.project.adaptor


import cool.project.domain.Skill
import cool.project.domain.enums.SubSkill
import cool.project.domain.skillMap
import cool.project.dto.entity.CandidateEntity
import cool.project.dto.entity.CandidateSkillsEntity
import cool.project.dto.entity.SkillEntity
import cool.project.dto.entity.SubSkillEntity

fun CandidateSkillsEntity.toDomain() : Skill =
    skillMap[skill.skillName.lowercase()]!!

fun Skill.toEntity() : SkillEntity = SkillEntity(name())
fun SkillEntity.toDomain() : Skill = skillMap[skillName.lowercase()]!!

fun Skill.toEntity(candidate : CandidateEntity, averageSkill : Double? = null) : CandidateSkillsEntity =
    CandidateSkillsEntity(SkillEntity(name()), candidate, averageSkill)

fun SubSkill.toEntity(skillId: Long? = null, subSkillRating: Double) : SubSkillEntity =
    SubSkillEntity(null, name, subSkillRating , skillId)