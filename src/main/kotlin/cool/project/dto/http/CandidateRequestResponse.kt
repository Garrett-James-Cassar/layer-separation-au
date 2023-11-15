package cool.project.dto.http

import cool.project.domain.Skill

data class CandidateRequestResponse(val name: String, val age : Int, val skills: List<Skill>)