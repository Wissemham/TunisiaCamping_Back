package esprit.tunisiacamp.restController;

import esprit.tunisiacamp.entities.forum.ChatRoom;
import esprit.tunisiacamp.services.IserviceRomm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GestionDesRoom {
@Autowired
    IserviceRomm iserviceRomm ;

    @PostMapping("AjouteChatroom")
    public ChatRoom room (@RequestBody ChatRoom room ){
        return    iserviceRomm.ajoutroom(room);
    }
    @PutMapping("AjouterUserRoom")
    void userroom (@RequestParam Integer idUser , @RequestParam long idroom)
    {

        iserviceRomm.affecteruseraroom(idUser, idroom);
    }
    @PostMapping("ModifierRoom")
    void modifroom(@RequestBody ChatRoom room){
        iserviceRomm.midifroom(room);
    }

    @DeleteMapping("SupprimerRoom")
    void supprimerRoom(@RequestParam  long idroom){
        iserviceRomm.suprimerroom(idroom);
    }
}
