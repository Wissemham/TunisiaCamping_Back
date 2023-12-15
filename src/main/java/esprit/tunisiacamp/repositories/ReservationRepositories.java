package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.camping.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepositories extends JpaRepository<Reservation,Long> {
    @Query(value = "select * from reservation r where r.start_date=?1",nativeQuery = true)
    List<Reservation> findByReservationDate(LocalDate reservationDate);
    @Query(value = "select * from reservation r where r.user_reservation_id_user=?1",nativeQuery=true)
    List<Reservation> getReservation(Integer userId);
//    @Query(value = "select f.activity_fav_id_activitiy from favorite f where f.campingg_fav_id_camping_ground=?1 ",nativeQuery = true)
//    Long getactivityid(Long campingId);
//    @Query(value = "select a.name from activity a where a.id_activitiy=?1",nativeQuery = true)
//    String getidAct(Long idAct);
//    @Query(value = "select f.user_fav_id_user from favorite f where f.campingg_fav_id_camping_ground=?1",nativeQuery = true)
//    List getallusercamp(Long campingId);
    @Query(value = "select f.activity_fav_id_activitiy from favorite f where f.user_fav_id_user=?1",nativeQuery = true)
    List getallActcamp(Long campingId);

    @Query(value = "SELECT * FROM reservation r WHERE r.etat = 'payer' ", nativeQuery = true)
    List<Reservation> listpayer();

    @Query(value = "SELECT * FROM reservation r WHERE r.etat = 'impayée' ", nativeQuery = true)
    List<Reservation> listimpayer();

}
