package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.camping.CampingGround;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampingGroundRepositories extends CrudRepository<CampingGround,Long> {
    @Query(value = "select * from camping_ground c where lower(c.genre)=lower(?1) or LOWER(c.location)=LOWER(?2) or LOWER(c.name)=LOWER(?3)",nativeQuery=true)
    List<CampingGround> searcCamp(String genre, String location, String name);
    @Query(value = "select * from camping_ground c where c.user_camp_id_user = ?1", nativeQuery = true)
    List findCampingsByUserId(long userId);

    @Query(value = "select c.name from camping_ground c where c.id_camping_ground = ?1", nativeQuery = true)
    String Campname(long campId);
}
