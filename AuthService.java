/**
 * it.mulaz1.winery.classes.Services contains every service class as validation, authentication, new order, Database manager
 */
package it.mulaz1.winery.classes.Services;

import it.mulaz1.winery.classes.Users.AbstractUser;
import it.mulaz1.winery.classes.Services.Database;

import it.mulaz1.winery.classes.Users.Customer;
import it.mulaz1.winery.classes.Users.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * {@code AuthService} is use to check if Customer/Employee is authenticity or registered, or add new Customer/Employee
 *
 */

public class AuthService {

	/**
	 * {@code isValidEmployee} function check if exist employee into Winery
	 * @param username Employee username
	 * @return
	 */
    public static Boolean isValidEmployee(String username) {
    	return Database.ExistEmployee(username);
    }

    /**
	 * {@code isValidCustomer} function check if exist customer into Winery
	 * @param username Customer username
	 * @return
	 */
    
    public Boolean isValidCustomer(String username) {
    	return Database.ExistCustomer(username);
    }
    
    /**
	 * {@code isValidAdmin} function check if exist admin int oWinery
	 * @param username Employee username
	 * @return
	 */
    public static Boolean isValidAdmin(String username) {
    	return Database.ExistAdmin(username);
    }

    /**
     * {@code newCustomer} function add Customer to Winery
     * @param customer Instance customer that will add to database
     * @return
     */
    public Boolean newCustomer(final Customer customer) {
        if (isValidCustomer(customer.getUsername())) {
            return false;
        }
        Database.AddUserToTable("customer",customer.getName(),customer.getUsername(),customer.getPassword(),customer.getEmail());
        return true;
    }

    /**
     * {@code newCustomer} function add Employee to Winery
     * @param employee Instance Employee that will add to database
     * @return
     */
    public Boolean newEmployee(final Employee employee){
        if (isValidEmployee(employee.getUsername())) {
            return false;
        }
        Database.AddUserToTable("employee",employee.getName(),employee.getUsername(),employee.getPassword(),employee.getEmail());
        return true;
    }
    
    /**
     * {@code validationCustomer} function is use to validation customer
     * @param username Customer username for validation
     * @param password Customer password for validation
     * @return
     */
    public Boolean validationCustomer(final String username, final String password) {
	
    		return Database.customerValidation(username,password);
    }
    
    /**
     * {@code validationEmployee} function is use to validation employee
     * @param username Employee username for validation
     * @param password Employee password for validation
     * @return
     */
    public Boolean validationEmployee(final String username, final String password) {
    	
    	return Database.employeeValidation(username,password);
    }
    /**
     * {@code validationAdmin} function is use to validation admin
     * @param username Admin username for validation
     * @param password Admin password for validation
     * @return
     */ 
    public Boolean validationAdmin(final String username, final String password) {
    	
    	return Database.adminValidation(username,password);
    }
}