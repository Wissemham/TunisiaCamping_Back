package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.camping.Favorite;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepositories extends CrudRepository<Favorite,Long> {
    @Query(value = "select * from camping_ground c left join favorite f on c.id_camping_ground = f.campingg_fav_id_camping_ground where f.user_fav_id_user = ?1", nativeQuery = true)
    List findFavoriteCampingsByUserId(Integer userId);

    @Query(value = "select campingg_fav_id_camping_ground from favorite f where f.campingg_fav_id_camping_ground = ?1 and f.user_fav_id_user = ?2", nativeQuery = true)
    Integer isFavorite(long id,long user);

    @Query(value = "select id_favorite from favorite f where f.campingg_fav_id_camping_ground = ?1 and f.user_fav_id_user = ?2", nativeQuery = true)
    String Favoriteid(long id,long user);
}
