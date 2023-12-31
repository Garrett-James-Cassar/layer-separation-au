package cool.project.domain

import cool.project.domain.enums.SubSkill
import cool.project.domain.enums.SubSkill.*

sealed class Skill(val subskills: List<SubSkill> = emptyList()) {
    fun name() : String = this.javaClass.simpleName
}

class Coding constructor() : Skill(subskills = listOf(LayerSeparation, Testing, Readability, LackOfFlair))
class Cooking constructor() : Skill(subskills = listOf(Plating, Flavouring, TemperatureControl))
class DancingTheFlamenco constructor() : Skill(subskills = listOf(Timing, Coordindation, Passion, Flair))
class Unknown : Skill(listOf())


val skillMap = mapOf(
    "cooking" to Cooking(),
    "coding" to Coding(),
    "dancingtheflamenco" to DancingTheFlamenco(),
)


