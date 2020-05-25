package styles;

import javafx.scene.control.Button;

public class JavaFXStyles {
	
	// Background Styles
	public static String backgroundStyle1 = "-fx-background-color: #ff2800;"
			+ "-fx-border-color: #333333;"
			+ "-fx-border-width: 7;";
	public static String backgroundStyle2 = "-fx-background-color: #999999;"
			+ "-fx-border-color: #333333;"
			+ "-fx-border-width: 5;";
	// Button Styles
	public static void  buttonStyle1(Button button) {
		String style1 = "-fx-background-color: #fff200;"
			+ "-fx-border-color: #111111;"
			+ "-fx-border-width: 1;"
			+ "-fx-font-size: " + create.Constants.textSize/2 + ";"
			+ "-fx-text-fill: #111111;"
			+ "-fx-font-weight: bold;";
		
		String style2 = "-fx-background-color: #009A4E;"
				+ "-fx-border-color: #111111;"
				+ "-fx-border-width: 3;"
				+ "-fx-font-size: " + create.Constants.textSize/2 + ";"
				+ "-fx-text-fill: #111111;"
				+ "-fx-font-weight: bold;";
		button.setStyle(style1);
		button.setOnMouseEntered(e -> {
			button.setStyle(style2);
		});
		button.setOnMouseExited(e -> {
			button.setStyle(style1);
		});
	}
		
			
	// Field Styles
	public static String fieldStyle1 = "-fx-background-color: #F2F2F2;"
			+ "-fx-font-size: " + create.Constants.textSize*0.75 + ";"
			+ "-fx-text-fill: #111111;";
//			+ "-fx-opacity: 1;";
	
	// Label Styles
	public static String labelStyle1 = "-fx-font-size: " + create.Constants.textSize + ";"
			+ "-fx-text-fill: #111111;";
			
	
	public static String labelStyleError = "-fx-font-size: " + create.Constants.textSize*0.6 + ";"
			+ "-fx-text-fill: #111111;";

	public static String labelError = "-fx-font-size: " + create.Constants.textSize + ";"
			+ "-fx-text-fill: #ff2800;"
			+ "-fx-text-stroke: #111111;"
			+ "-fx-text-stroke-width: 2 ;";
	
	// TableView Styles
	public static String tableviewStyle1 = "-fx-alignment: CENTER;";

	
	// HBox Styles
	public static String HBoxStyle = "-fx-border-style: none none solid solid;" 
			+ "-fx-border-width: 2;"
			+ "-fx-border-color: black;";
	
	// Combobox Styles
	public static String ComboBoxStyle1 = "-fx-border-width: 2;"
			+ "-fx-font-size: " + create.Constants.textSize + ";"
			+ "-fx-border-color: black;";
	
	}

