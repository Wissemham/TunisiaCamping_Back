package esprit.tunisiacamp.repository;

import esprit.tunisiacamp.entities.model.Userr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface UserRepositoryy extends JpaRepository<Userr, Integer> {
    HashSet<Userr> findByusername(String username);
    //  Optional<User> findByName(String userName);

 //   Optional<User> findByusername(String username);
}
