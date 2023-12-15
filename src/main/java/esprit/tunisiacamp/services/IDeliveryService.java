package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.Role;
import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.shopping.Delivery;
import org.springframework.data.repository.query.Param;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Map;

public interface IDeliveryService {
    void addDelivery(Delivery del);

    List<Delivery> findDeliveriesByUser(Integer idUser);

    public double latitude(String city);

    public double longitude(String city);

    public double calculateDistanceCity(String depart, String destination);

    Float findPricesByIdDelivery(long idDelivery);

    public double calculatePrice(String depart, String destination, long idDelivery);

    public void assignDriverToDelivery(Long deliveryId, Integer driverId);

    List<String> findDeliveryLocationsForDrivers();

    //public void assignDriverToDeliverybydistance(long idDelivery) ;
    public void sendSms();

    List<User> findDriversByAvailability();

    // public User getNearestAvailableDriver(Delivery delivery);
    public User getNearestAvailableDriver(long idDelivery);

    //  List<User> getNearestAvailableDrivers(long deliveryId);
    void addDeliveryweight(Integer idUser, long idDelivery, Delivery del1) throws AccessDeniedException;

    void addLocation(Integer idUser, long idTransaction, String location);

    public List<Delivery> getall();

    public List<User> assignDriversToDeliveries();
    public List<Delivery> findAllDeliveriesAssignedToDrivers();
   // public List<User> getAllAssignedDrivers();
    List<Object[]> getdeliveryaffectetodriverbyshop(/*Long id*/);
   // public Delivery addDeliverytr(Delivery delivery, Long idTransaction);
   public void addDeliveryAndAssignToLatestTransaction(String location);
    public List<Delivery> findUserDeliveryInfoByUseId(@Param("idUser") Long idUser);
}
