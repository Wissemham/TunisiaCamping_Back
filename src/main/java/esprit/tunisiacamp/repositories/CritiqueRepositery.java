package esprit.tunisiacamp.repositeries;

import esprit.tunisiacamp.entities.shopping.Critique;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CritiqueRepositery extends CrudRepository<Critique,Long> {
}
