package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.forum.ChatRoom;
import esprit.tunisiacamp.repositories.RoomRespository;
import esprit.tunisiacamp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ServiceRoom implements IserviceRomm{
    @Autowired
    RoomRespository roomRespository;
    @Autowired
    UserRepository userRepository;

    @Override
    public ChatRoom ajoutroom(ChatRoom room) {
        return roomRespository.save(room);
    }

    @Override
    public void affecteruseraroom(Integer idUser, long idroom) {
        User user = userRepository.findById(idUser).get();
        ChatRoom room = roomRespository.findById(idroom).get(); // child
        user.getChatRooms().add(room);

        userRepository.save(user);
    }

    @Override
    public void midifroom(ChatRoom room) {
        ChatRoom roomm = roomRespository.findById(room.getIdChatRoom()).get();
        roomm.setNameRoom(room.getNameRoom());
        roomRespository.save(roomm);
    }

    @Override
    public void suprimerroom(long idroom) {
        roomRespository.deleteById(idroom);
    }

}
