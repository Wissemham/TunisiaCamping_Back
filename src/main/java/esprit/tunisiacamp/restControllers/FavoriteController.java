package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.entities.camping.CampingGround;
import esprit.tunisiacamp.entities.camping.Favorite;
import esprit.tunisiacamp.repositories.ReservationRepositories;
import esprit.tunisiacamp.services.IFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FavoriteController {
    @Autowired
    private IFavoriteService iFavoriteService;
    @Autowired
    private ReservationRepositories reservationRepositories;

    @PostMapping("savefavorite")
    public Favorite saveFavorite(Favorite favorite) {
        return iFavoriteService.saveFavorite(favorite);      }

    @GetMapping("getAllfavorite")
    public List<Favorite> getAllFavorite() {
        return iFavoriteService.getAllFavorite();
    }

    @PutMapping("affecterfavorite")
    public void affecterFavorite(@RequestParam Integer favoriteId, @RequestParam Integer campingId, @RequestParam Integer userId){
        iFavoriteService.affecterFavorite(favoriteId,campingId,userId);
    }
    @GetMapping("MyFavorite/{id}")
    public List<CampingGround> getMyFavorite(@RequestParam Integer userId){
        List<CampingGround> favoriteCampgrounds = iFavoriteService.getMyFavorite(userId);
        return favoriteCampgrounds;

    }

    @DeleteMapping("deletFavorite/{id}")
    public void deleteFavoriteById(@RequestParam long id) {
        iFavoriteService.deleteFavoriteById(id);

    }
    @GetMapping("isfavorite")
    public String isFavorite(@RequestParam long id, @RequestParam long user) {
        String p="2";
         if (iFavoriteService.isFavorite(id,user)==true){
             p="1";
         };
         System.out.println(p);
         return p;
    }
    @GetMapping("favoriteid")
    public String Favoriteid(@RequestParam long id, @RequestParam long user) {
       return iFavoriteService.favoriteid(id,user);
    }
}
