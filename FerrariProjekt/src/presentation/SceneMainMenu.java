package presentation;

import create.Constants;
import create.CreateButton;
import create.CreateLabel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import styles.JavaFXStyles;

public class SceneMainMenu {
	private Stage stage;
	private CreateLabel cl = new CreateLabel();
	private CreateButton cb = new CreateButton();

	public void init(Stage stage) {
		this.stage = stage;

		// SETUP
		BorderPane root = new BorderPane();
		VBox vBoxCenter = new VBox(30);
		root.setCenter(vBoxCenter);
		root.setStyle(JavaFXStyles.backgroundStyle1);
		vBoxCenter.setAlignment(Pos.CENTER);
		vBoxCenter.setMaxSize(stage.getWidth() / 3, stage.getHeight() * 0.65);
		vBoxCenter.setStyle(JavaFXStyles.backgroundStyle2);

		// Buttons and Labels
		Label lbTitle = cl.lb("Ferrari Financing", Constants.textSize * 2);

		// Create offer button
		Button btnCreateOffer = cb.btn("Opret Tilbud", 6, 2);
		btnCreateOffer.setOnAction(e -> {
			SceneCreateOffer scOT = new SceneCreateOffer();
			scOT.init(stage);
		});

		// Look up offers button
		Button btnLookUp = cb.btn("Se tilbud", 6, 2);
		btnLookUp.setOnAction(e -> {
			SceneLookUpOffer scLUO = new SceneLookUpOffer();
			scLUO.init(stage);
		});

		// Log off button
		Button btnLogOff = cb.btn("Log af", 6, 2);
		btnLogOff.setOnAction(e -> {
			SceneLogin scLogin = new SceneLogin();
			scLogin.init(stage);
		});

		// Close program button
		Button btnClose = cb.btn("Luk", 6, 2);
		btnClose.setOnAction(e -> {
			stage.close();
		});

		// Add to center vBox
		vBoxCenter.getChildren().addAll(lbTitle, btnCreateOffer, btnLookUp, btnLogOff, btnClose);

		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		stage.setScene(scene);

	}
}
