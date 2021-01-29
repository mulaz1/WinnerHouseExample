package it.mulaz1.winery.classes.Services;

import it.mulaz1.winery.classes.Users.Customer;
import javafx.scene.control.Alert;
import it.mulaz1.winery.classes.Wine;

import java.util.ArrayList;
import java.util.List;

import application.AlertHelper;


/** 
 * {@code NotificationService} is use to add customers in a subscribe list and wait that wine is available again 
 * when the wine becomes available again the system sends a message to all customers who were waiting for this wine in particular
 *
 */
public class NotificationService {
	
	/**
	 * @param username Customer username to add in waiting room
	 * @param wine	   Specify which is the wine 
	 * @return 		   True or False
	 */
    public Boolean addSubscriber(final String username, final Wine wine) {

    	return(Database.addUserForNotify(username,wine));
    	
    }
    
    /**
     * {@code botifyAll} simulates the sending of an email to specific customer in notify list
     * @param wine  Specify which is the wine 
     */
    
    public void notifyAll(final Wine wine) {
    	for(String user : Database.sendNotification(wine)) {
    		AlertHelper.showAlert(Alert.AlertType.INFORMATION,"Sent notification to customers via email","Message : Ehi " + user + " great news for you," + wine.getName() + "is return in stock");
    	}
    }        
}
