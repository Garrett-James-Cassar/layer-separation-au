package cool.project.domain

import cool.project.domain.enums.SubSkill
import cool.project.domain.enums.SubSkill.*

sealed class Skill(val subskills: List<SubSkill> = emptyList()) {
    fun name() = this.javaClass.name
}
private class Coding : Skill(listOf(LayerSeparation, Testing, Readability, LackOfFlair))
private class Cooking : Skill(listOf(Plating, Flavouring, TemperatureControl))
class DancingTheFlamenco : Skill(listOf(Timing, Coordindation, Passion, Flair))
class Unknown : Skill(listOf())


val skillMap = mapOf(
    "cooking" to Cooking(),
    "coding" to Coding(),
    "dancingTheFlamenco" to DancingTheFlamenco(),
)


