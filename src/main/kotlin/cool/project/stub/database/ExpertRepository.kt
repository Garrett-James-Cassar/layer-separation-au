package cool.project.stub.database

import org.springframework.stereotype.Repository

@Repository
class HardCodedExpertRepository  {
    fun findBySkill(skill: String) = expertDomainList.find { it.skillQualifiedToAssess.name() == skill }!!
}


