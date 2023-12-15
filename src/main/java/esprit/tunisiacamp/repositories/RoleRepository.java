package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.Autority;
import esprit.tunisiacamp.entities.Role;
import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.enums.role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {



    @Transactional
    @Query("SELECT a FROM Role a WHERE a.role=:r")
    public Role getRole(role r);
    //Optional<Role> findByName(role name);


}
