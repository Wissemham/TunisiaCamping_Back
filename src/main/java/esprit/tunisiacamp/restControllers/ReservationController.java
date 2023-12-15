package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.camping.Reservation;
import esprit.tunisiacamp.services.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ReservationController {
    @Autowired
    private IReservationService reservationService;
    @PostMapping("AddReservation")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation, @RequestParam int campingId, @RequestParam int userId) {
        Reservation reservation1= reservationService.saveReservation(reservation,campingId,userId);
    return ResponseEntity.ok(reservation1);}
    @PutMapping("affecterReservationToCamping")
    public void affecterReservationToCamping(@RequestParam int idr, @RequestParam int idc, @RequestParam int idu){
       // System.out.println("job");
        reservationService.affecterReservationToCamping(idr,idc,idu);
    }
    @CrossOrigin("http://localhost:4200")
    @GetMapping("getReservation")
    public List<Reservation> getAllReservation(Integer userId) {
        return reservationService.getAllReservation(userId);}
    @GetMapping("getReservationId")
    public Optional<Reservation> getAllReservations(long id) {
        return reservationService.getAllReservations(id);
    }
    @DeleteMapping("deletereservation/{id}")
    public void deleteReservationById(@RequestParam long id){
        reservationService.deleteReservationById(id);
    }
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("MatchGroup")
    public List<User> matchCampers(Integer reservationId){
        return reservationService.matchCampers(reservationId);
    }
    //@Scheduled(cron = "5 * * * * *")
    @PostMapping("sms")
    public void sendSms(){
        reservationService.sendReminderSms();
    }
    @PutMapping ("updateReservation")
    public void updateReservation(long id){
        reservationService.updatePayer(id);
    }
    @GetMapping("isPayer")
    public String isFavorite(@RequestParam long id) {
        String p="2";
        if (reservationService.isPayer(id)==true){
            p="1";
        };
        System.out.println(p);
        return p;
    }
    @GetMapping("isPayere")
    public List<Reservation> listpayer () {
        return reservationService.listPeyer();
    }
    @GetMapping("isimPayere")
    public List<Reservation> listimpayer () {
        return reservationService.listimPeyer();
    }
    @GetMapping("nombreRest")
    public String reservation(@RequestParam long id){
        return reservationService.reservation(id);
    }
    @GetMapping("AllReservation")
    public List<Reservation> AllReservation() {
        return reservationService.AllReservation();
    }
    }
