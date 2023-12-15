package esprit.tunisiacamp.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.camping.CampingGround;
import esprit.tunisiacamp.entities.camping.Reservation;
import esprit.tunisiacamp.paypal.PaypalService;
import esprit.tunisiacamp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import static jdk.internal.logger.DefaultLoggerFinder.SharedLoggers.system;

@Service
public class ReservationService implements IReservationService{
    @Autowired
    private ReservationRepositories reservationRepository;
    @Autowired
    private CampingGroundRepositories campingGroundRepositories;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FavoriteRepositories favoriteRepositories;
    @Autowired
    private AdvantagesRepositories advantagesRepositories;
    @Autowired
    private ActivityRepositories activityRepository;
    @Autowired
    private GroupService groupService;
    @Autowired
    PaypalService paypalService;

    @Override
    public Reservation saveReservation(Reservation reservation,Integer campingId,Integer userId) {
        CampingGround cp = campingGroundRepositories.findById(Long.valueOf(campingId)).get();
        reservation.setEtat("impayée");
        if (reservation.getParticipant_number()==0){
            //return new RedirectView("Nombre de participants est required !!");
        }
        else {
            cp.setNbreservation(cp.getNbreservation() + reservation.getParticipant_number());
            if (cp.getNbreservation() <= cp.getCapacity()) {
                reservationRepository.save(reservation);
                affecterReservationToCamping((int) reservation.getIdReservation(), campingId, userId);
                //return new RedirectView("http://localhost:9090/rest/hm");
            } else {
                cp.setNbreservation(cp.getNbreservation() - reservation.getParticipant_number());
                int p = cp.getCapacity() - cp.getNbreservation();
               // return new RedirectView("Place disponible est " + p + " !!");
            }
        }
        return reservationRepository.save(reservation);
    }

    @Override
    public Optional<Reservation> getAllReservations(long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public void deleteReservationById(long id) {
        reservationRepository.deleteById(id);

    }

    @Override
    public List<Reservation> getAllReservation(Integer userId) {
        return reservationRepository.getReservation(userId);
    }
    //private CampingGroundRepositories campingGroundRepositories;


    @Override
    public void affecterReservationToCamping(Integer reservationId, Integer campingId,Integer userId) {
        Reservation rs = reservationRepository.findById(Long.valueOf(reservationId)).get();
        CampingGround cp = campingGroundRepositories.findById(Long.valueOf(campingId)).get();
        //rs.setParticipant_number(nb);
        //cp.setNbreservation(cp.getNbreservation()+ rs.getParticipant_number());
        User us = userRepository.findById(userId).get();

            rs.setUserReservation(us);
            rs.setCampingGround(cp);
            reservationRepository.save(rs);

    }

    @Override
    public List<User> matchCampers(Integer reservationId) {
        Reservation rs = reservationRepository.findById(Long.valueOf(reservationId)).get();
        //Optional<Activity> ac = activityRepository.findById(Long.valueOf(reservationRepository.getactivityid(rs.getCampingGround().getIdCampingGround())));
        //String name = reservationRepository.getidAct(reservationRepository.getactivityid(Long.valueOf(reservationId)));
//        Date startDate = rs.getStart_date();
//        Date endDate = rs.getEnd_date();
        List acts = reservationRepository.getallActcamp((long) rs.getUserReservation().getIdUser());
        System.out.println(acts);
        int score;
        String a= null;
        List<Reservation> reservations = reservationRepository.findAll();
        ArrayList<Reservation> edit=new ArrayList<Reservation>();
        ArrayList<User> users = new ArrayList<User>();
        for (Reservation reservation : reservations) {

            score=0;
            if (reservation.getCampingGround() == rs.getCampingGround()){
                if (reservation.getStart_date().equals(rs.getStart_date())){
                    score= score +3;
                }
                if (reservation.getEnd_date().equals(rs.getEnd_date())){
                    score = score +3;
                }
                 if (reservation.getStart_date().after(rs.getStart_date()) && reservation.getStart_date().before(rs.getEnd_date())){
                         score = score + 1;
                 }
                if (reservation.getEnd_date().after(rs.getStart_date()) && reservation.getEnd_date().before(rs.getEnd_date())){
                    score = score + 1;
                }
                 if (score != 0){
                     List act = reservationRepository.getallActcamp((long)reservation.getUserReservation().getIdUser());
                     for (Object i : acts){
                         for (Object j : act){
                             if (i == j){
                                 score = score + 1;
                             }
                         }
                     }
                     if (score>3){
                         User user = userRepository.findById(reservation.getUserReservation().getIdUser()).get();
                         users.add(user);
                     }
                 }
                // if ()
//                 if ( score != 0){
//                     if (reservationRepository.getidAct(reservationRepository.getactivityid(Long.valueOf(reservation.getIdReservation()))) != null){
//                         if (reservationRepository.getidAct(reservationRepository.getactivityid(Long.valueOf(reservation.getIdReservation()))).toLowerCase().equals(name)) {
//                             score = score + 4;
//                         }
//                     }                 }
            }

            reservation.setParticipant_number(score);
            edit.add(reservation);
        }
        for (User us :users){
            groupService.saveGroup(reservationId, (int) us.getIdUser());
        }
        return users ;
    }
    private final String ACCOUNT_SID = "AC40a2bf2c3b42a8ca159c39d88298e173";
    private final String AUTH_TOKEN = "29ca782d1b148a5088c9285361ca9b9a";
    @Autowired
    private ReviewRepositories reviewRepositories;

    // private final String FROM_NUMBER = "+14302492629";
    @Override
    public void sendSms() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber("+21692108297"),
                new PhoneNumber("+14302492629"),
                "Reminder: Your reservation is tomorrow.").create();

        System.out.println(message.getSid());
    }
    //@Scheduled(cron = "5 * * * * *")
    public void sendReminderSms() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
       List<Reservation> reservations = reservationRepository.findByReservationDate(tomorrow);
       tomorrow.isBefore(today);
//
       for (Reservation reservation : reservations) {
           Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
           Message message = Message.creator(
                   new PhoneNumber("+21692108297"),
                   new PhoneNumber("+14302492629"),
                   "Reminder: Hi Mr/Ms "+reservation.getUserReservation().getFirstname()+", the date for reservation in "+reservation.getCampingGround().getName()+","+reservation.getCampingGround().getLocation()+" is Tommorow !! " +
                           "Thank you for choosing us, and we hope to see you soon.").create();

           System.out.println(message.getSid());
      }
    }

    @Override
    public void updatePayer(long id) {
        Reservation rs =reservationRepository.findById(id).get();
        rs.setEtat("payer");
        reservationRepository.save(rs);
    }

    @Override
    public boolean isPayer(long id) {
        Reservation rs = reservationRepository.findById(id).get();

            if ( rs.getEtat().equals("payer")){
                System.out.println("payer");
                return true;
            }else {
        return false;
    }}

    @Override
    public List<Reservation> listPeyer() {
        return reservationRepository.listpayer();
    }
    @Override
    public List<Reservation> listimPeyer() {
        return reservationRepository.listimpayer();
    }

    @Override
    public String reservation(long id) {
        CampingGround cp = campingGroundRepositories.findById(Long.valueOf(id)).get();

        if (cp.getCapacity()== cp.getNbreservation()){
            return "Complete";
        }
        else {
            return Integer.toString(cp.getCapacity() - cp.getNbreservation());
        }
    }

    @Override
    public List<Reservation> AllReservation() {
        return reservationRepository.findAll();
    }
}
