package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import it.mulaz1.winery.classes.Stock;
import it.mulaz1.winery.classes.Services.AuthService;
import it.mulaz1.winery.classes.WineCellar.WineCellar;
 
/**
 * 
 * {@code MainController} is controller of Main Page
 *
 */
public class MainController 
{
	
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
	 	private TableView wineryShowcase;
	 
	 /**
	  * {@code inizialize} init stock table
	  */
	 @FXML
	 private void initialize() {
			
			System.out.println("Init Stock list...");
			TableColumn<Stock, String> colName =  new TableColumn("Name");
			TableColumn<Stock,Integer> colYear =  new TableColumn("Year");
			TableColumn<Stock, String> colWineProducer =  new TableColumn("Wine Producer");
			TableColumn<Stock, String> colVineType =  new TableColumn("Vine Type");
			TableColumn<Stock, String> colTechnicalNote =  new TableColumn("Technical Note");
			TableColumn<Stock, Integer> colQuantity =  new TableColumn("Quantity");
			
			
	        // Enable single cell selection
			wineryShowcase.getSelectionModel (). setSelectionMode (SelectionMode.SINGLE);
			wineryShowcase.getSelectionModel (). setCellSelectionEnabled (true);
	        
	        colQuantity.setEditable(true);
	        
	        wineryShowcase.getColumns().addAll(colName,colYear,colWineProducer,colVineType,colTechnicalNote,colQuantity);
	        addBuyButton();
	        
			ObservableList<Stock> stockList = FXCollections.observableArrayList(WineCellar.getStock());
			colName.setCellValueFactory(new PropertyValueFactory<>("name"));
			colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
			colWineProducer.setCellValueFactory(new PropertyValueFactory<>("WineProducer"));
			colTechnicalNote.setCellValueFactory(new PropertyValueFactory<>("TechnicalNotes"));
			colVineType.setCellValueFactory(new PropertyValueFactory<>("VineType"));
			colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));	
			wineryShowcase.setItems(stockList);	
		}
     
    
	/**
	 * {@code RegisterClicked} open register page
	 */
    @FXML
    private void RegisterClicked() {
    	 System.out.println("Register"); 
         try {
     	    Parent registerPage = FXMLLoader.load(getClass().getResource("../register.fxml"));
     	    Scene registerScene = new Scene(registerPage, 500,500);
     	    application.Main.window.setScene(registerScene);
     	   } 
       catch(Exception e) {
     	       e.printStackTrace();
     	      }
    	 	
    }
	/**
	 * {@code LogoutClicked} remove access paramenter and go to  login page
	 */
    @FXML
    private void LogoutClicked() {

    	System.out.println("Logout");
    	application.Main.username = null;
    	try {
   	    	Parent loginPage = FXMLLoader.load(getClass().getResource("../login.fxml"));
        	    Scene loginScene = new Scene(loginPage, 500,500);
   	    	application.Main.window.setScene(loginScene);
   	    }
   	    catch(Exception e) {
   	    		System.out.println(e);
   	    }
    }
    
    /**
     * {@code About} open about page
     */
    
    @FXML
    private void About() {
    	System.out.println("About");
        try {
    	    Parent aboutPage = FXMLLoader.load(getClass().getResource("../about.fxml"));
    	    Scene aboutScene = new Scene(aboutPage,650,500);
    	    application.Main.window.setScene(aboutScene);
    	   } 
      catch(Exception e) {
    	       e.printStackTrace();
    	      }
    }
    
    /**
     * {@code AdminPage} open admin page
     */
    @FXML
    private void AdminPage() {
    	System.out.println("admin");
    	if(AuthService.isValidAdmin(application.Main.username))
    	{
	        try {
	    	    Parent adminPage = FXMLLoader.load(getClass().getResource("../admin.fxml"));
	    	    Scene adminScene = new Scene(adminPage, 930,600);
	    	    application.Main.window.setScene(adminScene);
	    	   } 
	           catch(Exception e) {
	    	       e.printStackTrace();
	    	   }
    	}
    	else {
    		AlertHelper.showAlert(Alert.AlertType.ERROR, "Authentication Error", 
                    "Permission Deniend");  
    	}
    }
    /**
     * {@code EmployeePage} open employee page
     */
    @FXML
    private void EmployeePage() {
    	System.out.println("employee");
    	if(AuthService.isValidAdmin(application.Main.username) || AuthService.isValidEmployee(application.Main.username))
    	{
    		try {
        	    Parent employeePage = FXMLLoader.load(getClass().getResource("../employee.fxml"));
        	    Scene employeeScene = new Scene(employeePage, 930,600);
        	    application.Main.window.setScene(employeeScene);
        	   } 
          catch(Exception e) {
        	       e.printStackTrace();
        	      }
    	}
    	else {
    		AlertHelper.showAlert(Alert.AlertType.ERROR, "Authentication Error", 
                    "Permission Deniend");  
    	}
        
    }
    
    private void addBuyButton() {
        TableColumn<Stock, Void> colBtn = new TableColumn("BuyWine");

        Callback<TableColumn<Stock, Void>, TableCell<Stock, Void>> cellFactory = new Callback<TableColumn<Stock, Void>, TableCell<Stock, Void>>() {
            @Override
            public TableCell<Stock, Void> call(final TableColumn<Stock, Void> param) {
                final TableCell<Stock, Void> cell = new TableCell<Stock, Void>() {
                private final Button btn = new Button("Buy Wine");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                           Stock data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            //alert with increment battle
                            TextInputDialog dialog = new TextInputDialog("How many??");
                            dialog.setTitle("thanks for choosing us");
                            dialog.setHeaderText("Insert number of bottle that you would purchase");
                            dialog.setContentText("Please enter quantity:");

                            // Traditional way to get the response value.
                            Optional<String> result = dialog.showAndWait();
                            if (result.isPresent()){
                               WineCellar.placeOrder(data.getWine(),Integer.valueOf(result.get()));
                            }
                            ObservableList<Stock> stockList = FXCollections.observableArrayList(WineCellar.getStock());
                            wineryShowcase.setItems(stockList);
                        });   
                    }
                    
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        wineryShowcase.getColumns().add(colBtn);

    }
}