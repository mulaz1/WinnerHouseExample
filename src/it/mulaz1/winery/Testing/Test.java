/**
 * 
 */
package it.mulaz1.winery.Testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.RepeatedTest;

import it.mulaz1.winery.classes.*;
import it.mulaz1.winery.classes.Services.*;
import it.mulaz1.winery.classes.Users.*;
import it.mulaz1.winery.classes.WineCellar.*;

/**
 * @author simo
 *
 */
public class Test {
	
private Boolean result;
	//List of username 
	private static List<String> UsernameTest = new ArrayList<String>();
	
	//List of password
	private static List<String> PasswordTest = new ArrayList<String>();
		
	private Wine wineTest = new Wine("WineTest",1234,"test","test","test");
	private Customer customerTest1 = new Customer("customer1","customerTest1","Email1","password1");
	private Employee employeeTest1 = new Employee("employee1","employeeTest1","Email2","password1");
	private WineCellar cellar = new WineCellar(employeeTest1);

	
	/**
	 * {@code init} initialize username and password list for login test
	 */
	@Before
	public void initAll() {
			
		UsernameTest.add(employeeTest1.getUsername());
		UsernameTest.add("mario");
		UsernameTest.add(customerTest1.getUsername());
		UsernameTest.add("piero");
		
		PasswordTest.add("password1");
		PasswordTest.add(employeeTest1.getPassword());
		PasswordTest.add("password2");
		PasswordTest.add(customerTest1.getPassword());

		
	}
	/**
	 * {@code} return result of test
	 * @return result result for every methods
	 */

	public Boolean getResult(){
		return result;
	}
	
	/** 
	 * {@code} testing login from user and employee
	 */
	
	@RepeatedTest(10)
	@org.junit.Test
	public void doLogin() {
		for(String username : UsernameTest) 
		{
			for(String password : PasswordTest)
			{
				result = WineCellar.ValidationUser(username,password);
				
				if(( username.equals(customerTest1.getUsername()) && password.equals(customerTest1.getPassword())) || (username.equals(employeeTest1.getUsername()) && password.equals(employeeTest1.getPassword())))
				{
					System.out.println( username + " " + password + " " + result);
					assertTrue("Non ci sto capendo un cazzo",result);
				}
				else {
					assertFalse(result);
					System.out.println( username + " " + password + " " + result);
				}
			}
		}
	}	
	/** 
	 * {@code} testing increment bottle on stock from employee
	 */

//	@org.junit.Test
//	public void addBottleToStock()
//	{
//		//employee login
//		result = cellar.ValidationUser(employeeTest1.getUsername(),employeeTest1.getPassword());
//		assertTrue("Erorre di loggin",result);
//		System.out.println("\n Employee logging");
//				
//		//get wineTest stock
//		cellar.putNewBottlesInWarehouse(wineTest,10);
//		for( Stock wineStock: cellar.getStock()) {
//		
//			if(wineStock.getWine().getName().equals(wineTest.getName()))
//			{
//				assertEquals(Double.valueOf(10),Double.valueOf(wineStock.getQuantity()));
//			}
//		}
//	}
	
	@org.junit.Test
	public void doBuying() {
		
		//employee login
		result = cellar.ValidationUser(customerTest1.getUsername(),customerTest1.getPassword());
		assertTrue("Erorre di loggin",result);		
		System.out.println("\n customer logging");
		
		cellar.placeOrder(wineTest,5);
		
		for( Stock wineStock: cellar.getStock()) {			
			if(wineStock.getWine().getName().equals(wineTest.getName()))
			{
				assertEquals(Double.valueOf(5),Double.valueOf(wineStock.getQuantity()));
			}
		}
	}
}
