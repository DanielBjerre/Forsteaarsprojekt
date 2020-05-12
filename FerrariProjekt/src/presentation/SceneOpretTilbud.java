package presentation;

import create.CreateButton;
import create.CreateLabel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneOpretTilbud {
	Stage stage;
	CreateLabel cl = new CreateLabel();
	CreateButton cb = new CreateButton();

	public void init(Stage stage) {
		this.stage = stage;

		double knapWidth = stage.getWidth()/4;
		double knapHeight = stage.getHeight()/20;

		// Opsætning
		Insets insets = new Insets(5, 5, 5, 5);
		BorderPane root = new BorderPane();
		root.setPadding(insets);
		VBox vBoxCenter = new VBox(20);
		root.setCenter(vBoxCenter);
		vBoxCenter.setAlignment(Pos.TOP_CENTER);

		// Lav knapper og labels
		Label lbTitel = cl.lb("Opret Tilbud", 50);
		lbTitel.setPrefHeight(stage.getHeight()/20);
		Button btnTilbage = cb.btn("Tilbage", knapWidth, knapHeight);

		// Knap funktioner
		btnTilbage.setOnAction(e -> {
			SceneHovedmenu scHM = new SceneHovedmenu();
			scHM.init(stage);
		});

		// Tilføj tl VBox
		vBoxCenter.getChildren().addAll(lbTitel,btnTilbage);

		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		stage.setScene(scene);

	}
}
