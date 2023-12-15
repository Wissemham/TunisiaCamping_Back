package esprit.tunisiacamp.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.enums.role;
import esprit.tunisiacamp.entities.shopping.Delivery;
import esprit.tunisiacamp.entities.shopping.Transaction;
import esprit.tunisiacamp.repositories.DeliveryRepository;
import esprit.tunisiacamp.repositories.TransactionRepository;
import esprit.tunisiacamp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;


import java.nio.file.AccessDeniedException;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DeliveryService implements IDeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TransactionRepository transactionRepository;
    private final String ACCOUNT_SID = "AC25cdc830efc26a4310a2a74ddf18c1e9";
    private final String AUTH_TOKEN = "efa128f2feb4fadd50a62768e8f90c05";
    // private final String FROM_NUMBER = "+15676543692";
    @Override
    public void sendSms() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21624591154"),
                new com.twilio.type.PhoneNumber("+15676543692"),
                "Reminder: You have a delivery is tomorrow.").create();
    }
    @Override
    public void addDelivery(Delivery del) {
      deliveryRepository.save(del);
    }

    @Override
    public List<Delivery> findDeliveriesByUser(Integer idUser) {

        return deliveryRepository.findDeliveriesByUser(idUser) ;
    }

    public double latitude(String city) {
        double lat=0;
        String[] Cities = new String[24];
        double[] Latitudes = new double[24];
        Latitudes[0]=36.8665;
        Latitudes[1]=36.7333;
        Latitudes[2]=36.7435;
        Latitudes[3]=37.2768;
        Latitudes[4]=33.8881;
        Latitudes[5]=34.4311;
        Latitudes[6]=36.5072;
        Latitudes[7]=35.6712;
        Latitudes[8]=35.1723;
        Latitudes[9]=33.7072;
        Latitudes[10]=36.1680;
        Latitudes[11]=35.5024;
        Latitudes[12]=36.8093;
        Latitudes[13]=33.3399;
        Latitudes[14]=35.7643;
        Latitudes[15]=36.4513;
        Latitudes[16]=34.7398;
        Latitudes[17]=35.0354;
        Latitudes[18]=36.0887;
        Latitudes[19]=35.8245;
        Latitudes[20]=32.9211;
        Latitudes[21]=33.9185;
        Latitudes[22]=36.8065;
        Latitudes[23]=36.4091;
        Cities[0] = "Ariana";
        Cities[1] = "Beja";
        Cities[2] = "Ben Arous";
        Cities[3] = "Bizerte";
        Cities[4] = "Gabes";
        Cities[5] = "Gafsa";
        Cities[6] = "Jendouba";
        Cities[7] = "Kairouan";
        Cities[8] = "Kassrine";
        Cities[9] = "Kebili";
        Cities[10] = "Kef";
        Cities[11] = "Mahdia";
        Cities[12] = "Manouba";
        Cities[13] = "Mednine";
        Cities[14] = "Monastir";
        Cities[15] = "Nabeul";
        Cities[16] = "Sfax";
        Cities[17] = "Sidi Bouzid";
        Cities[18] = "Siliana";
        Cities[19] = "Sousse";
        Cities[20] = "Tataouine";
        Cities[21] = "Tozeur";
        Cities[22] = "Tunis";
        Cities[23] = "Zaghouan";
        for (int i = 0; i < 24; i++) {
            if (Cities[i].equals(city))
                lat = Latitudes[i];
        }
        return lat;
    }
    public double longitude(String city){
        double lon = 0;
        String[] Cities = new String[24];
        Cities[0] = "Ariana";
        Cities[1] = "Beja";
        Cities[2] = "Ben Arous";
        Cities[3] = "Bizerte";
        Cities[4] = "Gabes";
        Cities[5] = "Gafsa";
        Cities[6] = "Jendouba";
        Cities[7] = "Kairouan";
        Cities[8] = "Kassrine";
        Cities[9] = "Kebili";
        Cities[10] = "Kef";
        Cities[11] = "Mahdia";
        Cities[12] = "Manouba";
        Cities[13] = "Mednine";
        Cities[14] = "Monastir";
        Cities[15] = "Nabeul";
        Cities[16] = "Sfax";
        Cities[17] = "Sidi Bouzid";
        Cities[18] = "Siliana";
        Cities[19] = "Sousse";
        Cities[20] = "Tataouine";
        Cities[21] = "Tozeur";
        Cities[22] = "Tunis";
        Cities[23] = "Zaghouan";
        double[] longitudes = new double[24];
        longitudes[0]=10.1647;
        longitudes[1]=9.1844;
        longitudes[2]=10.2320;
        longitudes[3]=9.8642;
        longitudes[4]=10.0975;
        longitudes[5]=8.7757;
        longitudes[6]=8.7757;
        longitudes[7]=10.1005;
        longitudes[8]=8.8308;
        longitudes[9]=8.9715;
        longitudes[10]=8.7096;
        longitudes[11]=11.0457;
        longitudes[12]=10.0863;
        longitudes[13]=10.4959;
        longitudes[14]=10.8113;
        longitudes[15]=10.7357;
        longitudes[16]=10.7600;
        longitudes[17]=9.4839;
        longitudes[18]=9.3645;
        longitudes[19]=10.6346;
        longitudes[20]=10.4509;
        longitudes[21]=8.1229;
        longitudes[22]=10.1815;
        longitudes[23]=10.1423;
        for (int i = 0; i < 24; i++) {
            if (Cities[i].equals(city))
                lon = longitudes[i];
        }
        return lon;
    }
    public double calculateDistanceCity(String depart,String destination) {
        double lat1=Math.toRadians(latitude(depart));
        double long1=Math.toRadians(longitude(depart));
        double lat2=Math.toRadians(latitude(destination));
        double long2=Math.toRadians(longitude(destination));
        double dLat = lat2 - lat1;
        double dLong = long2 - long1;
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLong/2) * Math.sin(dLong/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double R = 6371;
        double distance = R * c;
        return distance;
    }



    @Override
    public double calculatePrice(String depart,String destination,long idDelivery){
        double pricedelivery =deliveryRepository.findPricesByIdDelivery(idDelivery);
        Delivery delivery=deliveryRepository.findById(idDelivery).get();
        double dist = calculateDistanceCity(depart,destination);
        if(dist>10 && dist<150) {
            pricedelivery+=dist*0.086;
        }
        else if (dist>150) {
            pricedelivery+=dist*0.071;
        }
        else
            pricedelivery+=0.850;
         //Delivery delivery = new Delivery();
        delivery.setPricedelivery(pricedelivery);
        deliveryRepository.save(delivery);
        return pricedelivery;
    }
    @Override
    public List<String> findDeliveryLocationsForDrivers() {
        return deliveryRepository.findDriverLocations();
    }
    @Override
    public void assignDriverToDelivery(Long deliveryId, Integer driverId) {
        User driver = userRepository.findById(driverId).get();
        if (driver != null && driver.getRole().getRole() == role.DRIVER) {
            Delivery delivery = deliveryRepository.findById(deliveryId).get();
            driver.getDeliveryList().add(delivery);
            deliveryRepository.save(delivery);
            sendSms();
        }
    }



    @Override
    public List<User> findDriversByAvailability(){

        return userRepository.findDriversByAvailability();
    }
    @Override
    public User getNearestAvailableDriver(long deliveryId) {

        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid delivery ID"));
       /* User assignedDriver = (User) delivery.getTransaction().getShopper().getDeliveryList();
        if (assignedDriver != null) {
            // Delivery is already assigned to a driver, return the assigned driver
            return assignedDriver;
        }*/
        List<User> availableDrivers = userRepository.findDriversByAvailability();
        if (availableDrivers.isEmpty()) {
            throw new IllegalStateException("No available drivers found");
        }
        Map<User, Double> driverDistances = new HashMap<>();
        for (User driver : availableDrivers) {
            double distance = calculateDistanceCity(driver.getAddress(), delivery.getLocation());
            driverDistances.put(driver, distance);
        }
        List<User> sortedDrivers = driverDistances.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        User assignedDriver = sortedDrivers.get(0);
        //delivery.setAssignedDriver(assignedDriver);
       // assignedDriver.setAvailability(false);
        assignedDriver.getDeliveryList().add(delivery);
        deliveryRepository.save(delivery);
        userRepository.save(assignedDriver);
       // sendSms();
        return assignedDriver;

       /* Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid delivery ID"));

        List<User> availableDrivers = userRepository.findDriversByAvailability();
        if (availableDrivers.isEmpty()) {
            throw new IllegalStateException("No available drivers found");
        }

        Map<User, Double> driverDistances = new HashMap<>();
        for (User driver : availableDrivers) {
            double distance = calculateDistanceCity(driver.getAddress(), delivery.getLocation());
            driverDistances.put(driver, distance);
        }

        List<User> sortedDrivers = driverDistances.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        User assignedDriver = sortedDrivers.get(0);
        if (!assignedDriver.getDeliveryList().contains(delivery)) {
            assignedDriver.getDeliveryList().add(delivery);
            userRepository.save(assignedDriver);
        }

        return assignedDriver;*/

    }
    @Override
    public Float findPricesByIdDelivery(long idDelivery) {
        return deliveryRepository.findPricesByIdDelivery(idDelivery);
    }
   /* @Override
    public List<User> getNearestAvailableDrivers(long deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
               .orElseThrow(() -> new IllegalArgumentException("Invalid delivery ID"));
        List<User> availableDrivers = userRepository.findDriversByAvailability();
        if (availableDrivers.isEmpty()) {
            throw new IllegalStateException("No available drivers found");
        }
        Map<User, Double> driverDistances = new HashMap<>();
        for (User driver : availableDrivers) {
            double distance = calculateDistanceCity(driver.getAddress(), delivery.getLocation());
            driverDistances.put(driver, distance);
        }
        List<User> sortedDrivers = driverDistances.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return sortedDrivers;
    }*/
    @Override
    public void addDeliveryweight(Integer idUser, long idDelivery,Delivery del1) throws AccessDeniedException {
        User user = userRepository.findById(idUser).get();
        Delivery delivery = deliveryRepository.findById(idDelivery).get();
        if (user.getRole().getRole() != role.SHOP) {
            throw new AccessDeniedException("Only SHOP users can add the weight and done for the delivery.");
        }
            delivery.setWeight(del1.getWeight());
            delivery.setDone(del1.getDone());
            deliveryRepository.save(delivery);
        }
        @Override
    public void addLocation(Integer idUser, long idTransaction, String location) {

        User user = userRepository.findById(idUser).get();
        Transaction transaction = transactionRepository.findById(idTransaction).get();
        Delivery delivery = transaction.getDelivery();

        delivery.setLocation(location);
        delivery.setDelivery_date(new Date());
        deliveryRepository.save(delivery);
    }
    @Override
    public List<Delivery> getall() {
        return (List<Delivery>) deliveryRepository.findAll();
    }

    @Override
    public List<User> assignDriversToDeliveries() {
        List<Delivery> deliveries = (List<Delivery>) deliveryRepository.findAll();
        List<User> assignedDrivers = new ArrayList<>();
        if (deliveries.isEmpty()) {
            return assignedDrivers;
        }
        for (Delivery delivery : deliveries) {
            if (assignedDrivers.size() == deliveries.size()) {
                return assignedDrivers;
            }
            User assignedDriver = getNearestAvailableDriver(delivery.getIdDelivery());
            if (assignedDriver != null) {
                assignedDrivers.add(assignedDriver);
                //return assignedDrivers;
            }
        }
        return assignedDrivers;
    }
    @Override
    public List<Delivery> findAllDeliveriesAssignedToDrivers() {
        return deliveryRepository.findAllDeliveriesAssignedToDrivers();
    }
    /*@Override
    public List<User> getAllAssignedDrivers() {
        List<Delivery> deliveries = (List<Delivery>) deliveryRepository.findAll();
        List<User> assignedDrivers = new ArrayList<>();

        for (Delivery delivery : deliveries) {
            List<Transaction> transactions = delivery.getShipments();
            for (Transaction transaction : transactions) {
                User driver = transaction.getShopper();
                if (driver != null && driver.getRole().getRole() == role.DRIVER) {
                    assignedDrivers.add(driver);
                }
            }
        }

        return assignedDrivers;
    }*/
        @Override
       public List<Object[]>getdeliveryaffectetodriverbyshop(/*Long id*/){
        return deliveryRepository.getdeliveryaffectetodriverbyshop(/*id*/);
    }
   /* @Override
    public Delivery addDeliverytr(Delivery delivery, Long idTransaction) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(idTransaction);
        if (optionalTransaction.isPresent()) {
            Transaction transaction = optionalTransaction.get();
            delivery.setTransaction(transaction);
            delivery.setLocation(delivery.getLocation());
            return deliveryRepository.save(delivery);
        } else {
            throw new EntityNotFoundException("Transaction with id " + idTransaction + " not found.");
        }
    }*/
   public Long getLatestTransactionId() {
       List<Transaction> transactions = transactionRepository.findAll(Sort.by(Sort.Direction.DESC, "idTransaction"));
       if (transactions.isEmpty()) {
           return null;
       } else {
           return transactions.get(0).getIdTransaction();
       }
   }
   @Override
    public void addDeliveryAndAssignToLatestTransaction(String location) {
        Long latestTransactionId = getLatestTransactionId();
        if (latestTransactionId != null) {
            Transaction latestTransaction = transactionRepository.findById(latestTransactionId).orElse(null);
            if (latestTransaction != null) {
                if (latestTransaction.getDelivery() != null) {
                    // There is already a delivery, do not add a new one
                    return;
                }
               /*delivery.setTransaction(latestTransaction);
                deliveryRepository.save(delivery);*/
                Delivery delivery = new Delivery();
                delivery.setLocation(location);
                delivery.setDone(false);
                delivery.setDelivery_date(new Date());
                delivery.setTransaction(latestTransaction);
                deliveryRepository.save(delivery);
                latestTransaction.setDelivery(delivery);
                transactionRepository.save(latestTransaction);
            }
        }
    }

    @Override
    public List<Delivery> findUserDeliveryInfoByUseId(Long idUser) {
        return deliveryRepository.findUserDeliveryInfoByUseId(idUser);
    }


}

