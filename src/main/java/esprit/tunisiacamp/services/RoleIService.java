package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.Role;

public interface RoleIService {

    void addRole(Role r);
    Role findRoleById(long id);
    void updateRole(Role role);
    void deleteRole(long id);
}
