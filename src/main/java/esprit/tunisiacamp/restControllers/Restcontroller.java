package esprit.tunisiacamp.restController;

import esprit.tunisiacamp.entities.forum.Mots;
import esprit.tunisiacamp.services.Iservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Restcontroller {
    @Autowired
    Iservices iservices ;

    @DeleteMapping("Supprimeruser")
    void suppuser (@RequestParam Integer iduser){
        iservices.supprimeruser(iduser);
    }


@PostMapping("AjouterMotsInterdit")
    void ajouter(@RequestBody List<Mots>mots){
        iservices.ajoutermots(mots);
}






}
