package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.shopping.Tool;
import esprit.tunisiacamp.repositeries.ShopRepositery;
import esprit.tunisiacamp.repositeries.ToolRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService implements ShopIService{
    @Autowired
    ShopRepositery sh_repo;
    @Autowired
    ToolRepositery to_repo;

    @Override
    public List<User> getShopById(long tool_id) {
        List<User> output=new ArrayList<User>();
        Tool input=to_repo.findById(tool_id).get();
        for (User sh:input.getOwners()) {
            if (sh.getRole().getRole().toString().equals("SHOP"))
                output.add(sh);
        }
        return output;
    }

    @Override
    public List<User> getShops() {
        List<User> output = new ArrayList<User>();
        for (User sh:sh_repo.findAll()) {
            if (sh.getRole().getRole().toString().equals("SHOP"))output.add(sh);
        }
        return output;
    }
}
