package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.Role;
import esprit.tunisiacamp.entities.enums.role;
import esprit.tunisiacamp.entities.shopping.Delivery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DeliveryRepository extends CrudRepository<Delivery,Long> {
    @Query("SELECT t.delivery FROM Transaction t WHERE t.shopper.idUser = :idUser")
    List<Delivery> findDeliveriesByUser(long idUser);

   @Query("SELECT t.price FROM Transaction t INNER JOIN t.delivery d WHERE d.idDelivery = :idDelivery")
   Float findPricesByIdDelivery(@Param("idDelivery") Long idDelivery);

   /* @Query("SELECT d.location FROM Delivery d JOIN d.shipments t JOIN t.shopper u WHERE u.role.role = :role")
    List<String> findDeliveryLocationsForDrivers(@Param("role") role driverRole);*/

   @Query("SELECT DISTINCT u.address FROM User u WHERE u.role.role = 'DRIVER'")
   List<String> findDriverLocations();



    @Query("SELECT d FROM Delivery d JOIN FETCH d.shipments t JOIN FETCH t.shopper u WHERE u.role.role = 'DRIVER'")
    List<Delivery> findAllDeliveriesAssignedToDrivers();
   // @Query(value = "SELECT username, email, telephone, location,done, weight, delivery_date,user_id_user,delivery_list_id_delivery FROM user u JOIN user_delivery_list ud on u.id_user=ud.user_id_user join delivery d on ud.delivery_list_id_delivery=d.id_delivery",nativeQuery = true)
    @Query(value="SELECT username, email, telephone,location,done, weight, delivery_date,address,pricedelivery,delivery_list_id_delivery ,user_id_user FROM user u JOIN user_delivery_list ud on u.id_user=ud.user_id_user join delivery d on ud.delivery_list_id_delivery=d.id_delivery",nativeQuery = true)
    List<Object[]> getdeliveryaffectetodriverbyshop(/*@Param("id") Long id*/);
    @Query("SELECT d.location , d.delivery_date,d.weight FROM Delivery d WHERE d.transaction.shopper.idUser = :idUser")
    List<Delivery> findUserDeliveryInfoByUseId(@Param("idUser") Long idUser);

   @Query(value = "SELECT username, email, telephone, location,done, weight, delivery_date,user_id_user,delivery_list_id_delivery FROM user u JOIN user_delivery_list ud on u.id_user=ud.user_id_user join delivery d on ud.delivery_list_id_delivery=d.id_delivery where u.shopper_id_user =?1;",nativeQuery = true)
   List<Object[]> getDeliveriesByShopperId(Long shopperId);

}
