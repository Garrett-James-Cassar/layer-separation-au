package cool.project.extensions

import cool.project.domain.enums.Skill
import cool.project.domain.enums.SkillLevel


typealias SkillMap = Map<Skill, SkillLevel>
fun SkillMap.toStringMap() = this.map { (key, value) -> key.name to value.name }.toMap()
