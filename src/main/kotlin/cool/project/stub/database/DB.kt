package cool.project.stub.database

import cool.project.domain.ExpertDomain
import cool.project.domain.enums.Skill
import cool.project.domain.enums.SkillLevel
import cool.project.dto.entity.ExpertEntity


val GordonRamsay = ExpertEntity("Gordon Ramsay", Skill.Cooking.name,
    mapOf(
        SkillLevel.Terrible.name to "You are an idiot sandwich",
        SkillLevel.OK.name to "I think you need a reality check",
        SkillLevel.Great.name to "Pack your bags. Because you're coming to work with me"
    ),
    mapOf(
        SkillLevel.Terrible.name to "Never stop cooking, you're going to grow up to be an amazing chef",
        SkillLevel.OK.name to "You're parents must be so proud of you",
        SkillLevel.Great.name to "I think that you are a sensation and all your dreams will come true",
    ))

val GaryCassar = ExpertEntity("Gary Cassar", Skill.Coding.name,
    mapOf(
        SkillLevel.Terrible.name to "Thanks for coming, we'll be in contact",
        SkillLevel.OK.name to "How long is your notice?",
        SkillLevel.Great.name to "How much money do you want?"
    ),
    mapOf(
        SkillLevel.Terrible.name to "Never stop coder, you're going to grow up to be an amazing coder",
        SkillLevel.OK.name to "You're parents must be so proud of you",
        SkillLevel.Great.name to "I think that you are a sensation and all your dreams will come true",
    ))

val expertDomainList : List<ExpertEntity> = listOf(GordonRamsay, GaryCassar)