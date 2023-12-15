package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.forum.Mots;

import java.util.List;


public interface Iservices {

    void supprimeruser(Integer iduser);



    void ajoutermots(List<Mots> mots);

    boolean contientMotInterdit(String ContentPost);
}