package create;

import javafx.scene.control.Button;

public class CreateButton {

	public Button btn(String knapNavn) {
		Button btn = new Button(knapNavn);
		btn.setPrefSize(Constants.stageWidth/10, Constants.stageHeight/15);
		return btn;
}
	public Button btn(String knapNavn, double width, double height) {
		double buttonWidth = (Constants.stageWidth/20)*width;
		double buttonHeigth = (Constants.stageHeight/20)*height;
		Button btn = new Button(knapNavn);
		btn.setPrefSize(buttonWidth, buttonHeigth);
		return btn;
	}
	
}
