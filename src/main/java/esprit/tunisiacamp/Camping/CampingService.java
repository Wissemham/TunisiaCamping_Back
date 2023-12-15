package esprit.tunisiacamp.Camping;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.repositories.UserRepository;
//import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CampingService implements CampingIService{

    @Autowired
    CampingRepository campingRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public List<Camping> AllCamp() {
        return (List<Camping>) campingRepository.findAll();
    }

    @Override
    public void addCamping(Camping c, HttpServletRequest request, Model model,Integer id) {

        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");;
       // model.addAttribute("idUser",id);
       // System.out.println(user);
        User u = userRepository.findById(id).get();
        c.setCreateur(u);
        campingRepository.save(c);

    }

    @Override
    public List<Camping> searchCamping() {
        List<Camping> c = (List<Camping>) campingRepository.findAll();
        List<Camping> c1 =  new ArrayList<>();
        for (Camping c2 : c){
            if(c2.getCheckInDate().after(new Date())){
                c1.add(c2);
            }
        }
        return c1;
    }

    @Override
    public String reserveCamp(Integer idCreateur, Integer idCamp) {
        Camping c = campingRepository.findById(idCamp).get();
        User u = userRepository.findById(idCreateur).get();
        {

            ArrayList<User> user = new ArrayList<>();
            user.add(u);
            c.setListgroup(user);

            c.setGroupSize(c.getGroupSize() - 1);
            campingRepository.save(c);
            u.setCampingGroupe(c);
            userRepository.save(u);

            return "successfully reservation";
        }
    }

    @Override
    public List<User> mesreservation(Integer idGroup) {
        Camping c = campingRepository.findById(idGroup).get();
        ArrayList<User> myList = new ArrayList<>();
        System.out.println(c.getListgroup().size());
       // for (int i=0;i<c.getListgroup().size();i++){
           // User u =userRepository.findById(c.getListgroup().get());
         //   myList.add((User) c.getListgroup());
      // }

        return c.getListgroup();
    }

    @Override
    public List<Camping> mesGroupes(Integer idUser) {
        //System.out.println(idUser);
        List<Camping> userArrayList = new ArrayList<>();
        List<Camping> c = (List<Camping>) campingRepository.findAll();
        for (Camping ca : c){
            if(ca.getCreateur().getIdUser() == idUser){
                userArrayList.add(ca);
            }
        }
        return userArrayList;
    }

    @Override
    public void deleteGroupe(Integer id) {
        campingRepository.deleteById(id);

    }


}
