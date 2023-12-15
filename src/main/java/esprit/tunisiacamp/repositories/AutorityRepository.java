package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.Autority;
import esprit.tunisiacamp.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorityRepository extends CrudRepository<Autority,Integer> {

    @Query("SELECT a FROM Autority a WHERE a.UserAuth=:u")
    public Autority getAutority(User u);
}
