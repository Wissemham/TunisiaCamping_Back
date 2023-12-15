package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.forum.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface postRepository extends CrudRepository<Post, Long> {
}
