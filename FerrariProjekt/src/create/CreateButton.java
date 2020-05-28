package create;

import javafx.scene.control.Button;
import styles.JavaFXStyles;

public class CreateButton {

	public Button btn(String knapNavn) {
		Button btn = new Button(knapNavn);
		btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		JavaFXStyles.buttonStyle1(btn);
		return btn;
	}

	public Button btn(String knapNavn, double width, double height) {
		double buttonWidth = (Constants.stageWidth / 20) * width;
		double buttonHeigth = (Constants.stageHeight / 20) * height;
		Button btn = new Button(knapNavn);
		btn.setMinSize(buttonWidth, buttonHeigth);
		JavaFXStyles.buttonStyle1(btn);
		return btn;
	}

}
