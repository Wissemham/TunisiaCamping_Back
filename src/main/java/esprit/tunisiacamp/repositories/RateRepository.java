package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.camping.Rate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RateRepository extends CrudRepository<Rate, Long> {
    @Query(value = "select id_rate from rate r  where r.camp_rate_id_camping_ground = ?1 and r.user_id_user = ?2", nativeQuery = true)
    String rateid(long id,long user);
}