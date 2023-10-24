package cool.project.connectors.repositories

import cool.project.stub.database.expertDomainList
import org.springframework.stereotype.Repository


@Repository
class HardCodedExpertRepository  {
    fun findBySkill(skill: String) = expertDomainList.find { it.skillQualifiedToAssess == skill }!!
}


