package it.mulaz1.winery.classes.WineCellar;

import it.mulaz1.winery.classes.Services.AuthService;
import it.mulaz1.winery.classes.Services.Database;
import it.mulaz1.winery.classes.Services.NotificationService;
import it.mulaz1.winery.classes.Services.OrderService;
import it.mulaz1.winery.classes.Users.Customer;
import it.mulaz1.winery.classes.Users.Employee;
import javafx.scene.control.Alert;
import it.mulaz1.winery.classes.Order;
import it.mulaz1.winery.classes.Stock;
import it.mulaz1.winery.classes.Wine;

import java.util.ArrayList;
import java.util.List;

import application.AlertHelper;
/**
 * 
 * {@code WineCellar} is the manager class of Winery
 *
 */
public class WineCellar {
    private final static AuthService auth = new AuthService();
    private final static OrderService orders = new OrderService();
    private final static NotificationService notifications = new NotificationService();

    private Employee theEmployee;
    
    private static List<String> logginEmployee = new ArrayList<String>();
    private static List<String> logginCustomer = new ArrayList<String>();
    
    
    public WineCellar(final Employee employee) {
        theEmployee = employee;
        auth.newEmployee(employee);
    }
     
    /**
     * {@code registerCustomer} Is used for register new Customer in winery
     * @param customer Customer instance
     * @return Boolean true o false
     */
    public static Boolean registerCustomer(final Customer customer) {
        return auth.newCustomer(customer);
    }
    /**
     *  {@code registerEmployee} Is used for register new Employeee by Admin in Winery
     * @param employee Employee instance
     * @return Boolean true o false
     */
    public static Boolean registerEmployee(final Employee employee) {
        return auth.newEmployee(employee);
    }
    
    /**
     * {@code ValidationUser} is used for Authentication User
     * @param username User username
     * @param password User password
     * @return Boolean true o false
     */
    public static Boolean ValidationUser(final String username,final String password) {
    	if(auth.validationCustomer(username,password)) {
    		logginCustomer.add(username);
    		return true;
    	}
    	if(auth.validationEmployee(username,password)) {
    		logginEmployee.add(username);
    		return true;
    	}
    	if(auth.validationAdmin(username, password)) {
    		return true;
    	}
    	return false;
    }
       
    /**
     * {@code addNewWineToCellar} Add new Wine Type into Cellar
     * @param wine Wine instance
     * @return Boolean true o false
     */
    public static Boolean addNewWineToCellar(final Wine wine) {
    	return Database.addWinetoDatabase(wine);
    }
    
    /**
     * {@code putNewBottlesInWarehourse} increment bottle number in stock
     * @param wine Wine instance
     * @param quantity  Quantity
     */
    public static void putNewBottlesInWarehouse(final Wine wine, final Integer quantity) {           
    
        	Database.IncrementStockQuantityDatabase(wine.getName(),quantity);
        	notifications.notifyAll(wine);
    }
    
    /**
     * {@code placeOrder} When user buy a wine this function manage all process
     * @param wine Wine instance
     * @param howMany Quantity
     */

    public static void placeOrder(final Wine wine, final Integer howMany) {
    	switch(Database.DecrementStockQuantityDatabase(wine.getName(),howMany))
    	{
    		case 0:
    			System.out.println("return 0");
    			orders.newOrder(application.Main.username,wine,howMany);
    			AlertHelper.showAlert(Alert.AlertType.CONFIRMATION,"Thank You", "thanks for buying " + wine.getName() + " from us");
    			
    		break;
    	
    		case 1:
    			System.out.println("return 1");
    			notifications.addSubscriber(application.Main.username,wine);
    			AlertHelper.showAlert(Alert.AlertType.ERROR,"Order Error","we are sorry but there are not enough bottles to fulfill your order, we will send you a notification when they are back in sotck " + wine.getName() + " from us");
    		break;
    	}
     }
    
   /**
    * {@code searchWineByName} is used to search wine by name in all stock
    * @param name Wine name
    * @return Wine instance with this name
    */
    
    public Wine searchWineByName(final String name) {
        return Database.searchBottlesByName(name);
    }

    /**
     * {@code searchWineByYear} is used to search wine by year in all stock
     * @param year Wine Year
     * @return List of Wine with this year
     */
    public List<Wine> searchWineByYear(final Integer year) {
        return Database.searchBottlesByYear(year);
    }
    
    
    /**
     * {@code GetCusomers} is used to get all customer register in Winery
     * @return List of Customer 
     */
    
    public static List<Customer> GetCustomers(){
    	return Database.GetAllCustomer();
    }
    
    /**
     * {@code GetEmployees} is used to get all Employee register in Winery
     * @return List of Employee
     */
    public static List<Employee> GetEmployees(){
    	return Database.GetAllEmployee();
    }
    
    /**
     * {@code getStock}  is used to get all wine and Quantity actualy in stock   
     * @return List of Stock All Element contains quantities and wine instance
     */
    public static List<Stock> getStock(){
    	return Database.getStock();
    }
    
    /**
     * {@code getOrders} is used to get every order made in the winery
     * @return List of Oder All element contains username, wine name and quantity
     */
    public static List<Order> getOrders(){
    	return Database.getOrder();
    }
}
