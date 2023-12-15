package esprit.tunisiacamp.repositeries;

import esprit.tunisiacamp.entities.shopping.Tool;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepositery extends CrudRepository<Tool,Long> {

}
