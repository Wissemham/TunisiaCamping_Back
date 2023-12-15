package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.shopping.Critique;
import esprit.tunisiacamp.entities.shopping.Tool;
import esprit.tunisiacamp.repositeries.CritiqueRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CritiqueService implements CritiqueIService {
    @Autowired
    CritiqueRepositery cr_repo;

    @Override
    public Critique addCritique(Critique critique, long tool_id,long myid) {
        Tool filler=new Tool();
        critique.setTool(filler);
        critique.getTool().setIdTool(tool_id);
        critique.setUser_id_user(myid);
        return cr_repo.save(critique);
    }

    @Override
    public void deleteCritique(Critique critique) {
        cr_repo.delete(critique);
    }

    @Override
    public Critique updateCritique(Critique critique) {
        return cr_repo.save(critique);
    }

    @Override
    public List<Critique> getCritiquesOfTool(long tool_id) {
        List<Critique> output = new ArrayList<Critique>();
        for (Critique cr:cr_repo.findAll()) {
            if (cr.getTool().getIdTool()==tool_id)output.add(cr);
        }
        return output;
    }
}
