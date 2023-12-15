package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.forum.Rating;

import java.util.List;

public interface IserviceLike {
    public void likerPost(Rating l, long idPost, Integer idUser);

    public List<Rating> getLikes();

    void supprimerlike(long idlike);

    public void modifrate(Rating rating);

    List<Rating> listelike (long idPoste) ;
}
