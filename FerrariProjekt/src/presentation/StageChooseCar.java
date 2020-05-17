package presentation;

import create.CreateButton;
import entities.Car;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
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
	
	public void init(Stage stage, Stage baseStage) {
		this.stage = stage;
		double buttonHeight = stage.getHeight()/10;
		double buttonWidth = stage.getWidth()/10;

		// Setup BorderPane
		Insets insets = new Insets(5, 5, 5, 5);
		BorderPane root = new BorderPane();
		root.setPadding(insets);
		VBox vBoxCenter = new VBox(20);
		root.setCenter(vBoxCenter);
		vBoxCenter.setAlignment(Pos.TOP_CENTER);
		root.setStyle("-fx-border-color: black ;-fx-border-width: 5 ;");
		
		HBox hBoxChoice = new HBox();
		ComboBox cbUsed = new ComboBox();
		ComboBox cbModel = new ComboBox();
		hBoxChoice.getChildren().addAll(cbUsed, cbModel);

		TableView<Car> tvCar = new TableView<Car>();
		ObservableList<Car> olCar = FXCollections.observableList(cl.getOriginalList());
		tvCar.setItems(olCar);
		System.out.println(olCar.size());
		
		Button btnClose = cb.btn("Luk", buttonWidth, buttonHeight);
		btnClose.setOnAction(e -> {
			stage.close();	
		});
		Button btnChoose = cb.btn("Vælg bil", buttonWidth, buttonHeight);
		
		
		vBoxCenter.getChildren().addAll(hBoxChoice, tvCar, btnClose, btnChoose);
		
		Scene scene = new Scene (root, baseStage.getWidth()/1.5, baseStage.getHeight()/1.5);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initStyle(StageStyle.UNDECORATED);
		
		stage.setScene(scene);
		stage.show();
	}
	
	
	
	
}

