package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import it.mulaz1.winery.classes.Services.Database;
/**
 * 
 *{@code Main} is starting class of application, with start method 
 *
 */
public class Main extends Application {
	
	  private Text winetitle;
	  public static Stage window;
	  public static String username;
	  
	  @Override
	  public void start(Stage primaryStage) throws IOException {

		Database.Connect();
		window = primaryStage;
		System.out.println("GUI Starting...");
		Parent loginPage = FXMLLoader.load(getClass().getResource("../login.fxml"));		
		//Build scene
        Scene loginScene = new Scene(loginPage,500,500);
        
        window.setTitle("Winery GUI");
        window.setScene(loginScene); 
        window.show();
	
    }
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
