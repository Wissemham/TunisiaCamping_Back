package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.Role;
import esprit.tunisiacamp.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class RoleService implements RoleIService{
    @Autowired
    RoleRepository roleRepository;

    @Override
    public void addRole(Role role){
        roleRepository.save(role);
    }
    @Override
    public Role findRoleById(long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public void updateRole(@RequestBody Role role) {
        roleRepository.save(role);
    }

    @Override
    public void deleteRole(long id) {
        roleRepository.deleteById(id);
    }
}
