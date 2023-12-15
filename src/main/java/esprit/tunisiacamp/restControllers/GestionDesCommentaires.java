package esprit.tunisiacamp.restController;

import esprit.tunisiacamp.entities.forum.Comment;
import esprit.tunisiacamp.services.IserviceCommentaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GestionDesCommentaires {

        @Autowired
    IserviceCommentaire iserviceCommentaire ;

    @PutMapping("AjouterCommentaire")
    String ajoutcomment (@RequestBody Comment m, @RequestParam long idPost, @RequestParam Integer idUser){


        if (iserviceCommentaire.contientMotInterdit(m.getContentComment().toLowerCase())) {
            return ("votre commentaire  contient un mot interdit. " );
        }




        iserviceCommentaire.ajoutercommentare(m,idPost,idUser);

        return "votre commentaire est ajouter avec succes" ;




    }

    @GetMapping("Affichercommentaires")
     List<Comment> getcomment (){
        return iserviceCommentaire.getComments();
    }

    @DeleteMapping("SupprimerCommenteire")
    void suppcomment (@RequestParam long idcomment){
        iserviceCommentaire.supprimercommentaire(idcomment);
    }

    @PostMapping("ModifierCommentaire")
    void modifcomment(@RequestBody Comment commentaire){
        iserviceCommentaire.modifierCommentaire( commentaire );
    }
}
