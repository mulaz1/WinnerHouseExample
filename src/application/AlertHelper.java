/**
 * Application contains every part of graphic of application 
 */
package application;

import javafx.scene.control.Alert;
import javafx.stage.Window;

/**
 * {@code AlerHelper} create alert window
 * 
 *
 */
public class AlertHelper {

	/**
	 * {@code showAlert} create Javafx alert with specifical message
	 * @param alertType
	 * @param title
	 * @param message
	 */
    public static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}