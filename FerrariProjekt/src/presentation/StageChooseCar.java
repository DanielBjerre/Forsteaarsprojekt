package presentation;

import create.CreateButton;
import entities.Car;
import entities.Offer;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.CarLogic;

public class StageChooseCar {
	Stage stage;
	CreateButton cb = new CreateButton();
	CarLogic cl = new CarLogic();
	
	public void init(Stage stage, Stage baseStage, Offer offer, TextField tfCarModel, TextField tfCarPrice) {
		this.stage = stage;
		double buttonHeight = baseStage.getHeight()/20;
		double buttonWidth = baseStage.getWidth()/15;

		// Setup BorderPane
		Insets insets = new Insets(5, 5, 5, 5);
		BorderPane root = new BorderPane();
		root.setPadding(insets);
		VBox vBoxCenter = new VBox(20);
		root.setCenter(vBoxCenter);
		vBoxCenter.setAlignment(Pos.TOP_CENTER);
		root.setStyle("-fx-border-color: black ;-fx-border-width: 5 ;");
		
		// Comboboxes for filtering cars
		HBox hBoxChoice = new HBox();
		ComboBox cbUsed = new ComboBox();
		ComboBox cbModel = new ComboBox();
		hBoxChoice.getChildren().addAll(cbUsed, cbModel);

		// Create tableview
		TableView<Car> tvCar = new TableView<Car>();
		
		// Create tableview columns
		TableColumn<Car, String> clmCondition = new TableColumn<>("Stand");
		TableColumn<Car, String> clmSerialNumber = new TableColumn<>("Serienummer");
		TableColumn<Car, String> clmModel = new TableColumn<>("Model");
		TableColumn<Car, String> clmModelYear = new TableColumn<>("Årgang");
		TableColumn<Car, String> clmColor = new TableColumn<>("Farve");
		TableColumn<Car, String> clmMileage = new TableColumn<>("Kilometer");
		TableColumn<Car, String> clmPrice = new TableColumn<>("Pris");
		
		// Add columns to tableview
		tvCar.getColumns().addAll(clmCondition, clmSerialNumber, clmModel, clmModelYear, clmColor, clmMileage, clmPrice);
		
		// Add values to columns
		  clmCondition.setCellValueFactory(cellData -> {
	            boolean condition = cellData.getValue().isUsed();
	            String conditionAsString;
	            if(condition == true) {
	                conditionAsString = "Brugt";
	            }
	            else {
	                conditionAsString = "Ny";
	            }
	         return new ReadOnlyStringWrapper(conditionAsString);
	        });
		clmSerialNumber.setCellValueFactory(new PropertyValueFactory<Car, String>("serialNumber"));
		clmModel.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
		clmModelYear.setCellValueFactory(new PropertyValueFactory<Car, String>("modelYear"));
		clmColor.setCellValueFactory(new PropertyValueFactory<Car, String>("color"));
		clmMileage.setCellValueFactory(new PropertyValueFactory<Car, String>("mileage"));
		clmPrice.setCellValueFactory(new PropertyValueFactory<Car, String>("price"));
				
		// Add items to tableview
		ObservableList<Car> olCar = FXCollections.observableList(cl.getOriginalList());
		tvCar.setItems(olCar);
		
		// Buttons below tableview
		HBox hBoxButtons = new HBox(20);
		Button btnClose = cb.btn("Luk", buttonWidth, buttonHeight);
		btnClose.setOnAction(e -> {
			stage.close();	
		});
		Button btnChoose = cb.btn("Vælg bil", buttonWidth, buttonHeight);
		btnChoose.setOnAction(e -> {
			if(tvCar.getSelectionModel().getSelectedItem() != null) {
			offer.setOfferCar(tvCar.getSelectionModel().getSelectedItem());
			tfCarModel.setText(tvCar.getSelectionModel().getSelectedItem().getModel());
			tfCarPrice.setText(tvCar.getSelectionModel().getSelectedItem().getPrice());
			stage.close();
			}
		});
		hBoxButtons.getChildren().addAll(btnClose, btnChoose);
		hBoxButtons.setAlignment(Pos.CENTER);
		
		
		vBoxCenter.getChildren().addAll(hBoxChoice, tvCar, hBoxButtons);
		
		Scene scene = new Scene (root, baseStage.getWidth()/1.5, baseStage.getHeight()/1.5);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initStyle(StageStyle.UNDECORATED);
		
		stage.setScene(scene);
		stage.show();
	}
	
	
	
	
}

