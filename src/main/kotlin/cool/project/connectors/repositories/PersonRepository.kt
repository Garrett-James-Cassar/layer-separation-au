package cool.project.connectors.repositories

import cool.project.connectors.dto.entity.PersonEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : CrudRepository<PersonEntity, Long> {

}
