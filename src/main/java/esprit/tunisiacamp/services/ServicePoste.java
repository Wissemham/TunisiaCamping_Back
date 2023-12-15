package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.forum.Comment;
import esprit.tunisiacamp.entities.forum.Mots;
import esprit.tunisiacamp.entities.forum.Post;
import esprit.tunisiacamp.repositories.MotsREpository;
import esprit.tunisiacamp.repositories.UserRepository;
import esprit.tunisiacamp.repositories.postRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class ServicePoste implements IservicePoste {
    @Autowired
    postRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MotsREpository motsREpository;

    @Override
    public void ajouterpost(Post p, Integer idUser) {


        User u = userRepository.findById(idUser).get();
        p.setUser(u);
        // p.setCreation(new Date());
        // p.setCreation(new Time());
        p.setCreation(LocalDateTime.now());


        postRepository.save(p);


    }

    @Override
    public void modifpost(Post post) {
        Post postt = postRepository.findById(post.getIdPost()).get();
        postt.setContentPost(post.getContentPost());
        postRepository.save(postt);

    }

    @Override
    public void supprimerpost(long idPost) {
        postRepository.deleteById(idPost);

    }

    @Override
    public List<Post> getposts() {
        return (List<Post>) postRepository.findAll();
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

    @Override
    public List<Comment> getCommentsByPost(long idPost) {
Post post= postRepository.findById(idPost).get();
List<Comment> commentList=post.getComments();
        return commentList;
    }

}
