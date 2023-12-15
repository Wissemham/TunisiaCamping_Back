package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.entities.camping.GroupCamp;
import esprit.tunisiacamp.services.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupController {
    @Autowired
    IGroupService iGroupService;
    @PutMapping("/acceptgroup")
    public void acceptgroupe(@RequestParam Integer groupId){
        iGroupService.acceptgroupe(groupId);
    }
    @GetMapping("/getmygroup")
    public List<GroupCamp> getMyGroup(@RequestParam Integer userId){
        return iGroupService.getMyGroup(userId);
    }
}
