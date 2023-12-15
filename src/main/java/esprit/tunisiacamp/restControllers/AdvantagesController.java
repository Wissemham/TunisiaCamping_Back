package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.entities.camping.Advantage;
import esprit.tunisiacamp.services.IAdvantageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class AdvantagesController {
    @Autowired
    private IAdvantageService advantageService;
    @PostMapping("saveAdvantages")
    public Advantage saveAdvantage(@RequestBody Advantage advantage) {
        return advantageService.saveAdvantage(advantage);
    }
    @CrossOrigin("http://localhost:4200")
    @GetMapping("getallAdvantages")
    public List<Advantage> getAllAdvantage(@RequestParam String type) {
        return advantageService.getAllAdvantage(type);
    }

    @DeleteMapping("deleteadvantages/{id}")
    public void deleteAdvantageById(@RequestParam long id) {
        advantageService.deleteAdvantageById(id);
    }

    @PutMapping("affecterAdvantage")
    public void affecterAdvantage(@RequestParam Integer advantId, @RequestParam Integer campingId){
        advantageService.affecterFavorite(advantId,campingId);
    }
    @GetMapping("CampAdvantage")
    public List<Advantage> CampAdvantage(long idcamp) {
        return advantageService.CampAdvantage(idcamp);
    }
}
