package esprit.tunisiacamp.restController;

import esprit.tunisiacamp.entities.forum.Reponse;
import esprit.tunisiacamp.services.IServiceReponseCommentaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GestionReponseCommentaire {


    @Autowired
    IServiceReponseCommentaire iServiceReponseCommentaire ;

    @PutMapping("repondreAunCommentaire")
    void repondre(@RequestBody Reponse r, @RequestParam long idCom, @RequestParam Integer idUser ){
        iServiceReponseCommentaire.repondreauncommentaire(r  ,idCom , idUser);
    }
}
