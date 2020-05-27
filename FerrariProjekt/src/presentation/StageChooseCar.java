package presentation;

import java.util.ArrayList;

import create.CreateButton;
import create.CreateLabel;
import entities.Car;
import entities.Offer;
import exception.CustomException;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import logic.ListSort;
import logic.OfferLogic;
import styles.JavaFXStyles;

public class StageChooseCar {
	private CreateButton cb = new CreateButton();
	private CreateLabel clabel = new CreateLabel();
	private CarLogic cl = new CarLogic();
	private ListSort ls = new ListSort();
	private TableView<Car> tvCar;

	public void init(Stage stage, Stage baseStage, Offer offer, TextField tfCarModel, TextField tfCarPrice) {

		// Setup BorderPane
		Insets insets = new Insets(20, 100, 20, 100);
		BorderPane root = new BorderPane();
		VBox vBoxCenter = new VBox(20);
		root.setCenter(vBoxCenter);
		vBoxCenter.setAlignment(Pos.TOP_CENTER);
		vBoxCenter.setPadding(insets);
		root.setStyle(JavaFXStyles.backgroundStyle1);

		// Comboboxes for filtering cars
		HBox hBoxChoice = new HBox();
		Button btnAllCars = cb.btn("Alle biler", 2, 1);
		btnAllCars.setOnAction(e -> {
			populateTableView(cl.getOriginalList());
		});
		Button btnNewCars = cb.btn("Nye biler", 2, 1);
		btnNewCars.setOnAction(e -> {
			populateTableView(ls.sortCar(cl.getOriginalList(),false));
		});
		Button btnUsedCars = cb.btn("Brugte biler", 2, 1);
		btnUsedCars.setOnAction(e -> {
			populateTableView(ls.sortCar(cl.getOriginalList(),true));
		});
		hBoxChoice.getChildren().addAll(btnAllCars, btnNewCars, btnUsedCars);
		hBoxChoice.setSpacing(20);
		hBoxChoice.setAlignment(Pos.CENTER);
		
		// Create tableview
		TableView<Car> tvCar = new TableView<Car>();
		this.tvCar = tvCar;

		// Create tableview columns
		TableColumn<Car, String> clmCondition = new TableColumn<>("Stand");
		TableColumn<Car, String> clmSerialNumber = new TableColumn<>("Serienummer");
		TableColumn<Car, String> clmModel = new TableColumn<>("Model");
		TableColumn<Car, String> clmModelYear = new TableColumn<>("�rgang");
		TableColumn<Car, String> clmColor = new TableColumn<>("Farve");
		TableColumn<Car, String> clmMileage = new TableColumn<>("Kilometer");
		TableColumn<Car, String> clmPrice = new TableColumn<>("Pris");

		// Add columns to tableview
		tvCar.getColumns().addAll(clmCondition, clmSerialNumber, clmModel, clmModelYear, clmColor, clmMileage,
				clmPrice);

		// COLUMNS WIDTH AND ALIGNTMENT
		clmCondition.prefWidthProperty().bind(tvCar.widthProperty().multiply(0.15));
		clmCondition.setStyle("-fx-alignment: CENTER");
		clmSerialNumber.prefWidthProperty().bind(tvCar.widthProperty().multiply(0.14));
		clmSerialNumber.setStyle("-fx-alignment: CENTER");
		clmModel.prefWidthProperty().bind(tvCar.widthProperty().multiply(0.14));
		clmModel.setStyle("-fx-alignment: CENTER");
		clmModelYear.prefWidthProperty().bind(tvCar.widthProperty().multiply(0.14));
		clmModelYear.setStyle("-fx-alignment: CENTER");
		clmColor.prefWidthProperty().bind(tvCar.widthProperty().multiply(0.14));
		clmColor.setStyle("-fx-alignment: CENTER");
		clmMileage.prefWidthProperty().bind(tvCar.widthProperty().multiply(0.14));
		clmMileage.setStyle("-fx-alignment: CENTER");
		clmPrice.prefWidthProperty().bind(tvCar.widthProperty().multiply(0.14));
		clmPrice.setStyle("-fx-alignment: CENTER");
		
		// Add values to columns
		clmCondition.setCellValueFactory(cellData -> {
			return new ReadOnlyStringWrapper(cellData.getValue().isUsed() ? "Brugt" : "Ny");
		});
		clmSerialNumber.setCellValueFactory(new PropertyValueFactory<Car, String>("serialNumber"));
		clmModel.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
		clmModelYear.setCellValueFactory(new PropertyValueFactory<Car, String>("modelYear"));
		clmColor.setCellValueFactory(new PropertyValueFactory<Car, String>("color"));
		clmMileage.setCellValueFactory(new PropertyValueFactory<Car, String>("mileage"));
		clmPrice.setCellValueFactory(new PropertyValueFactory<Car, String>("price"));

		// Add items to tableview
		populateTableView(cl.getOriginalList());


		// Error label
		Label lbError = clabel.lb();
		


		// Buttons below tableview
		HBox hBoxButtons = new HBox(20);
		Button btnClose = cb.btn("Luk", 3, 1);
		btnClose.setOnAction(e -> {
			stage.close();
		});
		Button btnChoose = cb.btn("V�lg bil", 3, 1);
		btnChoose.setOnAction(e -> {
			if (tvCar.getSelectionModel().getSelectedItem() != null) {
				try {
					offer.setOfferCar(tvCar.getSelectionModel().getSelectedItem());
					new OfferLogic().validateCar(offer);
					tfCarModel.setText(tvCar.getSelectionModel().getSelectedItem().getModel());
					tfCarPrice.setText(tvCar.getSelectionModel().getSelectedItem().getPrice());
					new OfferLogic().checkOverOrUnderLimit(offer);
					
					stage.close();
				} catch (CustomException e2) {
					lbError.setText(e2.getMessage());
				}
			}
		});
		hBoxButtons.getChildren().addAll(btnClose, btnChoose);
		hBoxButtons.setAlignment(Pos.CENTER);

		vBoxCenter.getChildren().addAll(hBoxChoice, tvCar,lbError, hBoxButtons);

		Scene scene = new Scene(root, baseStage.getWidth() / 1.5, baseStage.getHeight() / 1.5);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initStyle(StageStyle.UNDECORATED);

		stage.setScene(scene);
		stage.show();
	}

	private void populateTableView(ArrayList<Car> arrayList) {
		ObservableList<Car> olCar = FXCollections.observableArrayList(arrayList);
		tvCar.setItems(olCar);
	}

}
