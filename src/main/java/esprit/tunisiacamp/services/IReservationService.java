package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.camping.Reservation;

import java.util.List;
import java.util.Optional;

public interface IReservationService {
    Reservation saveReservation(Reservation reservation, Integer campingId, Integer userId);
    Optional<Reservation> getAllReservations(long id);
    void deleteReservationById(long id);
    List<Reservation> getAllReservation(Integer userId);
    void affecterReservationToCamping (Integer reservationId , Integer campingId,Integer userId);
    public List<User> matchCampers(Integer reservationId);
    void sendSms();
    void sendReminderSms();
    void updatePayer(long id);
    boolean isPayer ( long id);
    List<Reservation> listPeyer();
    List<Reservation> listimPeyer();
    String reservation(long id);
    List<Reservation> AllReservation();
}
