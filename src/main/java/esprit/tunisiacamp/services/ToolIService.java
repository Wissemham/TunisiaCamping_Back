package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.shopping.Tool;

import java.util.List;

public interface ToolIService {
    Tool addTool(Tool tool);
    void deleteTool(Tool tool);
    Tool updateTool(Tool tool);
    List<Tool> getTools();
    List<Tool> getToolsByShop(long shop_id);
    List<Tool> threeCriteriaToolSearch
            (String criteria1,String criteria2,String criteria3,String input1,String input2,String input3,int price);
}
