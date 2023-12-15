package esprit.tunisiacamp.repositeries;

import esprit.tunisiacamp.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepositery extends CrudRepository<User,Long> {
}
