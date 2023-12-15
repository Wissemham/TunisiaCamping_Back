package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.forum.Comment;
import esprit.tunisiacamp.entities.forum.Post;

import java.util.List;

public interface IservicePoste {
    void ajouterpost(Post p, Integer idUser);

    void supprimerpost(long idPost);

    public List<Post> getposts();

    public void modifpost(Post post);
    boolean contientMotInterdit(String ContentPost);
    public List<Comment> getCommentsByPost(long idPost);

}
