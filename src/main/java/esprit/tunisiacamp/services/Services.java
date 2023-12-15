package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.forum.Mots;
import esprit.tunisiacamp.repositories.MotsREpository;
import esprit.tunisiacamp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class Services implements Iservices {


    @Autowired
    UserRepository userRepository;
    @Autowired
    MotsREpository motsREpository;
    @Override
    public void supprimeruser(Integer iduser) {
        userRepository.deleteById(iduser);
    }

    @Override
    public void ajoutermots(List<Mots> mots) {
        motsREpository.saveAll(mots);
    }

    @Override
    public boolean contientMotInterdit(String ContentPost) {
        List<Mots> motsInterdits = (List<Mots>) motsREpository.findAll();
        for (Mots motInterdit : motsInterdits) {
            if (ContentPost.contains(motInterdit.getMots().toLowerCase())) {
                return true;
            }
        }
        return false;
    }





}



