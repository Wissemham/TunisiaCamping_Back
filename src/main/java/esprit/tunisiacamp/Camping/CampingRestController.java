package esprit.tunisiacamp.Camping;

import esprit.tunisiacamp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CampingRestController {
    @Autowired
    CampingIService campingIService;
    //@CrossOrigin("http://localhost:4200")
    @GetMapping("/AllCamp")
    @PreAuthorize("hasAnyAuthority('CAMPER')")
    public List<Camping> listCamping(){
        return campingIService.AllCamp();

    }

    @PreAuthorize("hasAnyAuthority('CAMPER')")
    @PostMapping("/addCamping/{id}")
    public  void  addCamping(@RequestBody Camping c, HttpServletRequest request, Model model,@PathVariable("id") Integer id){
        campingIService.addCamping(c,request,model,id);
        //return null;
    }
    @PreAuthorize("hasAnyAuthority('CAMPER')")
    @GetMapping("/searchCamp")
    public List<Camping> searsh(){
        return campingIService.searchCamping();
    }
    //@PreAuthorize("hasAnyAuthority('CAMPER')")
    @PutMapping("/reserveCamp/{idcrea}/{idCam}")
    public String reserveCamp(@PathVariable("idcrea") Integer idCreateur,@PathVariable("idCam") Integer IdgroupCamp){
        return campingIService.reserveCamp(idCreateur, IdgroupCamp);
    }
    @GetMapping("/mesgroupe/{id}")
    public List<User> mesgroupe(@PathVariable("id") Integer IdGroup){
        return campingIService.mesreservation(IdGroup);
    }
    @GetMapping("/groubeByUser/{id}")
    public List<Camping> groupebyuser(@PathVariable("id") Integer id){
        return campingIService.mesGroupes(id);
    }
    @DeleteMapping("/deletegroupe/{id}")
    public void deletegroupe(@PathVariable("id") Integer id){
        campingIService.deleteGroupe(id);
    }
}
