package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.forum.Mots;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotsREpository extends CrudRepository<Mots, Long> {
}
