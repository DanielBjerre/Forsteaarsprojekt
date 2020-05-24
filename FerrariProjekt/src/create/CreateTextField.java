package create;

import javafx.scene.control.TextField;
import styles.JavaFXStyles;

public class CreateTextField {
	
	public TextField tf() {
		TextField tf = new TextField();
		tf.setPrefSize(Constants.stageWidth/10, Constants.stageHeight/25);
		tf.setStyle(JavaFXStyles.fieldStyle1);
		return tf;
	}
	// Lav TextField med 1 parameter (TEKST)
	public TextField tf(String text) {
		TextField tf = new TextField();
		tf.setPrefSize(Constants.stageHeight/15, Constants.stageWidth/20);
		tf.setText(text);
		return tf;
	}

	// Lav TextField med 2 parametre (TEKST og TEKSTSIZE)
	public TextField tf(String text, double textsize) {
		TextField tf = new TextField();
		tf.setText(text);
		String style = "-fx-font-size: " + textsize + "px;";
		tf.setStyle(style);
		return tf;
	}

	// Lav TextField med 3 parametre (TEKST, TEKSTSIZE, PROMT)
	public TextField tf(String text, double textsize, boolean prompt) {
		TextField tf = new TextField();
		tf.setText(text);
		String style = "-fx-font-size: " + textsize + "px;";
		tf.setStyle(style);
		tf.setPromptText(text);
		return tf;
	}
}
