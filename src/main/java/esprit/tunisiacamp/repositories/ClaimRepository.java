package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.Claim;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ClaimRepository extends CrudRepository<Claim,Long> {
    List<Claim> findByState(boolean state);
    List<Claim> findByUser_IdUser(Integer idUser);
    @Query("SELECT c FROM Claim c WHERE c.user.role = ?1")
    List<Claim> findByRole(String role);
    @Query("SELECT c.category, COUNT(c) FROM Claim c GROUP BY c.category")
    List<Object[]> countByCategory();
    @Query("SELECT c.category, COUNT(c) FROM Claim c WHERE c.creation BETWEEN :startDate AND :endDate GROUP BY c.category")
    List<Object[]> countClaimsByCategoryBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
