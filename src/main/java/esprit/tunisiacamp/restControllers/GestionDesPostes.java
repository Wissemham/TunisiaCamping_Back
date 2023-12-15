package esprit.tunisiacamp.restController;

import esprit.tunisiacamp.entities.forum.Comment;
import esprit.tunisiacamp.entities.forum.Post;
import esprit.tunisiacamp.services.IservicePoste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class GestionDesPostes {

@Autowired
    IservicePoste iservicePoste  ;

    @PutMapping("AjouterPost")
    String ajouterpost(@RequestBody Post p, @RequestParam Integer idUser){
      //  String[] interdit = {"non", "oui"};
        /*
        for (String word : interdit) {
            if (p.getContentPost().toLowerCase().contains(word.toLowerCase())) {
            return "Votre poste nest pas approuve car le mot '" + word + "' est interdit. \n"
                    + "\"veuillez reessayer\"";
        }
        }
        */

        if (iservicePoste.contientMotInterdit(p.getContentPost().toLowerCase())) {
            return ("votre poste  contient un mot interdit. " );
        }

        iservicePoste.ajouterpost(p,idUser) ;

        return "votre poste est ajouter avec succes" ;

    }

    @GetMapping("AfficherPostes")
    public List<Post> getpost (){
        return iservicePoste.getposts();
    }
    @DeleteMapping("SupprimerPost")
    void deletepost(@RequestParam long idPost){
        iservicePoste.supprimerpost(idPost);
    }
    @PostMapping("ModifierPost")
    void modifposte(@RequestBody Post post){
        iservicePoste.modifpost(post);
    }

    @GetMapping("/getCommentByPost")
    public List<Comment> getCommentByPost (@RequestParam long idPost){

        return iservicePoste.getCommentsByPost(idPost);
    }

}
