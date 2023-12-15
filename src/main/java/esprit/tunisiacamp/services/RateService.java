package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.camping.CampingGround;
import esprit.tunisiacamp.entities.camping.Rate;
import esprit.tunisiacamp.repositories.CampingGroundRepositories;
import esprit.tunisiacamp.repositories.RateRepository;
import esprit.tunisiacamp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateService implements IRateService{
    @Autowired
    RateRepository rtingRepository;
    @Autowired
    CampingGroundRepositories campingGroundRepositories;
    @Autowired
    UserRepository userRepository;


    @Override
    public void likerCamp(long idCamp, long idUser) {
        Rate r=new Rate();
        CampingGround camp = campingGroundRepositories.findById(idCamp).get();
        User user = userRepository.findById((int)idUser).get();
        if (chercheuser((int) idUser, (int) idCamp) == false){
            r.setCamp_rate(camp);
            r.setUser(user);

            rtingRepository.save(r);
        }else {
        System.out.println("Deja rate");
    }}

    @Override
    public List<Rate> getLikes() {
        return (List<Rate>) rtingRepository.findAll();
    }

    @Override
    public void deletelike(long idlike) {
        rtingRepository.deleteById(idlike);
    }

    @Override
    public void modifierrate(Rate rating) {

    }

    @Override
    public boolean chercheuser(Integer iduser, Integer idcamp) {
        List<Rate> rate = (List<Rate>) rtingRepository.findAll();
        for (Rate r :rate){
            if (r.getUser().getIdUser() == iduser && r.getCamp_rate().getIdCampingGround() == idcamp){
                return true;
            }
    }return false;
    }

    @Override
    public int likenumber(Integer idcamp) {
        List<Rate> rate = (List<Rate>) rtingRepository.findAll();
        int s=0;
        for (Rate r :rate){
            if (r.getCamp_rate().getIdCampingGround() == idcamp){
                s=s+1;
            }
        }
        return s;
    }

    @Override
    public boolean isLike(long id, long user) {
        List<Rate> rate = (List<Rate>) rtingRepository.findAll();
        for (Rate r :rate){
            if (r.getUser().getIdUser() == user && r.getCamp_rate().getIdCampingGround() == id){
                return true;
            }
        }return false;
    }

    @Override
    public String likeid(long id, long user) {
        return rtingRepository.rateid(id,user);
    }

}
