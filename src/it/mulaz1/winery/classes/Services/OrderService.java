package it.mulaz1.winery.classes.Services;

import it.mulaz1.winery.classes.Users.Customer;
import it.mulaz1.winery.classes.Wine;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * {@code OrderService} save every order in Winery
 *
 */

public class OrderService {
    public Boolean newOrder(String username, Wine wine, Integer quantity) {
    	return Database.addOrder(username,wine,quantity);
    }
}
