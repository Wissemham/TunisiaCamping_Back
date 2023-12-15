package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.forum.ChatRoom;

public interface IserviceRomm {
    public ChatRoom ajoutroom(ChatRoom room);

    void affecteruseraroom(Integer idUser, long idroom);

    public void midifroom(ChatRoom room);

    void suprimerroom(long idroom);
}
