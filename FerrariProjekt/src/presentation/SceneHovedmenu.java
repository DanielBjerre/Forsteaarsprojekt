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

public class SceneHovedmenu {
	Stage stage;
	CreateLabel cl = new CreateLabel();
	CreateButton cb = new CreateButton();

	public void init(Stage stage) {
		this.stage = stage;

		double knapWidth = stage.getWidth()/4;
		double knapHeight = stage.getHeight()/20;
		double textsize = stage.getHeight()/40;

		// Opsætning
		Insets insets = new Insets(5, 5, 5, 5);
		BorderPane root = new BorderPane();
		root.setPadding(insets);
		VBox vBoxCenter = new VBox(20);
		root.setCenter(vBoxCenter);
		vBoxCenter.setAlignment(Pos.TOP_CENTER);

		// Lav knapper og labels
		Label lbTitel = cl.lb("Ferrari Financing", textsize*2);
		Button btnOpretTilbud = cb.btn("Opret Tilbud", knapWidth, knapHeight);
		Button btnLuk = cb.btn("Luk", knapWidth, knapHeight);

		// Knap funktioner
		btnOpretTilbud.setOnAction(e -> {
			SceneOpretTilbud scOT = new SceneOpretTilbud();
			scOT.init(stage);
		});
		
		btnLuk.setOnAction(e -> {
			stage.close();
		});

		// Tilføj tl VBox
		vBoxCenter.getChildren().addAll(lbTitel,btnOpretTilbud,btnLuk);

		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		stage.setScene(scene);

	}
}
