package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.camping.Advantage;
import esprit.tunisiacamp.entities.camping.CampingGround;
import esprit.tunisiacamp.repositories.AdvantagesRepositories;
import esprit.tunisiacamp.repositories.CampingGroundRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvantageService implements IAdvantageService{
    @Autowired
    private AdvantagesRepositories advantagesRepositories;
    @Autowired
    private CampingGroundRepositories campingGroundRepositories;

    @Override
    public Advantage saveAdvantage(Advantage advantage) {
        return advantagesRepositories.save(advantage);
    }

    @Override
    public List<Advantage> getAllAdvantage(String type) {
        return advantagesRepositories.getAdvantagesByType(type);
    }

    @Override
    public List<Advantage> getAdvantage(long id) {
        List<Advantage> rate = (List<Advantage>) advantagesRepositories.findAll();

        return null;
    }

    @Override
    public void deleteAdvantageById(long id) {
        advantagesRepositories.deleteById(id);
    }

    @Override
    public void affecterFavorite(Integer favoriteId, Integer campingId) {
        Advantage ad = advantagesRepositories.findById(Long.valueOf(favoriteId)).get();
        CampingGround cp = campingGroundRepositories.findById(Long.valueOf(campingId)).get();
        cp.getAdvantages().add(ad);
        campingGroundRepositories.save(cp);
    }

    @Override
    public List<Advantage> CampAdvantage(long idcamp) {
        return advantagesRepositories.CampAdvantage(idcamp);
    }
}
