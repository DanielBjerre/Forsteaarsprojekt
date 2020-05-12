package create;

import javafx.scene.control.Button;

public class CreateButton {

	public Button btn(String knapNavn) {
		Button btn = new Button(knapNavn);
		btn.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
		return btn;
}
	public Button btn(String knapNavn, double width, double height) {
		Button btn = new Button(knapNavn);
		btn.setPrefSize(width, height);
		return btn;
	}
	
}
