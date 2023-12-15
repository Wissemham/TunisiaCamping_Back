package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.services.ShopIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ShopRestController {
    @Autowired
    ShopIService shopservice;

    @GetMapping("/shop/getshopbyid")
    public List<User> getShopById(@RequestParam long tool_id){return shopservice.getShopById(tool_id);}
    @GetMapping("/shop/getshops")
    public List<User> getShops(){return shopservice.getShops();}
}
