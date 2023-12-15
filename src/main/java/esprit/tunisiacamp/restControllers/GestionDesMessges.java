package esprit.tunisiacamp.restController;

import esprit.tunisiacamp.entities.forum.Messagee;
import esprit.tunisiacamp.services.IserviceMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GestionDesMessges {
   @Autowired
    IserviceMessage iserviceMessage ;

    @PutMapping("EnvoyerMessage")
    void ajoutermessage(@RequestBody Messagee m, @RequestParam Integer idUser, @RequestParam long chatroom ){
        iserviceMessage.ajouterMessage(m, idUser ,chatroom); ;
    }
    @GetMapping("AfficherMessages")
    public List<Messagee> getmsg (){
        return iserviceMessage.getmessages();
    }
    @DeleteMapping("SupprimerMsg")
    void suppmessage (@RequestParam long idmsg){
        iserviceMessage.supprimermessage(idmsg);
    }
}
