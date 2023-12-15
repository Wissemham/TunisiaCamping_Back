package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.model.Userr;

import java.util.HashSet;
import java.util.List;

public interface UserServiceImp {
    List<Userr> getall() ;

    Userr addUser(Userr userr) ;

    HashSet<Userr> getUserByUserName(String username)  ;
}
