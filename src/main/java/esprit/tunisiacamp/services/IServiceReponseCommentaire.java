package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.forum.Reponse;

public interface IServiceReponseCommentaire {

    void repondreauncommentaire(Reponse r ,long idCom , Integer idUser);
}
