package cool.project.domain.enums

enum class Skills {
    Coding,Cooking,DancingTheFlemenco,Unknown;

    companion object {
        fun valueOrUnknown(skillString: String) : Skills = values().firstOrNull { it.name == skillString } ?: Unknown
    }
}