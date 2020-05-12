package create;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class CreateLabel {

	// Lav label med 1 parameter (TEKST)
	public Label lb(String text) {
		Label lb = new Label(text);
		return lb;
	}

	// Lav label med 2 parametre (TEKST og TEKSTSIZE)
	public Label lb(String text, double textsize) {
		Label lb = new Label(text);
		String style = "-fx-font-size: " + textsize + "px;";
		lb.setStyle(style);
		return lb;
	}

	// Lav label med 3 parametre (TEKST, TEKSTSIZE, ALIGNMENT)
	public Label lb(String text, double textsize, String alignment) {
		Label lb = new Label(text);
		String style = "-fx-font-size: " + textsize + "px;";
		lb.setStyle(style);
		if (alignment == "RIGHT") {
			lb.setMaxWidth(Double.MAX_VALUE);
			lb.setAlignment(Pos.BASELINE_RIGHT);
		}
		if (alignment == "CENTER") {
			lb.setMaxWidth(Double.MAX_VALUE);
			lb.setAlignment(Pos.CENTER);
		}
		return lb;
	}
}
