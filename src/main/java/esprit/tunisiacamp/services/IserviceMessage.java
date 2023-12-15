package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.forum.Messagee;

import java.util.List;

public interface IserviceMessage {

    public void ajouterMessage(Messagee m, Integer idUser, long chatroom);

    public List<Messagee> getmessages();

    void supprimermessage(long idmsg);
}
