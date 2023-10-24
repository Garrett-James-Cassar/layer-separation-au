package cool.project.domain.enums

enum class Skill {
    Coding,Cooking,DancingTheFlemenco,Unknown;

    companion object {
        fun valueOrUnknown(skillString: String) : Skill = values().firstOrNull { it.name == skillString } ?: Unknown
    }
}

enum class SkillLevel {
    Terrible,OK,Great,Unknown;

    companion object {
        fun valueOrUnknown(skillString: String) : SkillLevel = SkillLevel.values()
            .firstOrNull { it.name == skillString } ?: SkillLevel.Unknown
    }
}