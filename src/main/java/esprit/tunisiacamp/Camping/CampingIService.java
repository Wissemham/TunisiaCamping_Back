package esprit.tunisiacamp.Camping;

import esprit.tunisiacamp.entities.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

public interface CampingIService {

    public List<Camping> AllCamp();
    public void addCamping(Camping c, HttpServletRequest request, Model model,Integer id);
    public List<Camping> searchCamping();
    public String reserveCamp(Integer idCreateur,Integer idCamp);
    public List<User> mesreservation(Integer idGroup);
    public List<Camping> mesGroupes(Integer idUser);
    public void deleteGroupe(Integer id);
}
