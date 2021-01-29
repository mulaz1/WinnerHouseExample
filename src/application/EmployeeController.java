package application;

import java.util.Optional;

import it.mulaz1.winery.classes.Stock;
import it.mulaz1.winery.classes.Wine;
import it.mulaz1.winery.classes.Users.Customer;
import it.mulaz1.winery.classes.WineCellar.WineCellar;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import javafx.util.Callback;
/**
 * 
 * {@code EmployeeController} is controller class for Employee page
 *
 */
public class EmployeeController {

	@FXML
	private TextField nameWineField;
	
	@FXML
	private TextField yearWineField;
	
	@FXML 
	private TextField technicalNoteField;
	
	@FXML
	private TextField vineTypeField;
	
	@FXML
	private TextField wineProducerField;
	
	@FXML
	private TextField quantityField;
	
	@FXML
	private Button submitButton;
	
	@FXML
	private TableView stockTable;
	
	/**
	 * {@code initialize} initialize stock table
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
        stockTable.getSelectionModel (). setSelectionMode (SelectionMode.SINGLE);
        stockTable.getSelectionModel (). setCellSelectionEnabled (true);
        
        colQuantity.setEditable(true);
        
		stockTable.getColumns().addAll(colName,colYear,colWineProducer,colVineType,colTechnicalNote,colQuantity);
        addButtonToTable();
        
		ObservableList<Stock> stockList = FXCollections.observableArrayList(WineCellar.getStock());
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
		colWineProducer.setCellValueFactory(new PropertyValueFactory<>("WineProducer"));
		colTechnicalNote.setCellValueFactory(new PropertyValueFactory<>("TechnicalNotes"));
		colVineType.setCellValueFactory(new PropertyValueFactory<>("VineType"));
		colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));	
		stockTable.setItems(stockList);	
	}
	
	/**
	 * {@code submitWine} get all fields and add new wine
	 */
	@FXML
	private void submitWine() {
	    
	    if(nameWineField.getText().isEmpty()) {
	        AlertHelper.showAlert(Alert.AlertType.ERROR, "Form Error!", 
	                "Please enter wine name");
	        return;
	    }
	    if(yearWineField.getText().isEmpty()) {
	        AlertHelper.showAlert(Alert.AlertType.ERROR, "Form Error!", 
	                "Please enter wine Year");
	        return;
	    }
	    if(vineTypeField.getText().isEmpty()) {
	        AlertHelper.showAlert(Alert.AlertType.ERROR, "Form Error!", 
	                "Please enter wine vine type");
	        return;
	    }
	    if(wineProducerField.getText().isEmpty()) {
	        AlertHelper.showAlert(Alert.AlertType.ERROR, "Form Error!", 
	                "Please enter wine Producer");
	        return;
	       
	    }
	   
	    Wine wine = new Wine(nameWineField.getText(),Integer.valueOf(yearWineField.getText()),technicalNoteField.getText(),vineTypeField.getText(),wineProducerField.getText());
	    WineCellar.addNewWineToCellar(wine);
	    WineCellar.putNewBottlesInWarehouse(wine,Integer.valueOf(quantityField.getText()));
 
		try {
	   	    Parent employeePage = FXMLLoader.load(getClass().getResource("../employee.fxml"));
	   	    Scene employeeScene = new Scene(employeePage, 920,600);
	   	    application.Main.window.setScene(employeeScene);
	   	   } 
	     catch(Exception e) {
	   	       e.printStackTrace();
	   	      }
	}
	
	/**
	 * Whit {@code closeEmployee} button return to mainpage 
	 */
	@FXML 
	private void closeEmployee() {
		try {
	   	    Parent mainPage = FXMLLoader.load(getClass().getResource("../main.fxml"));
	   	    Scene mainScene = new Scene(mainPage, 920,600);
	   	    application.Main.window.setScene(mainScene);
	   	 } 
	     catch(Exception e) {
	   	       e.printStackTrace();
	   	      }
	}
	
	
	private void addButtonToTable() {
        TableColumn<Stock, Void> colBtn = new TableColumn("Increment Stock");

        Callback<TableColumn<Stock, Void>, TableCell<Stock, Void>> cellFactory = new Callback<TableColumn<Stock, Void>, TableCell<Stock, Void>>() {
            @Override
            public TableCell<Stock, Void> call(final TableColumn<Stock, Void> param) {
                final TableCell<Stock, Void> cell = new TableCell<Stock, Void>() {
                private final Button btn = new Button("Increment Stock");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                           Stock data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            //alert with increment battle
                            TextInputDialog dialog = new TextInputDialog("Quantity");
                            dialog.setTitle("Add bottle to stock");
                            dialog.setHeaderText("Insert number of bottle");
                            dialog.setContentText("Please enter quantity:");

                            // Traditional way to get the response value.
                            Optional<String> result = dialog.showAndWait();
                            if (result.isPresent()){
                               WineCellar.putNewBottlesInWarehouse(data.getWine(),Integer.valueOf(result.get()));
                            }
                            ObservableList<Stock> stockList = FXCollections.observableArrayList(WineCellar.getStock());
                            stockTable.setItems(stockList);
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

        stockTable.getColumns().add(colBtn);

    }
	
}
