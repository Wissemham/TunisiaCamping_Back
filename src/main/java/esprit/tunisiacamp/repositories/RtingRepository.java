package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.forum.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RtingRepository extends CrudRepository<Rating, Long> {
}
