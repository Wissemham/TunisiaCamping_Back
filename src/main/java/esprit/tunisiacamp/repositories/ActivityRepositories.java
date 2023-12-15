package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.Activity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepositories extends CrudRepository<Activity,Long> {
}
