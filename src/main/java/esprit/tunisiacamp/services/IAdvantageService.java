package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.camping.Advantage;

import java.util.List;

public interface IAdvantageService {
    Advantage saveAdvantage(Advantage advantage);
    List<Advantage> getAllAdvantage(String type);
    List<Advantage> getAdvantage(long id);
    void deleteAdvantageById(long id);
    void affecterFavorite (Integer favoriteId , Integer campingId);
    List<Advantage> CampAdvantage (long idcamp);
}
