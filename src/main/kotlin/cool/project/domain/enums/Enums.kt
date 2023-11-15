package cool.project.domain.enums

enum class SubSkill {

    // Cooking
    Plating,
    Flavouring,
    TemperatureControl,

    // Coding
    LayerSeparation,
    Testing,
    Readability,
    LackOfFlair,

    // Dancing The Flamenco
    Timing,
    Coordindation,
    Passion,
    Flair;

    companion object {
        fun valueOrUnknown(subSkillString: String): SubSkill = values().first { it.name == subSkillString }
    }
}

enum class SubSkillLevel {
    Terrible,OK,Great,Unknown;

    companion object {
        fun valueOrUnknown(skillString: String) : SubSkillLevel = SubSkillLevel.values()
            .firstOrNull { it.name == skillString } ?: SubSkillLevel.Unknown
    }
}