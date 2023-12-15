package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;

import java.util.List;

public interface ShopIService {
    List<User> getShopById(long tool_id);
    List<User> getShops();
}
