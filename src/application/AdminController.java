package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import it.mulaz1.winery.classes.Order;
import it.mulaz1.winery.classes.Stock;
import it.mulaz1.winery.classes.Wine;
import it.mulaz1.winery.classes.Users.Customer;
import it.mulaz1.winery.classes.Users.Employee;
import it.mulaz1.winery.classes.WineCellar.*;


/**
 * 
 * {@code AdminController} menage all control into adminPage
 *
 */
public class AdminController {


@FXML
private TableView customerTable;

@FXML
private TableView employeeTable;

@FXML
private TableView stockTable;

@FXML
private TableView orderTable;

//Employee Fields
@FXML
private TextField nameField;

@FXML 
private TextField usernameField;

@FXML
private PasswordField passwordField;

@FXML
private TextField emailField;

@FXML
private Button submitButton;

//wine fields

@FXML
private TextField nameWineField;

@FXML 
private TextField yearField;

@FXML
private TextField vineTypeField;

@FXML 
private TextField wineProducerField;

@FXML 
private TextField TechnicalNoteField;


/**
 * {@code initialize} initialize customer,employee,stock and order tables
 */
@FXML
private void initialize()  {	
	System.out.println("Init Customer table");
	
	TableColumn<Customer, String> colName =  new TableColumn("Name");
	TableColumn<Customer, String> colEmail =  new TableColumn("Email");
	TableColumn<Customer, String> colUsername =  new TableColumn("Username");
	
	customerTable.getColumns().addAll(colName,colUsername,colEmail);
	
	ObservableList<Customer> customerList = FXCollections.observableArrayList(WineCellar.GetCustomers());
	colName.setCellValueFactory(new PropertyValueFactory<>("name"));
	colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
	colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
	
	customerTable.setItems(customerList);
	
	
	System.out.println("Init Employee table");
	
	TableColumn<Employee, String> colNameEmployee =  new TableColumn("Name");
	TableColumn<Employee, String> colEmailEmployee =  new TableColumn("Email");
	TableColumn<Employee, String> colUsernameEmployee =  new TableColumn("Username");
	
	employeeTable.getColumns().addAll(colNameEmployee,colUsernameEmployee,colEmailEmployee);
	
	ObservableList<Employee> employeeList = FXCollections.observableArrayList(WineCellar.GetEmployees());
	colNameEmployee.setCellValueFactory(new PropertyValueFactory<>("name"));
	colEmailEmployee.setCellValueFactory(new PropertyValueFactory<>("email"));
	colUsernameEmployee.setCellValueFactory(new PropertyValueFactory<>("username"));
	
	employeeTable.setItems(employeeList);
	
	
	
	System.out.println("Init Stock list...");
	TableColumn<Stock, String> colNameWine =  new TableColumn("Name");
	TableColumn<Stock,Integer> colYear =  new TableColumn("Year");
	TableColumn<Stock, String> colWineProducer =  new TableColumn("Wine Producer");
	TableColumn<Stock, String> colVineType =  new TableColumn("Vine Type");
	TableColumn<Stock, String> colTechnicalNote =  new TableColumn("Technical Note");
	TableColumn<Stock, Integer> colQuantity =  new TableColumn("Quantity");
	
	
    // Enable single cell selection
    stockTable.getSelectionModel (). setSelectionMode (SelectionMode.SINGLE);
    stockTable.getSelectionModel (). setCellSelectionEnabled (true);
    
    colQuantity.setEditable(true);
    
	stockTable.getColumns().addAll(colNameWine,colYear,colWineProducer,colVineType,colTechnicalNote,colQuantity);
    
	ObservableList<Stock> stockList = FXCollections.observableArrayList(WineCellar.getStock());
	colNameWine.setCellValueFactory(new PropertyValueFactory<>("name"));
	colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
	colWineProducer.setCellValueFactory(new PropertyValueFactory<>("WineProducer"));
	colTechnicalNote.setCellValueFactory(new PropertyValueFactory<>("TechnicalNotes"));
	colVineType.setCellValueFactory(new PropertyValueFactory<>("VineType"));
	colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));	
	stockTable.setItems(stockList);		
	
	System.out.println("Init Order Table");
	
	TableColumn<Order, String> colDateOrder =  new TableColumn("Date");
	TableColumn<Order, String> colUsernameOrder =  new TableColumn("Username");
	TableColumn<Order, Integer> colQuantityOrder =  new TableColumn("quantity");
	TableColumn<Order, String> colWineNameOrder =  new TableColumn("WineName");

	
	orderTable.getColumns().addAll(colDateOrder,colUsernameOrder,colWineNameOrder,colQuantityOrder);
	
	ObservableList<Order> orderList = FXCollections.observableArrayList(WineCellar.getOrders());
	colWineNameOrder.setCellValueFactory(new PropertyValueFactory<>("wineName"));
	colDateOrder.setCellValueFactory(new PropertyValueFactory<>("Date"));
	colQuantityOrder.setCellValueFactory(new PropertyValueFactory<>("quantity"));
	colUsernameOrder.setCellValueFactory(new PropertyValueFactory<>("username"));

	orderTable.setItems(orderList);	
}

@FXML
/**
 * {@code submitEmployee} when submit is clicked this method get all fields and register new employee
 */
private void submitEmployee() {
    
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
	   Employee employee = new Employee(nameField.getText(),usernameField.getText(),emailField.getText(),passwordField.getText());
	   WineCellar.registerEmployee(employee);
	   
	try {
   	    Parent adminPage = FXMLLoader.load(getClass().getResource("../main.fxml"));
   	    Scene adminScene = new Scene(adminPage, 930,600);
   	    application.Main.window.setScene(adminScene);
   	   } 
     catch(Exception e) {
   	       e.printStackTrace();
   	      }
	}

}
