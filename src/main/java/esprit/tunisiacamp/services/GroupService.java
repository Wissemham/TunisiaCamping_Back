package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.camping.GroupCamp;
import esprit.tunisiacamp.entities.camping.Reservation;
import esprit.tunisiacamp.repositories.GroupRepositories;
import esprit.tunisiacamp.repositories.ReservationRepositories;
import esprit.tunisiacamp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService implements IGroupService{
    @Autowired
    GroupRepositories groupRepositories;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReservationRepositories reservationRepositories;


    @Override
    public GroupCamp saveGroup( Integer ReservationId, Integer userId) {
        GroupCamp gc = new GroupCamp();
        User user=userRepository.findById(userId).get();
        Reservation rs=reservationRepositories.findById(Long.valueOf(ReservationId)).get();
        gc.setReservation_grw(rs);
        gc.setUser_grw(user);
        gc.setEtat(false);
        groupRepositories.save(gc);

        return gc;
    }

    @Override
    public void acceptgroupe(Integer groupId) {
        GroupCamp gc = groupRepositories.findById(Long.valueOf(groupId)).get();
        gc.setEtat(true);
        groupRepositories.save(gc);
    }

    @Override
    public List<GroupCamp> getMyGroup(Integer userId) {
        return groupRepositories.findGroupByUserId(userId);
    }
}
