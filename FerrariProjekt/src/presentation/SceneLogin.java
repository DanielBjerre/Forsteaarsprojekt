package presentation;

import create.CreateButton;
import exception.IncorrectInputException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Login;

public class SceneLogin {
	Stage stage;
	TextField tfUsername, tfPassword, tfError;

	public void init(Stage stage) {
		this.stage = stage;
		CreateButton cb = new CreateButton();

		// Setup BorderPane
		Insets insets = new Insets(5, 5, 5, 5);
		BorderPane root = new BorderPane();
		root.setPadding(insets);
		VBox vBoxCenter = new VBox(20);
		root.setCenter(vBoxCenter);
		vBoxCenter.setAlignment(Pos.TOP_CENTER);

		// Add to VBoxCenter
		tfUsername = new TextField();
		tfUsername.setPromptText("Username");
		tfPassword = new TextField();
		tfPassword.setPromptText("Password");
		tfError = new TextField();
		Button btnLogin = cb.btn("Login");
		btnLogin.setOnAction(e -> {
			if (tfUsername.getText().isEmpty() || tfPassword.getText().isEmpty()) {
				errorMessage(new IncorrectInputException("Brugernavn eller adgangskode ikke udfyldt"));
			} else {
				login();
			}
		});
		Button btnClose = cb.btn("Luk");
		btnClose.setOnAction(e -> {
			stage.close();
		});
		
		vBoxCenter.getChildren().addAll(tfUsername, tfPassword, tfError, btnLogin,btnClose);

		// TESTING PURPOSES
		tfUsername.setText("username");
		tfPassword.setText("password");
		
		
		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		stage.setScene(scene);
		stage.show();
	}

	private void login() {
		try {
			Login login = new Login();
			login.login(tfUsername.getText(), tfPassword.getText());
			SceneMainMenu scHM = new SceneMainMenu();
			scHM.init(stage);
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	private void errorMessage(Exception e) {
		System.out.println(e.getMessage());
		tfError.setText(e.getMessage());
	}
}
