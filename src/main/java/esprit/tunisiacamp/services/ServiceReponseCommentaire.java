package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.forum.Comment;
import esprit.tunisiacamp.entities.forum.Reponse;
//import esprit.tunisiacamp.repositories.CommentRepository;
//import esprit.tunisiacamp.repositories.ReponseRepository;
import esprit.tunisiacamp.repositories.CommentRepository;
import esprit.tunisiacamp.repositories.ReponseRepository;
import esprit.tunisiacamp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service

public class ServiceReponseCommentaire implements IServiceReponseCommentaire {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReponseRepository reponseRepository ;
    @Autowired
    CommentRepository commentRepository;

    @Override
    public void repondreauncommentaire(Reponse r, long idCom, Integer idUser) {

        Comment comment= commentRepository.findById(idCom).get();
        User user = userRepository.findById(idUser).get();
        r.setUserreponse(user);
        r.setReponsecomm(comment);
        r.setDateReponse(LocalDateTime.now());
        reponseRepository.save(r);
    }
}
