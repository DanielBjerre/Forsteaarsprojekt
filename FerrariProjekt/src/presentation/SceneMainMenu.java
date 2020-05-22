package presentation;

import create.Constants;
import create.CreateButton;
import create.CreateLabel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.ActiveEmployee;

public class SceneMainMenu {
	Stage stage;
	CreateLabel cl = new CreateLabel();
	CreateButton cb = new CreateButton();

	public void init(Stage stage) {
		this.stage = stage;
				
		// SETUP
		Insets insets = new Insets(5, 5, 5, 5);
		BorderPane root = new BorderPane();
		root.setPadding(insets);
		VBox vBoxCenter = new VBox(20);
		root.setCenter(vBoxCenter);
		vBoxCenter.setAlignment(Pos.TOP_CENTER);
		TextField tfEmployee = new TextField();
		tfEmployee.setText(ActiveEmployee.getInstance().getEmployee().getFirstName());

		// Lav knapper og labels
		Label lbTitel = cl.lb("Ferrari Financing", Constants.textSize * 2);
		Button btnOpretTilbud = cb.btn("Opret Tilbud");
		Button btnLookUp = cb.btn("Se tilbud");
		btnLookUp.setOnAction(e -> {
			SceneLookUpOffer scLUO = new SceneLookUpOffer();
			scLUO.init(stage);
		});
		Button btnLuk = cb.btn("Luk");

		// Knap funktioner
		btnOpretTilbud.setOnAction(e -> {
			SceneCreateOffer scOT = new SceneCreateOffer();
			scOT.init(stage);
		});

		btnLuk.setOnAction(e -> {
			stage.close();
		});

		// Tilføj tl VBox
		vBoxCenter.getChildren().addAll(lbTitel, btnOpretTilbud, btnLookUp, btnLuk, tfEmployee);

		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		stage.setScene(scene);

	}
}
