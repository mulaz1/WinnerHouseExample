package application;

import it.mulaz1.winery.classes.Users.Customer;
import it.mulaz1.winery.classes.Users.Employee;
import it.mulaz1.winery.classes.WineCellar.WineCellar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * 
 * {@code LoginController} is controller class for login page
 *
 */
public class LoginController {

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
	    
	    
	    public static void initialize() {
	    	
	    	application.Main.username = null;
	    }
	    
	    /**
	     * {@code loginSubmit} When Login is clicked get all Field and check if are valid
	     * @param event
	     */
	    @FXML
        protected void loginSubmit(ActionEvent event) {
	        
        	Window owner = submitButton.getScene().getWindow();
	        if(usernameField.getText().isEmpty()) {
	            AlertHelper.showAlert(Alert.AlertType.ERROR, "Form Error!", 
	                    "Please enter your name");
	            return;
	        }
	       
	        if(passwordField.getText().isEmpty()) {
	            AlertHelper.showAlert(Alert.AlertType.ERROR, "Form Error!", 
	                    "Please enter a password");
	            return;
	        }
	        if(WineCellar.ValidationUser(usernameField.getText(),passwordField.getText()))
	        {
	        	application.Main.username = usernameField.getText();
	        	 try {
	            	    Parent mainPage = FXMLLoader.load(getClass().getResource("../main.fxml"));
	            	    Scene mainScene = new Scene(mainPage, 920,600);
	            	    Stage stage = new Stage();
	            	    //stage.setScene(loginScene); 
	            	    application.Main.window.setScene(mainScene);
	            	   } 
	              catch(Exception e) {
	            	       e.printStackTrace();
	                   }	
	        }
	        else
	        {
		        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, "Authentication Error", 
                "Try Again");  
	        }  
	     }
        
	    /**
	     * {@code logonClicked} open register page
	     */
        @FXML
        private void LogonClicked() {
        	 try {
           	    Parent registerPage = FXMLLoader.load(getClass().getResource("../register.fxml"));
           	    Scene registerScene = new Scene(registerPage, 500,500);
           	    Stage stage = new Stage();
           	    //stage.setScene(loginScene); 
           	    application.Main.window.setScene(registerScene);
           	   } 
             catch(Exception e) {
           	       e.printStackTrace();
                  }	
        	
        }
}
