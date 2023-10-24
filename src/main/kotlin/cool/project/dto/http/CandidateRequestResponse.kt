package cool.project.dto.http

data class CandidateRequestResponse(val name: String, val age : Int, val skills: Map<String, String>)