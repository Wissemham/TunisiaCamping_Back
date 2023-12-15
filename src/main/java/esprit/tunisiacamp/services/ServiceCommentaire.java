package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.forum.Comment;
import esprit.tunisiacamp.entities.forum.Mots;
import esprit.tunisiacamp.entities.forum.Post;
import esprit.tunisiacamp.repositories.CommentRepository;
import esprit.tunisiacamp.repositories.MotsREpository;
import esprit.tunisiacamp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class ServiceCommentaire implements IserviceCommentaire{
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    esprit.tunisiacamp.repositories.postRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MotsREpository motsREpository;

    @Override
    public void ajoutercommentare(Comment m, long idPost, Integer idUser) {
        Post post = postRepository.findById(idPost).get();
        User user = userRepository.findById(idUser).get();

        m.setPost(post);
        m.setUser(user);
        m.setCreationComment(LocalDateTime.now());

        commentRepository.save(m);
    }

    @Override
    public void modifierCommentaire(Comment commentaire) {
        Comment comment = commentRepository.findById(commentaire.getIdComment()).get();
        comment.setContentComment(commentaire.getContentComment());
        commentRepository.save(comment);
    }

    @Override
    public void supprimercommentaire(long idcomment) {
        commentRepository.deleteById(idcomment);
    }

    @Override
    public List<Comment> getComments() {
        return (List<Comment>) commentRepository.findAll();

    }

    @Override
    public boolean contientMotInterdit(String ContentPost) {
        List<Mots> motsInterdits = (List<Mots>) motsREpository.findAll();
        for (Mots motInterdit : motsInterdits) {
            if (ContentPost.contains(motInterdit.getMots().toLowerCase())) {
                return true;
            }
        }
        return false;
    }





}
