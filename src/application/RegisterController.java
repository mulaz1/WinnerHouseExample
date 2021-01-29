package application;

import javafx.event.ActionEvent;

import it.mulaz1.winery.classes.Services.*;
import it.mulaz1.winery.classes.Users.*;
import it.mulaz1.winery.classes.WineCellar.WineCellar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

/**
 * 
 * {@code RegisterController} id controller class for register page
 *
 */
public class RegisterController {

	public RegisterController() {		
	}
	 @FXML
	    private TextField nameField;

	    @FXML
	    private TextField emailField;
	    
	    @FXML
	    private TextField usernameField;

	    @FXML
	    private PasswordField passwordField;

	    @FXML
	    private Button submitButton;
	    
	    @FXML
	    private CheckBox employee;
	    
	    @FXML
	    private CheckBox customer;
	    
	    /**
	     * {@code closeRegister} return login page
	     */
	    @FXML
	    private void closeRegister() {
	    	try {
	    	Parent loginPage = FXMLLoader.load(getClass().getResource("../login.fxml"));
     	    Scene loginScene = new Scene(loginPage,500,500);
	    	application.Main.window.setScene(loginScene);
	    	}
	    	catch(Exception e) {
	    		System.out.println(e);
	    	}
	    	
	    }
	    /**
	     *  {@code handleSubmitButtonAction} when clicked register this method get all fields and register new customer
	     */
	    @FXML
	    protected void handleSubmitButtonAction(ActionEvent event) {
	        
	    	Window owner = submitButton.getScene().getWindow();
	        if(nameField.getText().isEmpty()) {
	            AlertHelper.showAlert(Alert.AlertType.ERROR, "Form Error!", 
	                    "Please enter your name");
	            return;
	        }
	        if(usernameField.getText().isEmpty()) {
	            AlertHelper.showAlert(Alert.AlertType.ERROR, "Form Error!", 
	                    "Please enter your name");
	            return;
	        }
	        if(emailField.getText().isEmpty()) {
	            AlertHelper.showAlert(Alert.AlertType.ERROR, "Form Error!", 
	                    "Please enter your email id");
	            return;
	        }
	        if(passwordField.getText().isEmpty()) {
	            AlertHelper.showAlert(Alert.AlertType.ERROR, "Form Error!", 
	                    "Please enter a password");
	            return;
	        }
	    	   Customer customer = new Customer(nameField.getText(),usernameField.getText(),emailField.getText(),passwordField.getText());
	    	   WineCellar.registerCustomer(customer);
	    	  	
	    	try {
	   	    	Parent loginPage = FXMLLoader.load(getClass().getResource("../login.fxml"));
	        	    Scene loginScene = new Scene(loginPage, 500,500);
	   	    	application.Main.window.setScene(loginScene);
	   	    }
	   	    catch(Exception e) {
	   	    		System.out.println(e);
	   	    }
	    }
	   
}