package presentation;

import create.Constants;
import create.CreateButton;
import create.CreateLabel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import styles.JavaFXStyles;

public class StagePopUp {
	Stage stage;
	CreateLabel cl = new CreateLabel();
	CreateButton cb = new CreateButton();

	public void init(Stage stage, String message, Stage baseStage) {
		// Setup BorderPane
		Insets insets = new Insets(15, 15, 15, 15);
		BorderPane root = new BorderPane();
		VBox vBoxCenter = new VBox(20);
		root.setCenter(vBoxCenter);
		vBoxCenter.setAlignment(Pos.CENTER);
		vBoxCenter.setPadding(insets);
		root.setStyle(JavaFXStyles.backgroundStyle1);

		Label lbMessage = cl.lb(message);
		lbMessage.setAlignment(Pos.CENTER);

		Button btnOk = cb.btn("Ok", 3, 1);
		btnOk.setAlignment(Pos.CENTER);
		btnOk.setOnAction(e -> {
			stage.close();
			if (baseStage != null) {
				SceneMainMenu scMM = new SceneMainMenu();
				scMM.init(baseStage);
			}
		});
		vBoxCenter.getChildren().addAll(lbMessage, btnOk);

		root.setCenter(vBoxCenter);

		Scene scene = new Scene(root, Constants.stageWidth / 5, Constants.stageHeight / 5);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initStyle(StageStyle.UNDECORATED);

		stage.setScene(scene);
		stage.show();
	}

}
