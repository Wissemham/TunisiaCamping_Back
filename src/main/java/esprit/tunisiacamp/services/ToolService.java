package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.shopping.Tool;
import esprit.tunisiacamp.repositeries.ShopRepositery;
import esprit.tunisiacamp.repositeries.ToolRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ToolService implements ToolIService {
    @Autowired
    ToolRepositery to_repo;
    @Autowired
    ShopRepositery shop_repo;

    @Override
    public Tool addTool(Tool tool) {
        return to_repo.save(tool);
    }

    @Override
    public void deleteTool(Tool tool) {
        to_repo.delete(tool);
    }

    @Override
    public Tool updateTool(Tool tool) {
        return to_repo.save(tool);
    }

    @Override
    public List<Tool> getTools() {
        return (List<Tool>) to_repo.findAll();
    }

    @Override
    public List<Tool> getToolsByShop(long shop_id) {
        List<User> shops=(List<User>)shop_repo.findAll();
        for (User shop:(List<User>)shop_repo.findAll()) {
            if(shop.getIdUser()==shop_id)
                return shop.getMy_tools();
        }
        return new ArrayList<Tool>();
    }

    @Override
    public List<Tool> threeCriteriaToolSearch(String criteria1, String criteria2, String criteria3,String input1,String input2,String input3,int price) {
        List<Tool> output=(List<Tool>) to_repo.findAll();
        List<Tool> outputs=new ArrayList<Tool>();
        List<Tool> outputss=new ArrayList<Tool>();
        List<Tool> outputsss=new ArrayList<Tool>();
        for (Tool tool:output) {
            if (criteria1.equals("name")) {
                if (tool.getName().contains(input1) || tool.getName().equals(input1))
                    outputs.add(tool);
            }
            if (criteria1.equals("variety")) {
                if (tool.getVariety().toString().equals(input1)) outputs.add(tool);
            }
            if (criteria1.equals("season")) {
                if (tool.getSeason().toString().equals(input1)) outputs.add(tool);
            }
            if (criteria1.equals("type")) {
                if (tool.getType().toString().equals(input1)) outputs.add(tool);
            }
            if (criteria1.equals("price")) {
                if (input1.equals(">=") && price <= tool.getPrice()) outputs.add(tool);
                if (input1.equals("<=") && price >= tool.getPrice()) outputs.add(tool);
                if (input1.equals("=") && price == tool.getPrice()) outputs.add(tool);
            }
        }
        if(criteria2.length()>3){
            for (Tool tool:outputs) {
                if (criteria2.equals("name")) {
                    if (tool.getName().contains(input2) || tool.getName().equals(input2))
                        outputss.add(tool);
                }
                if (criteria2.equals("variety")) {
                    if (tool.getVariety().toString().equals(input2)) outputss.add(tool);
                }
                if (criteria2.equals("season")) {
                    if (tool.getSeason().toString().equals(input2)) outputss.add(tool);
                }
                if (criteria2.equals("type")) {
                    if (tool.getType().toString().equals(input2)) outputss.add(tool);
                }
                if (criteria2.equals("price")) {
                    if (input2.equals(">=") && price <= tool.getPrice()) outputss.add(tool);
                    if (input2.equals("<=") && price >= tool.getPrice()) outputss.add(tool);
                    if (input2.equals("=") && price == tool.getPrice()) outputss.add(tool);
                }
            }
        }
        if(criteria2.length()>3 && criteria3.length()>3){
            for (Tool tool:outputss) {
                if (criteria3.equals("name")) {
                    if (tool.getName().contains(input3) || tool.getName().equals(input3))
                        outputsss.add(tool);
                }
                if (criteria3.equals("variety")) {
                    if (tool.getVariety().toString().equals(input3)) outputsss.add(tool);
                }
                if (criteria3.equals("season")) {
                    if (tool.getSeason().toString().equals(input3)) outputsss.add(tool);
                }
                if (criteria3.equals("type")) {
                    if (tool.getType().toString().equals(input3)) outputsss.add(tool);
                }
                if (criteria3.equals("price")) {
                    if (input3.equals(">=") && price <= tool.getPrice()) outputsss.add(tool);
                    if (input3.equals("<=") && price >= tool.getPrice()) outputsss.add(tool);
                    if (input3.equals("=") && price == tool.getPrice()) outputsss.add(tool);
                }
            }
        }
        if(criteria2.length()>3 & criteria3.length()>3)return outputsss;
        if(criteria2.length()>3 & criteria3.length()<3)return outputss;
        return outputs;
    }


}
