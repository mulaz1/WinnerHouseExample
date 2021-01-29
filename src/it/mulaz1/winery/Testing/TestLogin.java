package it.mulaz1.winery.Testing;

import it.mulaz1.winery.classes.WineCellar.*;

import java.util.ArrayList;
import java.util.List;

import it.mulaz1.winery.classes.Users.*;

public class TestLogin{
	
	private Boolean result;
	
	//List of username 
	private static List<String> UsernameTest = new ArrayList<String>();
	
	//List of password
	private static List<String> PasswordTest = new ArrayList<String>();
			
			
	private WineCellar cellar = new WineCellar(null);
	
	
	/**
	 * {@code init} initialize username and password list for login test
	 */
	
	public void init() {
		
		Customer customerTest1 = new Customer("Customer1","CustomerTest1","Email1","Password");
		Employee employeeTest1 = new Employee("Employee1","EmployeeTest1","Email2","Password2");
		cellar.registerEmployee(employeeTest1);    //registro degli utenti per il test
		cellar.registerCustomer(customerTest1);
		
		UsernameTest.add(employeeTest1.getUsername());
		UsernameTest.add("mario");
		UsernameTest.add(customerTest1.getUsername());
		UsernameTest.add("piero");
		
		PasswordTest.add("password1");
		PasswordTest.add("password2");
		PasswordTest.add(customerTest1.getPassword());
		UsernameTest.add(employeeTest1.getPassword());
		
	}
	/**
	 * {@code} return result of test
	 * @return result result for every methods
	 */
	public Boolean getResult(){
		return result;
	}
	
	
	public void doLogin() {
			
		
	}
	
}
