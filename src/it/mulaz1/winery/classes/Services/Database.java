package it.mulaz1.winery.classes.Services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import it.mulaz1.winery.classes.Order;
import it.mulaz1.winery.classes.Stock;
import it.mulaz1.winery.classes.Wine;
import it.mulaz1.winery.classes.Users.*;

public class Database {
	
	private static Connection connection = null;	
	private static String database = "../Winery.db";
	
	/**
	 * {@code HasHPassword} is private function that encoding password
	 * @param password
	 * @return Encrypted password
	 */
	private static String HashPassword(String password) {
		
		String hashPassword = "";
		
		try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
             hashPassword= sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        return hashPassword;
	}
	
	/**
	 * {@code Connect} create Database,Table and admin user
	 */
	public static void Connect() {
		
		try
        {
          // create a database connection
		  connection = DriverManager.getConnection("jdbc:sqlite:" + database);
          Statement statement = connection.createStatement();
          statement.setQueryTimeout(5); 

          try { 
          statement.executeUpdate("CREATE TABLE IF NOT EXISTS wine("
          		+ " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
          		+ " name TEXT NOT NULL,"
          		+ " wineProducer TEXT NOT NULL,"
          		+ " year INTEGER NOT NULL,"
          		+ " technicalNotes TEXT,"
          		+ " wineType TEXT NOT NULL"
          		+ ");");
          }
          catch(SQLException e) {
        	  System.err.println(e.getMessage());
          }
          try {
          
          statement.executeUpdate("CREATE TABLE customer(" 
            		+ " id  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
            		+ " name  TEXT NOT NULL,"
            		+ " username TEXT NOT NULL UNIQUE,"
            		+ "	password TEXT NOT NULL,"
            		+ "	email TEXT"
            		+ ");");
          }
          catch(SQLException e) {
        	  System.err.println(e.getMessage());
          }
          
          try {
          statement.executeUpdate("CREATE TABLE employee("
          		+ "	name	TEXT NOT NULL,"
          		+ "	id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
          		+ "	email	TEXT NOT NULL,"
          		+ "	password	TEXT NOT NULL,"
          		+ "	username	TEXT NOT NULL UNIQUE"
          		+ ");");
          }
          catch(SQLException e) {
        	  System.err.println(e.getMessage());
          }
          
          try {
          statement.executeUpdate("CREATE TABLE admin("
        		+"	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
        		+ "	name	TEXT,"
          		+ "	`username`	TEXT NOT NULL UNIQUE,"
          		+ "	`password`	TEXT NOT NULL,"
          		+ "	`email`	TEXT NOT NULL UNIQUE"
          		+ ");");
          } 
          catch(SQLException e) {
        	  System.err.println(e.getMessage());
          }
          
          
          try {
              statement.executeUpdate("CREATE TABLE ordered("
              		+ "	id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
              		+ "	date	TEXT NOT NULL,"
              		+ "	username	TEXT NOT NULL,"
              		+ "	winename	TEXT NOT NULL,"
              		+ "	quantity	INTEGER NOT NULL"
              		+ ");");
              } 
              catch(SQLException e) {
            	  System.err.println(e.getMessage());
              }
          
         
          
          try {
        	  statement.executeUpdate("CREATE TABLE notify("
        			  +" id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
        			  +" username TEXT NOT NULL,"
        			  +" wineName TEXT NOT NULL"
        			  +");");
          	} 
          	catch(SQLException e) {
        	  System.err.println(e.getMessage());
          	}	  
          
          try {
          statement.executeUpdate("CREATE TABLE stock("
          		+ "	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
          		+ "	`quantity`	INTEGER NOT NULL,"
          		+ "	`wine_id`	INTEGER NOT NULL UNIQUE"
          		+ ");");
          }
          
          catch(SQLException e) {
        	  System.err.println(e.getMessage());
          }
          
          try{
 			 PreparedStatement statementAdmin = connection.prepareStatement("INSERT INTO 'admin'(name,username,password,email) VALUES (?,?,?,?);");
 	         statementAdmin.setQueryTimeout(5);  // set timeout to 5 sec.
 	        
 	         statementAdmin.setString(1,"administrator");
 	         statementAdmin.setString(2, "admin");
 	         statementAdmin.setString(3, HashPassword("admin"));
 	         statementAdmin.setString(4, "webmaster@localhost.io");     
 	         statementAdmin.executeUpdate();
 		}
 		catch(SQLException e)
         {
 			
           System.err.println(e.getMessage());
         }
          
          try{
  			 PreparedStatement statementCustomerTest = connection.prepareStatement("INSERT INTO 'customer'(name,username,password,email) VALUES (?,?,?,?);");
  			statementCustomerTest.setQueryTimeout(5);  // set timeout to 5 sec.
  			statementCustomerTest.setString(1,"CustomerTest1");
  			statementCustomerTest.setString(2, "customerTest1");
  			statementCustomerTest.setString(3, HashPassword("password1"));
  			statementCustomerTest.setString(4, "emailtest1");     
  			statementCustomerTest.executeUpdate();
  	         
  	         PreparedStatement statementEmployeeTest = connection.prepareStatement("INSERT INTO 'employee'(name,username,password,email) VALUES (?,?,?,?);");
  	         statementEmployeeTest.setQueryTimeout(5);  // set timeout to 5 sec.
	        
	         statementEmployeeTest.setString(1,"EmployeeTest1");
	         statementEmployeeTest.setString(2, "employeeTest1");
	         statementEmployeeTest.setString(3, HashPassword("password1"));
	         statementEmployeeTest.setString(4, "emailtest1");     
	         statementEmployeeTest.executeUpdate();
	         
	         PreparedStatement statementWineTest = connection.prepareStatement("INSERT INTO 'wine'(name,year,wineProducer,technicalNotes,wineType) VALUES (?,?,?,?,?);");
	  	      statementWineTest.setQueryTimeout(5);  // set timeout to 5 sec.
		      statementWineTest.setString(1,"winetest");
		      statementWineTest.setInt(2,1234);
		      statementWineTest.setString(3,"test");
		      statementWineTest.setString(4,"test");  
		      statementWineTest.setString(5,"test");
		      statementWineTest.executeUpdate();
  		}
  		catch(SQLException e)
          {
  			
            System.err.println(e.getMessage());
          }
          
        }
		catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          System.err.println(e.getMessage());
        }
        finally
        {
          try
          {
            if(connection != null)
              connection.close();
          }
          catch(SQLException e)
          {
            // connection close failed.
            System.err.println(e.getMessage());
          }
        }
	}
    /**  	
     * {@code AddUserToTable} add user ( admin, employee and Customer) to Database
     * @param role Insert employee if you would add user to employee, admin insert into admin table or customer if you would insert into customer table
     * @param name
     * @param username
     * @param password
     * @param email
     */
	public static void AddUserToTable(String role, String name, String username, String password, String email) {  //role : admin / customer / employee
		
	try{
			 connection = DriverManager.getConnection("jdbc:sqlite:" + database);
			 PreparedStatement statement2 = connection.prepareStatement("INSERT INTO '" + role + "' (name,username,password,email) VALUES (?,?,?,?);");
	         statement2.setQueryTimeout(5);  // set timeout to 5 sec.
	        
	         statement2.setString(1, name);
	         statement2.setString(2, username);
	         statement2.setString(3, HashPassword(password));
	         statement2.setString(4, email);     
	         statement2.executeUpdate();
		}
	
		catch(SQLException e)
        {
			
          System.err.println(e.getMessage() + "Ciao");
     
        }
		 finally
	        {
	          try
	          {
	            if(connection != null)
	              connection.close();
	          }
	          catch(SQLException e)
	          {
	            // connection close failed.
	            System.err.println(e.getMessage() + "second");
	          }
	        }	
	}

	/**
	 * {@code addWineToDatabase} add new wine to table wine on database
	 * @param wine
	 * @return
	 */
	public static Boolean addWinetoDatabase(final Wine wine) {
		
		try{
			 connection = DriverManager.getConnection("jdbc:sqlite:" + database);
			 PreparedStatement statement = connection.prepareStatement("INSERT INTO wine(name,year,wineProducer,technicalNotes,wineType) VALUES (?,?,?,?,?);");
	         statement.setQueryTimeout(5);  // set timeout to 5 sec.
	        
	         statement.setString(1, wine.getName());
	         statement.setInt(2, wine.getYear());
	         statement.setString(3,wine.getWineProducer());
	         statement.setString(4,wine.getTechnicalNotes());    
	         statement.setString(5,wine.getVineType()); 
	         statement .executeUpdate();
	         if(connection != null)
	              connection.close();
	              return true;
		}
		catch(SQLException e)
       {
			
         System.err.println(e.getMessage());
       }
		return false;
	}
	
	/**
	 * {@code RemoveWineToDatabase}  remove wine to from database
	 * @param name
	 */
	public static void RemoveWineToDatabase(String name) {
		try{
			 connection = DriverManager.getConnection("jdbc:sqlite:" + database);
	         Statement statement = connection.createStatement();
	         statement.setQueryTimeout(5);  // set timeout to 5 sec.
	         statement.executeQuery("DELETE from wine WHERE name = " + name);    
		}
		catch(SQLException e)
       {
			
         System.err.println(e.getMessage());
       }
		 finally
	        {
	          try
	          {
	            if(connection != null)
	              connection.close();
	          }
	          catch(SQLException e)
	          {
	            // connection close failed.
	            System.err.println(e.getMessage());
	          }
	        }	
	}
	
	/**
	 * {@code IncrementStockQuantityDatabase} increment quantity of wine in stock
	 * @param wineName
	 * @param quantity
	 */
	public static void IncrementStockQuantityDatabase(String wineName, Integer quantity) {
		
		Integer wine_id = 0;
		Integer newQuantity = 0;
		
		try{
			 connection = DriverManager.getConnection("jdbc:sqlite:" + database);
	         Statement statement = connection.createStatement();
	         statement.setQueryTimeout(5);

	         ResultSet wine = statement.executeQuery("SELECT id FROM wine WHERE name = '" + wineName + "'");   //get wine id
		     while(wine.next())
		     {
		        wine_id = wine.getInt("id");
		        System.out.println("id" + wine_id);
		     }
	        try {
		        ResultSet stock = statement.executeQuery("SELECT * FROM stock WHERE wine_id =" + wine_id);  //get stock quantity
		        while(stock.next())
		        {
		        	newQuantity = wine.getInt("quantity") + quantity;
		        }
		        
		        statement.executeQuery("UPDATE stock SET quantity = " + newQuantity +  " WHERE wine_id = " + wine_id); 
	       }
	       catch(SQLException e){
	    	   		statement.executeQuery("INSERT INTO stock(wine_id,quantity) VALUES("
	        	 		+ wine_id + ","
	        	 		+ quantity
	        	 		+ ")");
	        	 }
		}
		
		catch(SQLException e)
       {
			
         System.err.println(e.getMessage());
       }
		 finally
	        {
	          try
	          {
	            if(connection != null)
	              connection.close();
	          }
	          catch(SQLException e)
	          {
	            // connection close failed.
	            System.err.println(e.getMessage());
	          }
	        }	
	}
	
	/**
	 * {@code DecrementStockQauntityDatabase} decrement bottle from  table stock
	 * @param wineName
	 * @param quantity
	 * @return
	 */
	public static Integer DecrementStockQuantityDatabase(String wineName, Integer quantity) {
		
		Integer wine_id = 0;
		Integer newQuantity = 0;
		
		try{
			 connection = DriverManager.getConnection("jdbc:sqlite:" + database);
	         Statement statement = connection.createStatement();
	         Statement statement2 = connection.createStatement();
	         statement.setQueryTimeout(5);

	        ResultSet wine = statement.executeQuery("SELECT * FROM wine WHERE name = '" + wineName + "'");   //get wine id
	        while(wine.next())
	        {
	        	wine_id = wine.getInt("id");
	        	System.out.println("id" + wine_id);
	        }
	        
	        ResultSet stock = statement.executeQuery("SELECT * FROM stock WHERE wine_id = " + wine_id);  //get stock quantity
	        
	        while(stock.next())
	        {
	        	newQuantity = stock.getInt("quantity") - quantity;
	        	System.out.println("quantity " + newQuantity);
	        	
	        	if(newQuantity < 0) {
	        		if(connection != null)
	  	              connection.close();
	        		  return 1;
	        	}
	        }
	        statement2.executeUpdate("UPDATE stock SET quantity = " + newQuantity +  " WHERE wine_id = " + wine_id);  
	        if(connection != null)
	              connection.close();
	        return 0;
		}
		
		catch(SQLException e)
       {
			
         System.err.println(e.getMessage());
         return -1;
       }		
	}
	
	public static List<Stock> getStock(){
		
		List<Stock> allStock = new ArrayList<Stock>();
		Integer idWine = 0;
		try{
			 connection = DriverManager.getConnection("jdbc:sqlite:" + database);
	         Statement statement = connection.createStatement();
	         Statement statementWine = connection.createStatement();
	         statement.setQueryTimeout(5);
	         ResultSet result = statement.executeQuery("select * from 'stock'");

	         while(result.next()) {   //get id wine
	        	 Integer quantity = result.getInt("quantity");
	        	 ResultSet wine = statementWine.executeQuery("select * from 'wine' WHERE id = " + result.getInt("wine_id"));
	        	 while(wine.next()) {
	        		 Wine newWine = new Wine(wine.getString("name"),wine.getInt("year"),wine.getString("wineProducer"),wine.getString("WineType"),wine.getString("technicalNotes"));   //create instance Wine with result query
	        		 Stock stock  = new Stock(newWine,quantity); 
	        		 allStock.add(stock);
	        		 wine.close();
	        	 } 	
	         }  
	         if(connection != null)
		              connection.close();
			}
		
	        catch(SQLException e)
	        {
	  			
	           System.err.println(e.getMessage());
	        }
		return allStock;	
	}
	
	
	/**
	 * {@code getOrder} get all order on database
	 * @return List of Order
	 */
	public static List<Order> getOrder(){
		
		List<Order> orders = new ArrayList<Order>();
		try{
			 connection = DriverManager.getConnection("jdbc:sqlite:" + database);
	         Statement statement = connection.createStatement();
	         Statement statementWine = connection.createStatement();
	         statement.setQueryTimeout(5);
	         ResultSet result = statement.executeQuery("SELECT * from `order`;");
	         while(result.next()) {
	        	 Order order = new Order(result.getString("username"),result.getString("wineName"),result.getInt("quantity"),result.getString("date"));
	        	 orders.add(order);
	         }
	         if(connection != null)
	              connection.close();
	         return orders;
	         
		}
		catch(SQLException e)
        {
  			
           System.err.println(e.getMessage());
           return null;
        }
		
		
	}
		
	/**
	 * {@code GetCustomer} get specific customer on Databse
	 * @param username
	 * @return Customer Instance
	 */
	public static Customer GetCustomer(String username) {
		
		Customer customer = null;
		try{
			 connection = DriverManager.getConnection("jdbc:sqlite:" + database);
	         Statement statement = connection.createStatement();
	         statement.setQueryTimeout(5);  // set timeout to 5 sec.
	         ResultSet result = statement.executeQuery("SELECT * from customer WHERE username = '" + username + "'");
	         while(result.next()) {
	        	 customer = new Customer(result.getString("name"),result.getString("username"),result.getString("email"),null);
	         }
		}
		catch(SQLException e)
     {
			
       System.err.println(e.getMessage());
     }
		 finally
	        {
	          try
	          {
	            if(connection != null)
	              connection.close();
	          }
	          catch(SQLException e)
	          {
	            // connection close failed.
	            System.err.println(e.getMessage());
	          }
	        }
		return customer;
	}
	
	/**
	 * {@code GetAllCustomer} get all customer on database
	 * @return List of Customer
	 */
	public static List<Customer> GetAllCustomer(){
		
		List<Customer> customers =new ArrayList<Customer>();
		try{
			 connection = DriverManager.getConnection("jdbc:sqlite:" + database);
	         Statement statement = connection.createStatement();
	         statement.setQueryTimeout(5);  // set timeout to 5 sec.
	         ResultSet result = statement.executeQuery("SELECT * from customer");
	         while(result.next()) {
	        	 Customer customer = new Customer(result.getString("name"),result.getString("username"),result.getString("email"),null);
	        	 customers.add(customer);
	         }
	         return customers;
		   }
		catch(SQLException e)
		{
			 System.err.println(e.getMessage());
		        return null;
		}
		
	}
	/**
	 * {@code GetAllEmployee} get all Employee on database
	 * @return List og customer
	 */
	public static List<Employee> GetAllEmployee(){
		
		List<Employee> employees =new ArrayList<Employee>();
		try{
			 connection = DriverManager.getConnection("jdbc:sqlite:" + database);
	         Statement statement = connection.createStatement();
	         statement.setQueryTimeout(5);  // set timeout to 5 sec.
	         ResultSet result = statement.executeQuery("SELECT * from employee");
	         while(result.next()) {
	        	 Employee employee = new Employee(result.getString("name"),result.getString("username"),result.getString("email"),null);
	        	 employees.add(employee);
	         }
	         return employees;
		   }
		catch(SQLException e)
		{
			 System.err.println(e.getMessage());
		        return null;
		}
		
	}
	
	/**
	 * {@code ExistCustomer} check if customer id already register into database
	 * @param username
	 * @return
	 */
	public static boolean ExistCustomer(String username) {
		
		try{
			 connection = DriverManager.getConnection("jdbc:sqlite:" + database);
	         Statement statement = connection.createStatement();
	         statement.setQueryTimeout(5);  // set timeout to 5 sec.
	         ResultSet result = statement.executeQuery("SELECT * from customer WHERE username = '" + username + "'");
	         return result.next();
		}
		catch(SQLException e)
      {
        System.err.println(e.getMessage());
        return false;
      }
	}
	
	/**
	 * {@code ExistEmployee} check if employee id already register into database
	 * @param username
	 * @return
	 */
	public static boolean ExistEmployee(String username) {
		
		try{
			 connection = DriverManager.getConnection("jdbc:sqlite:" + database);
	         Statement statement = connection.createStatement();
	         statement.setQueryTimeout(5);  // set timeout to 5 sec.
	         ResultSet result = statement.executeQuery("SELECT * from 'employee' WHERE username = '" + username + "'");
	         return result.next();
		}
		catch(SQLException e)
      {
        System.err.println(e.getMessage());
        return false;
      }
	}
	
	/**
	 * {@code ExistAdmin} check if admin id already register into database
	 * @param username
	 * @return
	 */
	public static boolean ExistAdmin(final String username) {
		try{
			 connection = DriverManager.getConnection("jdbc:sqlite:" + database);
	         Statement statement = connection.createStatement();
	         statement.setQueryTimeout(5);  // set timeout to 5 sec.
	         ResultSet result = statement.executeQuery("SELECT * from 'admin' WHERE username = '" + username + "'");
	         return result.next();
		}
		catch(SQLException e)
     {
       System.err.println(e.getMessage());
       return false;
     }
	}
	
	/**
	 * {@code customerValidation} check if credential is correct for validation
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean customerValidation(String username,String password) {
		try{
			 connection = DriverManager.getConnection("jdbc:sqlite:" + database);
	         Statement statement = connection.createStatement();
	         statement.setQueryTimeout(5);  // set timeout to 5 sec.
	         ResultSet result = statement.executeQuery("SELECT * from customer WHERE username = '" + username + "' AND password = '" + HashPassword(password) + "'");
	         return result.next();
		}
		catch(SQLException e)
     {
			
       System.err.println(e.getMessage());
       return false;
     }
  }
	/**
	 * {@code employeeValidation} check if credential is correct for validation
	 * @param username
	 * @param password
	 * @return
	 */
    public static boolean employeeValidation(String username,String password) {
		try{
			 connection = DriverManager.getConnection("jdbc:sqlite:" + database);
	         Statement statement = connection.createStatement();
	         statement.setQueryTimeout(5);  // set timeout to 5 sec.
	         ResultSet result = statement.executeQuery("SELECT * from employee WHERE username = '" + username + "' AND password = '" + HashPassword(password) + "'");
	         return result.next();
		}
		catch(SQLException e)
     {
			
       System.err.println(e.getMessage());
       return false;
     }
  }
	/**
	 * {@code adminValidation} check if credential is correct for validation
	 * @param username
	 * @param password
	 * @return
	 */
    public static boolean adminValidation(String username,String password) {
		try{
			 connection = DriverManager.getConnection("jdbc:sqlite:" + database);
	         Statement statement = connection.createStatement();
	         statement.setQueryTimeout(5);  // set timeout to 5 sec.
	         ResultSet result = statement.executeQuery("SELECT * from admin WHERE username = '" + username + "' AND password = '" + HashPassword(password) + "'");
	         return result.next();
		}
		catch(SQLException e)
     {
			
       System.err.println(e.getMessage());
       return false;
     }
  }
    
    /**
     * {@code addUserForNotify} add user into notify list 
     * @param username
     * @param wine
     * @return
     */
    public static boolean addUserForNotify(String username,Wine wine) {

		try{
			 connection = DriverManager.getConnection("jdbc:sqlite:" + database);
			 PreparedStatement statement = connection.prepareStatement("INSERT INTO notify(username,wineName) VALUES (?,?);");
	         statement.setQueryTimeout(5);  // set timeout to 5 sec.
	        
	         statement.setString(1, username);
	         statement.setString(2, wine.getName());
	         statement .executeUpdate();
	         if(connection != null)
	              connection.close();
	              return true;
		}
		catch(SQLException e)
       {
			
         System.err.println(e.getMessage());
       }
		return false;
	} 
    
    /**
     * {@code sendNotification} get user in waiting for specific wine
     * @param wine
     * @return List of username
     */
    public static List<String> sendNotification(Wine wine) {
    	
    	List<String> allnotify = new ArrayList<String>();
    	try{
			 connection = DriverManager.getConnection("jdbc:sqlite:" + database);
	         Statement statement = connection.createStatement();
	         Statement statement2 = connection.createStatement();
	         statement.setQueryTimeout(5);  // set timeout to 5 sec.
	         ResultSet result = statement.executeQuery("SELECT * from notify WHERE wineName = '" + wine.getName() + "'");
	         while(result.next()) {
	        	 allnotify.add(result.getString("username"));
	         }
	         statement2.executeUpdate("DELETE from notify WHERE wineName = '" + wine.getName() + "'");
	         if(connection != null)
	              connection.close();
	         return allnotify;
		   }
		catch(SQLException e)
		{
			 System.err.println(e.getMessage());
		        return null;
		}
    	
    }
    
    /**
     * {@code addOrder} add new order into table
     * @param username
     * @param wine
     * @param quantity
     * @return
     */
    public static Boolean addOrder(String username,Wine wine,Integer quantity) {
    	
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    	LocalDateTime now = LocalDateTime.now();  
		try{
			 connection = DriverManager.getConnection("jdbc:sqlite:" + database);
			 PreparedStatement statement = connection.prepareStatement("INSERT INTO `ordered` (username,winename,quantity,date) VALUES (?,?,?,?);");
	         statement.setQueryTimeout(5);  // set timeout to 5 sec.
	        
	         statement.setString(1, username);
	         statement.setString(2, wine.getName());
	         statement.setInt(3,quantity);
	         statement.setString(4,dtf.format(now));
	         statement .executeUpdate();

	         if(connection != null)
	              connection.close();
	              return true;
		}
		catch(SQLException e)
      {
			
        System.err.println(e.getMessage());
      }
		return false;
    	
    }
    
    /**
     * {@code searchBottlesByYear} get all wine with specifical year
     * @param year
     * @return List of Wine
     */
    public static List<Wine> searchBottlesByYear(Integer year) {
    	
    	List<Wine> wines = new ArrayList<Wine>(); 
    	try{
			 connection = DriverManager.getConnection("jdbc:sqlite:" + database);
	         Statement statement = connection.createStatement();
	         statement.setQueryTimeout(5);  // set timeout to 5 sec.
	         ResultSet result = statement.executeQuery("SELECT * from wine WHERE year = " + year);
	         while(result.next()) {
	        	 Wine wine = new Wine(result.getString("name"),result.getInt("year"),result.getString("technicalNotes"),result.getString("wineType"),result.getString("wineProducer"));   //create istance Wine with result query
	        	 wines.add(wine); 
	         }
	         return wines;
	         
		   }
		catch(SQLException e)
		{
			 System.err.println(e.getMessage());
		        return null;
		}
    	
    }
    
    /**
     * {@code searchBottlesByName} get wine with specifical name
     * @param name
     * @return Wine Instance
     */
    public static Wine searchBottlesByName(String name) {
    	Wine wine = null;
    	try{
			 connection = DriverManager.getConnection("jdbc:sqlite:" + database);
	         Statement statement = connection.createStatement();
	         statement.setQueryTimeout(5);  // set timeout to 5 sec.
	         ResultSet result = statement.executeQuery("SELECT * from wine WHERE name = " + name);
	         while(result.next()) {
	        	wine = new Wine(result.getString("name"),result.getInt("year"),result.getString("technicalNotes"),result.getString("wineType"),result.getString("wineProducer"));   //create istance Wine with result query
	        	 
	         }
	         return wine;
		   }
		catch(SQLException e)
		{
			 System.err.println(e.getMessage());
		        return null;
		}
    	
    }
}


