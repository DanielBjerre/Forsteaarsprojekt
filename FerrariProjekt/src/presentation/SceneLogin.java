package presentation;

import create.CreateButton;
import create.CreateLabel;
import create.CreateTextField;
import exception.IncorrectInputException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Login;
import styles.JavaFXStyles;

public class SceneLogin {
	private Stage stage;
	private CreateLabel cl = new CreateLabel();
	private CreateTextField ctf = new CreateTextField();
	private Label lbError;
	private TextField tfUsername; 
	private PasswordField pfPassword;

	public void init(Stage stage) {
		this.stage = stage;
		CreateButton cb = new CreateButton();

		// Setup BorderPane
		Insets insets = new Insets(20, 20, 20, 20);
		BorderPane root = new BorderPane();
		VBox vBoxCenter = new VBox(20);
		vBoxCenter.setPadding(insets);
		root.setCenter(vBoxCenter);
		root.setStyle(JavaFXStyles.backgroundStyle1);
		vBoxCenter.setAlignment(Pos.CENTER);
		vBoxCenter.setMaxSize(stage.getWidth()/4, stage.getHeight()/4);
		vBoxCenter.setStyle(JavaFXStyles.backgroundStyle2);


		// GridPane login Details
		GridPane gp1 = new GridPane();
		gp1.setPadding(insets);
		gp1.setVgap(25);
		gp1.setHgap(25);

		// Labels
		Label lbUsername = cl.lb("Brugernavn:");
		gp1.add(lbUsername, 0, 0);
		
		Label lbPassword = cl.lb("Adgangskode:");
		gp1.add(lbPassword, 0, 1);
		
		// Fields
		tfUsername = ctf.tf();
		gp1.add(tfUsername, 1, 0);
	
		pfPassword = new PasswordField();
		pfPassword.setStyle(JavaFXStyles.fieldStyle1);
		gp1.add(pfPassword, 1, 1);
		
		// First column width
		ColumnConstraints column = new ColumnConstraints();
		column.setPercentWidth(55);
		gp1.getColumnConstraints().add(column);
		
		// Hbox that GridPane goes into
		HBox hBox = new HBox();
		hBox.getChildren().add(gp1);
		
		// Error message label
		lbError = cl.lb();
		lbError.setStyle(JavaFXStyles.labelStyleError);
		
		// Login button
		Button btnLogin = cb.btn("Login", 1, 0.8);
		btnLogin.setOnAction(e -> {
			if (tfUsername.getText().isEmpty() || pfPassword.getText().isEmpty()) {
				errorMessage(new IncorrectInputException("Brugernavn eller adgangskode ikke udfyldt"));
			} else {
				login();
			}
		});		
	
		// Close button
		Button btnClose = cb.btn("Luk", 1, 0.8);
		JavaFXStyles.buttonStyle1(btnClose);
		btnClose.setOnAction(e -> {
			stage.close();
		});
		
		// Hbox that buttons go into
		HBox hBox2 = new HBox();
		hBox2.getChildren().addAll(btnClose, btnLogin);
		hBox2.setAlignment(Pos.CENTER);
		hBox2.setSpacing(100);
		
		// Add everything to the center VBOX
		vBoxCenter.getChildren().addAll(hBox, lbError, hBox2);

		// TESTING PURPOSES
		tfUsername.setText("username");
		pfPassword.setText("password");
		
		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		stage.setScene(scene);
		stage.show();
	}

	private void login() {
		try {
			Login login = new Login();
			login.login(tfUsername.getText(), pfPassword.getText());
			SceneMainMenu scHM = new SceneMainMenu();
			scHM.init(stage);
		} catch (Exception e) {
			errorMessage(e);
		}
	}

	private void errorMessage(Exception e) {
		System.out.println(e.getMessage());
		lbError.setText(e.getMessage());
	}
}
