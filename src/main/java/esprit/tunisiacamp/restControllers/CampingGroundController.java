package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.entities.camping.CampingGround;
import esprit.tunisiacamp.repositories.CampingGroundRepositories;
import esprit.tunisiacamp.services.ICampingGroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins ="*" )
public class CampingGroundController {
    @Autowired
    private ICampingGroundService campingGroundService;
    @Autowired
    private CampingGroundRepositories campingGroundRepositories;

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/addCamping")
    public ResponseEntity<CampingGround> ajouterCamping(@RequestBody CampingGround c, @RequestParam String captcha) {

        CampingGround camping= campingGroundService.ajouterCamping(c,captcha);
        return ResponseEntity.ok(camping);
    }
    @PutMapping("updateCamp/{id}")
    public ResponseEntity<Optional<CampingGround>> updateCampingGround(@RequestParam(value ="id")  long id, @RequestBody CampingGround campingGround,@RequestParam String captcha) {
        //Optional<CampingGround> existingCampingGround = campingGroundService.getCampingGroundById(id);
        Optional<CampingGround> campingGrou = campingGroundService.getCampingGroundById(id);

       /* if (existingCampingGround.isPresent()) {
            campingGround.setIdCampingGround(id);
            return ResponseEntity.ok(campingGroundService.ajouterCamping(campingGround,captcha));
        } else {*/
            return ResponseEntity.ok(campingGrou);
       // }
    }


    @GetMapping("getCamp")
    public List<CampingGround> getAllCampingGrounds() {
        return campingGroundService.getAllCampingGrounds();
    }

    @CrossOrigin("http://localhost:4200")
    @DeleteMapping("deleteCamp/{id}")
    public void  deleteCampingGround(@RequestParam long idCampingGround) {

        campingGroundService.deleteCampingGroundById(idCampingGround);

    }
    @CrossOrigin("http://localhost:4200")
    @GetMapping("getCampId")
    public ResponseEntity<Optional<CampingGround>> getCampingGrounds(@RequestParam long id) {
        return ResponseEntity.ok(campingGroundService.getCampingGroundById(id));
    }

    @PutMapping("AffecterCampingAdvantages")
    public String affecterCampingAuAdvantages(Long IdCamping, Long IdAdvantages){
        return campingGroundService.affecterCampingAuAdvantages(IdCamping,IdAdvantages);
    }
    @GetMapping("SerachCamp")
    public List<CampingGround> search(@RequestParam(required = false) String genre,@RequestParam(required = false) String location,@RequestParam(required = false) String name){
        return campingGroundService.search(genre,location,name);
    }
    @CrossOrigin("http://localhost:4200")
    @GetMapping("Captcha")
    public  String GenCaptcha() {
        return campingGroundService.GenCaptcha();
    }
    @GetMapping("MyCamp/{id}")
    public List<CampingGround> getMyCamp(@RequestParam long userId){
        List<CampingGround> MyCampgrounds = campingGroundService.getCampByUser(userId);
        return MyCampgrounds;

    }
    @GetMapping("Campname")
    public  String Gampname(long id) {
        return campingGroundService.Campname(id);
    }
}
