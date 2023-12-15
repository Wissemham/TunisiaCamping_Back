package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.Autority;
import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.enums.State;
import esprit.tunisiacamp.entities.enums.role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    @Transactional
    @Modifying
    @Query("update User  set enable=false , state=?2  where idUser=?1")
    void deleteUser(Integer id, State state);
    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    public User findByVerificationCode(String code);

    @Query("SELECT u FROM User u WHERE u.email = :username")
    public User getUserByUsername(@Param("username") String username);

    @Query("select u from User u")
    public List<User> allUser();


    @Query("SELECT u FROM User u where u.verifiepwd=:code")
    public User getUserByVerifiepwd(@Param("code") String code);

    @Query("SELECT u FROM User u WHERE u.verifiepwd=:cd")
    public User getUserCD(@Param("cd") String code);

    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);

    @Query(value = "SELECT username, email, telephone, location,done, weight, delivery_date,user_id_user,delivery_list_id_delivery FROM user u JOIN user_delivery_list ud on u.id_user=ud.user_id_user join delivery d on ud.delivery_list_id_delivery=d.id_delivery where u.shopper_id_user =?1;",nativeQuery = true)
    List<Object[]> getDeliveriesByShopperId(Integer shopperId);
    @Query("SELECT u FROM User u JOIN u.role r WHERE r.role = 'DRIVER' AND u.availability = true")
    List<User> findDriversByAvailability();
    List<User> findByAddressAndRoleRole(String address, role role);
    //List<User> findByRole_RoleAndDeliveryIsNotNull(Role role);



    
}
