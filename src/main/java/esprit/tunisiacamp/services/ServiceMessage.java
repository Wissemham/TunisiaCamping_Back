package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.forum.ChatRoom;
import esprit.tunisiacamp.entities.forum.Messagee;
import esprit.tunisiacamp.repositories.MessageeRepository;
import esprit.tunisiacamp.repositories.RoomRespository;
import esprit.tunisiacamp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ServiceMessage implements IserviceMessage{
    @Autowired
    MessageeRepository messageeRepository;
    @Autowired
    RoomRespository roomRespository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void ajouterMessage(Messagee m, Integer idUser, long chatroom) {
        User user = userRepository.findById(idUser).get();
        ChatRoom room = roomRespository.findById(chatroom).get();

        m.setUser(user);
        m.setChatRoom(room);

        messageeRepository.save(m);
    }

    @Override
    public void supprimermessage(long idmsg) {
        messageeRepository.deleteById(idmsg);
    }

    @Override
    public List<Messagee> getmessages() {

        return (List<Messagee>) messageeRepository.findAll();

    }
}
