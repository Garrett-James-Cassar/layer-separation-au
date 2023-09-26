package cool.project.domain

import cool.project.domain.enums.Skills

data class PersonDomain(val name: String, val age : Int, val skills: List<Skills>)