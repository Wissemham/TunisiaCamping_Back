package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.camping.Advantage;
import esprit.tunisiacamp.entities.camping.CampingGround;
import esprit.tunisiacamp.repositories.AdvantagesRepositories;
import esprit.tunisiacamp.repositories.CampingGroundRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CampingGroundService implements ICampingGroundService {
    @Autowired
    private CampingGroundRepositories campingGroundRepository;
    @Autowired
    private AdvantagesRepositories advantagesRepositories;

    @Override
    public CampingGround ajouterCamping(CampingGround campingGrounds,String captch) {
        //GenCaptcha();
        if (captch.equals(captcha)){
            return campingGroundRepository.save(campingGrounds);
        }
       return null;
    }

    @Override
    public List<CampingGround> getAllCampingGrounds() {
        return (List<CampingGround>) campingGroundRepository.findAll();
    }

    @Override
    public Optional<CampingGround> getCampingGroundById(long id) {
        return campingGroundRepository.findById(id);
    }

    @Override
    public void deleteCampingGroundById(long id) {
        campingGroundRepository.deleteById(id);
    }

    @Override
    public String affecterCampingAuAdvantages(Long IdCamping, Long IdAdvantages) {
        CampingGround cp = campingGroundRepository.findById(IdCamping).get();
        Advantage ad = advantagesRepositories.findById(IdAdvantages).get();
        cp.getAdvantages().add(ad);
        campingGroundRepository.save(cp);
        return null;
    }

    @Override
    public List<CampingGround> search(String genre, String location, String name) {
//            List<CampingGround> campfilter = getAllCampingGrounds().stream().filter(s -> s.getGenre().toString() == genre).filter(s -> s.getLocation().toLowerCase()==location.toLowerCase()).filter(s -> s.getName()==name).collect(Collectors.toList());
//        return campfilter.;
            return campingGroundRepository.searcCamp(genre,location,name);
    }
    public String captcha ;
    public  String GenCaptcha() {


        char data[]={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
        char index[]=new char[7];

        Random r=new Random();
        int i =0;

        for( i=0;i<(index.length);i++)
        {
            int ran=r.nextInt(data.length);
            index[i]=data[ran];
            captcha=String.valueOf(index);
           // System.out.println("captcha is"+captcha);

        }
        System.out.println("captcha is "+captcha);
        return captcha;

    }

    @Override
    public List<CampingGround> getCampByUser(long user) {
        return (List<CampingGround>) campingGroundRepository.findCampingsByUserId(user);
    }

    @Override
    public String Campname(long idcamp) {
        return campingGroundRepository.Campname(idcamp);
    }


}
