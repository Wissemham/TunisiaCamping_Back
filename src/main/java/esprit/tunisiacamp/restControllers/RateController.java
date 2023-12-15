package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.entities.camping.Rate;
import esprit.tunisiacamp.services.IRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RateController {
    @Autowired
    IRateService iRateService;

    @PostMapping("LikerCamp")
    void  likerpost (@RequestParam long idCamp, @RequestParam long idUser){
        iRateService.likerCamp(idCamp,idUser);
    }
    @GetMapping("AfficheRate")
    public List<Rate> getlikess (){
        return iRateService.getLikes();
    }

    @DeleteMapping("SupprimerLike")
    void supprimerlike(@RequestParam long idlike ){
        iRateService.deletelike(idlike);
    }
    @GetMapping("nlike")
    public int likenumber(@RequestParam Integer idcamp) {
    return iRateService.likenumber(idcamp);
    }
    @GetMapping("isLike")
    public String isLike(@RequestParam long id, @RequestParam long user) {
        String p="2";
        if (iRateService.isLike(id,user)==true){
            p="1";
        };
        System.out.println(p);
        return p;
    }
    @GetMapping("rateId")
    public String likeid(long id, long user) {
        return iRateService.likeid(id,user);
    }
}
