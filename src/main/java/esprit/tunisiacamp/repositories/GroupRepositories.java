package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.camping.GroupCamp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepositories extends CrudRepository<GroupCamp,Long> {
    @Query(value = "select * from group_camp g where g.user_grw_id_user = ?1", nativeQuery = true)
    List findGroupByUserId(Integer userId);
}
