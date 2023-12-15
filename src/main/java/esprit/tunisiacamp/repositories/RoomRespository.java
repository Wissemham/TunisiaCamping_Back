package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.forum.ChatRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RoomRespository extends CrudRepository<ChatRoom, Long> {
}
