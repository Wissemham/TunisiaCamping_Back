package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.entities.Role;
import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.shopping.Delivery;
import esprit.tunisiacamp.services.IDeliveryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name="Manager of Delivery")
@RequestMapping("/api/claims")
@CrossOrigin(origins ="*")
public class DeliveryRestController {
    @Autowired
    IDeliveryService iDeliveryService;

    @PostMapping("/addDelivery")
    public void addDelivery(@RequestBody Delivery del) {
        iDeliveryService.addDelivery(del);
    }
    @GetMapping("/getdelivery")
    public List<Delivery> findDeliveriesByUser(Integer idUser){
        return iDeliveryService.findDeliveriesByUser(idUser);
    }
    @GetMapping("/retriveprice")
    public Float findPricesByIdDelivery(long idDelivery) {
        return iDeliveryService.findPricesByIdDelivery(idDelivery);
    }

    @PostMapping("/calculatePrice")
    public double calculatePrice(@RequestParam String depart,@RequestParam String destination,@RequestParam long idDelivery){
        double P=iDeliveryService.calculatePrice(depart,destination,idDelivery);
        return P;
    }
    @PutMapping("/affecter")
    public void assignDriverToDelivery(Long deliveryId, Integer driverId){
         iDeliveryService.assignDriverToDelivery(deliveryId,driverId);
    }
    /*@GetMapping("/listdriverlocation")
    public  List<String> findDeliveryLocationsForDrivers() {
        return iDeliveryService.findDeliveryLocationsForDrivers();
    }*/
    /*@PutMapping("/affecterdriver")
    public void assignDriverToDelivery(long idDelivery) {
        iDeliveryService.assignDriverToDeliverybydistance(idDelivery);
    }*/
    @GetMapping("/findavailabeldriver")
    public List<User> findDriversByAvailability(){
        return iDeliveryService.findDriversByAvailability();
    }
    @GetMapping("/findNearestdriverfromdelivery")
    public User getNearestAvailableDriver(@RequestParam long idDelivery){
        return iDeliveryService.getNearestAvailableDriver(idDelivery);
    }
   /* @GetMapping("/list")
    public List<User> getNearestAvailableDrivers(long deliveryId){
        return iDeliveryService.getNearestAvailableDrivers(deliveryId);
    }*/
    @PutMapping ("/addweight")
    public void addDeliveryweight(@RequestParam Integer idUser, @RequestParam long idDelivery,@RequestBody Delivery del1)throws AccessDeniedException {
        iDeliveryService.addDeliveryweight(idUser,idDelivery,del1);
    }
    @PostMapping("/addlocationdelivery")
    void addLocation(@RequestParam Integer idUser,@RequestParam long idTransaction,@RequestParam String location){
        iDeliveryService.addLocation(idUser,idTransaction,location);
    }
    @GetMapping("/getalldelivery")
    public List<Delivery> getall() {
        return iDeliveryService.getall();
    }
    @GetMapping("/alldeliveryaffectetodriver")
    public List<User> assignDriversToDeliveries(){
        return iDeliveryService.assignDriversToDeliveries();
    }
    /*@GetMapping("/get")
    public List<Delivery> findAllDeliveriesAssignedToDrivers(){
        return iDeliveryService.findAllDeliveriesAssignedToDrivers();
    }
    @GetMapping("/dddd")
    public List<User> getAllAssignedDrivers(){
        return iDeliveryService.getAllAssignedDrivers();
    }*/
    @GetMapping("/deliveryaffectetodriverbyshop")
   public List<Object[]> getdeliveryaffectetodriverbyshop(){
        return iDeliveryService.getdeliveryaffectetodriverbyshop();
    }
   /* @PostMapping("/addTransttodelivery")
    public Delivery addDeliverytr(Delivery delivery,Long idTransaction){
        return iDeliveryService.addDeliverytr(delivery,idTransaction);
    }*/
    @PostMapping("/adddeliveryandaffectertransaction")
    public void addDeliveryAndAssignToLatestTransaction(@RequestParam String location){
        iDeliveryService.addDeliveryAndAssignToLatestTransaction(location);
    }
     @GetMapping("/getdeliverybyUser")
     public List<Delivery> findUserDeliveryInfoByUseId(@RequestParam Integer idUser){
        return iDeliveryService.findDeliveriesByUser(idUser);
     }
}
