package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.shopping.Critique;

import java.util.List;

public interface CritiqueIService {
    Critique addCritique(Critique critique, long tool_id,long myid);
    void deleteCritique(Critique critique);
    Critique updateCritique(Critique critique);
    List<Critique> getCritiquesOfTool(long tool_id);
}
