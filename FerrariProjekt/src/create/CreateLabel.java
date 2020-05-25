package create;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import styles.JavaFXStyles;

public class CreateLabel {

	public Label lb() {
		Label lb = new Label();
		lb.setStyle(JavaFXStyles.labelStyle1);
		return lb;
	}
	// Lav label med 1 parameter (TEKST)
	public Label lb(String text) {
		Label lb = new Label(text);
		lb.setStyle(JavaFXStyles.labelStyle1);
		return lb;
	}

	// Lav label med 2 parametre (TEKST og TEKSTSIZE)
	public Label lb(String text, double textsize) {
		Label lb = new Label(text);
		lb.setStyle(JavaFXStyles.labelStyle1);
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
