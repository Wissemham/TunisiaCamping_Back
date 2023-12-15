package esprit.tunisiacamp.repositories;


import esprit.tunisiacamp.entities.forum.Reponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ReponseRepository extends CrudRepository<Reponse, Long> {
}
