package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.camping.Advantage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvantagesRepositories extends CrudRepository<Advantage,Long> {
    @Query(value = "select * from advantage a where a.type=?1",nativeQuery=true)
    List<Advantage> getAdvantagesByType(String type);
    @Query(value = "select * from advantage a where a.type=?1",nativeQuery=true)
    List<Advantage> getAdvantages(long id);
    @Query(value = "select a.* from advantage a left join camping_ground_advantages c on a.id_advantage = c.advantages_id_advantage where c.camping_grounds_id_camping_ground = ?1", nativeQuery = true)
    List<Advantage> CampAdvantage (long idcamp);
}
