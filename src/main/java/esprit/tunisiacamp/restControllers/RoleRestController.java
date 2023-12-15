package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.entities.Role;
import esprit.tunisiacamp.services.RoleIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleRestController {
    @Autowired
    RoleIService roleIService;
    @PostMapping("/addRole")
    public void addRole(@RequestBody Role r){
        roleIService.addRole(r);
    }
    @GetMapping("/getRoleById")
    public Role getRoleById(long id){
        return roleIService.findRoleById(id);
    }
    @PutMapping("/updateRole")
    public void updateRole(@RequestBody Role role){
        roleIService.updateRole(role);
    }
    @DeleteMapping("/deleteRole")
    public void deleteRole(long id){
        roleIService.deleteRole(id);
    }
}
