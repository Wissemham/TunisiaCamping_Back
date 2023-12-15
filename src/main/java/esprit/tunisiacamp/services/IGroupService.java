package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.camping.GroupCamp;

import java.util.List;

public interface IGroupService {
    GroupCamp saveGroup(Integer ReservationId,Integer userId);
    void acceptgroupe(Integer groupId);
    List<GroupCamp> getMyGroup(Integer userId);
}
