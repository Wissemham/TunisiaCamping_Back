package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.camping.CampingGround;
import esprit.tunisiacamp.entities.camping.Favorite;

import java.util.List;

public interface IFavoriteService {
    Favorite saveFavorite(Favorite favorite);
    List<Favorite> getAllFavorite();
    List<CampingGround> getMyFavorite(Integer userId);
    void deleteFavoriteById(long id);
    void affecterFavorite (Integer favoriteId , Integer campingId,Integer userId);
    boolean isFavorite ( long id,long user);
    String favoriteid(long id,long user);
}
