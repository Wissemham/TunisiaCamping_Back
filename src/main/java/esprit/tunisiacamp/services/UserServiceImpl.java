package esprit.tunisiacamp.services;


import esprit.tunisiacamp.entities.model.Userr;

import esprit.tunisiacamp.repository.UserRepositoryy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserServiceImp{

    @Autowired
    private UserRepositoryy userRepositoryy;


    @Override
    public List<Userr> getall()  {
        List<Userr> userrs = userRepositoryy.findAll();

           return userrs;

    }

    @Override
    public Userr addUser(Userr userr) {

           return userRepositoryy.save(userr);

    }

    @Override
    public HashSet<Userr> getUserByUserName(String username)  {
        HashSet<Userr> userr1 = userRepositoryy.findByusername(username);
            return userr1;

    }

}
