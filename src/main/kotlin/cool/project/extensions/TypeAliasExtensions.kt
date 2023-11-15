package cool.project.extensions

import cool.project.domain.enums.SubSkill
import cool.project.domain.enums.SubSkillLevel


typealias SkillMap = Map<SubSkill, SubSkillLevel>
fun SkillMap.toStringMap() = this.map { (key, value) -> key.name to value.name }.toMap()
