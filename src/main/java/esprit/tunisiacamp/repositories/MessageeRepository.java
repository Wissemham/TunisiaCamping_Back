package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.forum.Messagee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MessageeRepository extends CrudRepository<Messagee, Long> {
}
