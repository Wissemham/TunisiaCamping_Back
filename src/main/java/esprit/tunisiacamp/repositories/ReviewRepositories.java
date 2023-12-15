package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.camping.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepositories extends CrudRepository<Review,Long> {
    @Query(value = "select * from review r where r.review_ground_id_camping_ground=?1",nativeQuery = true)
    List<Review> findByCamp(Long id);
}
