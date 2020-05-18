package presentation;

import FFL.Rating;
import create.CreateButton;
import create.CreateLabel;
import create.CreateTextField;
import entities.Car;
import entities.Customer;
import entities.Employee;
import entities.Offer;
import exception.CustomException;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.APIController;
import logic.CalculateLoan;
import logic.CustomerController;

public class SceneCreateOffer {
	Stage stage;
	CreateLabel cl = new CreateLabel();
	CreateButton cb = new CreateButton();
	CreateTextField ctf = new CreateTextField();
	Offer offer = new Offer();
	TextField tfPhoneNumber, tfCprNumber, tfCreditrating, tfFirstName, tfLastName, tfEMail, tfCity, tfZipCode,
			tfAddress, tfDownpayment, tfNumOfTerms;
	Label customerError;
	VBox vBoxCenter;
	Insets insets = new Insets(15, 15, 15, 15);
	Button btnFindCustomer;
	APIController ac = new APIController();

	public void init(Stage stage) {
		this.stage = stage;
		double knapWidth = stage.getWidth() / 4;
		double knapHeight = stage.getHeight() / 20;
		double textsize = stage.getHeight() / 40;
		
		// Get daily rate when opening page
		ac.findDailyRate(offer);

		// Setup
		BorderPane root = new BorderPane();
		root.setPadding(insets);
		vBoxCenter = new VBox(20);
		root.setCenter(vBoxCenter);
		vBoxCenter.setAlignment(Pos.TOP_CENTER);

		// STAGE TITLE
		Label lbTitle = cl.lb("Opret Tilbud", 50);
		lbTitle.setPrefHeight(stage.getHeight() / 20);
		vBoxCenter.getChildren().add(lbTitle);

		// CPR NUMBER
		tfCprNumber = ctf.tf("");
		Label lbCprNumber = cl.lb("CPR Nummer:", textsize);
		btnFindCustomer = new Button("Søg");
		btnFindCustomer.setOnAction(e -> {
			if (tfCprNumber.getLength() != 10) {
				customerError.setText("Ugyldigt CPR-Nummer");
				clearInfo();
			} else {
				findCustomer(tfCprNumber.getText());
				btnFindCustomer.setDisable(true);
			}
		});
		makeHbox(lbCprNumber, tfCprNumber, btnFindCustomer);

		// ERRORBOX FOR CPRNUMBER
		customerError = new Label();
		customerError.setAlignment(Pos.CENTER);
		vBoxCenter.getChildren().add(customerError);

		// CREDITRATING
		Label lbCreditRating = cl.lb("Kundens Kreditvurdering:", textsize);
		tfCreditrating = ctf.tf("");
		makeHbox(lbCreditRating, tfCreditrating);

		// PHONENUMBER
		Label lbPhoneNumber = cl.lb("Telefonnummer:", textsize);
		tfPhoneNumber = ctf.tf("");
		makeHbox(lbPhoneNumber, tfPhoneNumber);

		// FIRSTNAME
		Label lbFirstName = cl.lb("Fornavn(e)", textsize);
		tfFirstName = ctf.tf("");
		makeHbox(lbFirstName, tfFirstName);

		// LASTNAME
		Label lbLastName = cl.lb("Efternavn", textsize);
		tfLastName = ctf.tf("");
		makeHbox(lbLastName, tfLastName);

		// EMAIL
		Label lbEMail = cl.lb("eMail:", textsize);
		tfEMail = ctf.tf("");
		makeHbox(lbEMail, tfEMail);

		// CITY
		Label lbCity = cl.lb("By:", textsize);
		tfCity = ctf.tf("");
		makeHbox(lbCity, tfCity);

		// ZIPCODE
		Label lbZipCode = cl.lb("Postnummer:", textsize);
		tfZipCode = ctf.tf("");
		makeHbox(lbZipCode, tfZipCode);

		// ADDRESS
		Label lbAddress = cl.lb("Adresse:", textsize);
		tfAddress = ctf.tf("");
		makeHbox(lbAddress, tfAddress);

		// CAR
		Label lbCar = cl.lb("Bil:", textsize);
		TextField tfCarModel = ctf.tf("");
		TextField tfCarPrice = ctf.tf("");
		Button btnChooseCar = cb.btn("Vælg bil", knapWidth, knapHeight);
		btnChooseCar.setOnAction(e -> {
			StageChooseCar stCC = new StageChooseCar();
			stCC.init(new Stage(), stage, offer, tfCarModel, tfCarPrice);

		});
		makeHbox(lbCar, tfCarModel, tfCarPrice, btnChooseCar);

		// DOWNPAYMENT
		Label lbDownpayment = cl.lb("Udbetaling:", textsize);
		tfDownpayment = ctf.tf("");
		makeHbox(lbDownpayment, tfDownpayment);

		// NUMBER OF TERMS
		Label lbNumOfTerms = cl.lb("Løbetid:", textsize);
		tfNumOfTerms = ctf.tf("");
		makeHbox(lbNumOfTerms, tfNumOfTerms);

		// BUTTONS
		Button btnCalculate = cb.btn("Udregn", knapWidth, knapHeight);
		btnCalculate.setOnAction(e -> {
			offer.setNumOfTerms(tfNumOfTerms.getText());
			offer.setDownPayment(tfDownpayment.getText());
			new CalculateLoan(offer);
		});
		Button btnBack = cb.btn("Tilbage", knapWidth, knapHeight);

		btnBack.setOnAction(e -> {
			SceneHovedmenu scHM = new SceneHovedmenu();
			scHM.init(stage);
		});
		vBoxCenter.getChildren().addAll(btnBack, btnCalculate);
		// TESTING PURPOSES
		tfCprNumber.setText("0123456789");
		tfDownpayment.setText("10000");
		tfNumOfTerms.setText("24");

		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		stage.setScene(scene);

	}

	private void findCustomer(String cprNumber) {
		customerError.setText("");
		try {
			CustomerController kl = new CustomerController();
			offer.setOfferCustomer(kl.findCustomer(cprNumber));
			tfPhoneNumber.setText(offer.getOfferCustomer().getPhoneNumber());
			tfFirstName.setText(offer.getOfferCustomer().getFirstName());
			tfLastName.setText(offer.getOfferCustomer().getLastName());
			tfCity.setText(offer.getOfferCustomer().getCity());
			tfZipCode.setText(offer.getOfferCustomer().getZipCode());
			tfEMail.setText(offer.getOfferCustomer().geteMail());
			tfAddress.setText(offer.getOfferCustomer().getAdress());
			System.out.println(offer.getOfferCustomer().isBadStanding());
		} catch (CustomException e) {
			offer.setOfferCustomer(new Customer());
			customerError.setText(e.getMessage());
		} finally {
			ac.findRating(cprNumber, this::fillRating);
		}

	}
	
	private void clearInfo() {
		tfPhoneNumber.setText("");
		tfCreditrating.setText("");
		tfFirstName.setText("");
		tfLastName.setText("");
		tfCity.setText("");
		tfZipCode.setText("");
		tfEMail.setText("");
		tfAddress.setText("");

	}

	private void fillRating(Rating creditRate) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				switch (creditRate) {
				case A:
					tfCreditrating.setText("A");
					break;
				case B:
					tfCreditrating.setText("B");
					break;
				case C:
					tfCreditrating.setText("C");
					break;
				case D:
					tfCreditrating.setText("D");
					customerError.setText("Kunder med kreditvurdering D betjenes ikke");
					break;
				}
				offer.getOfferCustomer().setCreditRating(creditRate);
				btnFindCustomer.setDisable(false);
			}
		});
	}

	private HBox makeHbox(Node... nodes) {
		HBox tempHBox = new HBox();
		for (Node node : nodes) {
			tempHBox.getChildren().add(node);
		}
		tempHBox.setAlignment(Pos.CENTER);
		vBoxCenter.getChildren().add(tempHBox);
		return tempHBox;
	}
}
