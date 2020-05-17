package presentation;

import FFL.CreditRator;
import FFL.Rating;
import create.CreateButton;
import create.CreateLabel;
import create.CreateTextField;
import entities.Car;
import entities.Customer;
import entities.Employee;
import entities.Offer;
import exception.CustomException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.APIController;
import logic.CustomerController;

public class SceneCreateOffer {
	Stage stage;
	CreateLabel cl = new CreateLabel();
	CreateButton cb = new CreateButton();
	CreateTextField ctf = new CreateTextField();
	Offer offer = new Offer();
	TextField tfPhoneNumber, tfCprNumber, tfCreditrating, tfFirstName, 
	tfLastName, tfEMail, tfCity, tfZipCode, tfAddress;
	Label customerError;
	
	public void init(Stage stage) {
		this.stage = stage;
		double knapWidth = stage.getWidth()/4;
		double knapHeight = stage.getHeight()/20;
		double textsize = stage.getHeight()/40;

		// Opsætning
		Insets insets = new Insets(5, 5, 5, 5);
		BorderPane root = new BorderPane();
		root.setPadding(insets);
		VBox vBoxCenter = new VBox(20);
		root.setCenter(vBoxCenter);
		vBoxCenter.setAlignment(Pos.TOP_CENTER);

		// Lav knapper og labels
		Label lbTitel = cl.lb("Opret Tilbud", 50);
		lbTitel.setPrefHeight(stage.getHeight()/20);
		Button btnTilbage = cb.btn("Tilbage", knapWidth, knapHeight);
	
		HBox hbCPRNummer = new HBox();
		Label lbCPRNummer = cl.lb("CPR Nummer:", textsize);
		tfCprNumber = ctf.tf("");
		Button btnFindCustomer = new Button("Søg");
		hbCPRNummer.setAlignment(Pos.CENTER);
		hbCPRNummer.getChildren().addAll(lbCPRNummer, tfCprNumber, btnFindCustomer);
	
		customerError = new Label();
		customerError.setAlignment(Pos.CENTER);
		
		HBox hbCreditRating = new HBox();
		Label lbCreditRating = cl.lb("Kundens Kreditvurdering:", textsize);
		tfCreditrating = ctf.tf("");
		hbCreditRating.setAlignment(Pos.CENTER);
		hbCreditRating.getChildren().addAll(lbCreditRating, tfCreditrating);
		
		HBox hbTelefon = new HBox();
		Label lbTelefonnnummer = cl.lb("Telefonnummer:", textsize);
		tfPhoneNumber = ctf.tf("");
		hbTelefon.setAlignment(Pos.CENTER);
		hbTelefon.getChildren().addAll(lbTelefonnnummer, tfPhoneNumber);
		
		HBox hbFornavn = new HBox();
		Label lbFornavn = cl.lb("Fornavn(e)", textsize);
		tfFirstName = ctf.tf("");
		hbFornavn.setAlignment(Pos.CENTER);
		hbFornavn.getChildren().addAll(lbFornavn, tfFirstName);
		
		HBox hbEfternavn = new HBox();
		Label lbEfternavn = cl.lb("Efternavn", textsize);
		tfLastName = ctf.tf("");
		hbEfternavn.setAlignment(Pos.CENTER);
		hbEfternavn.getChildren().addAll(lbEfternavn, tfLastName);
		
		HBox hbEmail = new HBox();
		Label lbEmail = cl.lb("Email:", textsize);
		tfEMail = ctf.tf("");
		hbEmail.setAlignment(Pos.CENTER);
		hbEmail.getChildren().addAll(lbEmail, tfEMail);
		
		HBox hbCity = new HBox();
		Label lbCity = cl.lb("By:", textsize);
		tfCity = ctf.tf("");
		hbCity.setAlignment(Pos.CENTER);
		hbCity.getChildren().addAll(lbCity, tfCity);
		
		HBox hbPostnummer = new HBox();
		Label lbPostnummer = cl.lb("Postnummer:", textsize);
		tfZipCode = ctf.tf("");
		hbPostnummer.setAlignment(Pos.CENTER);
		hbPostnummer.getChildren().addAll(lbPostnummer, tfZipCode);
		
		HBox hbAddress = new HBox();
		Label lbAddress = cl.lb("Adresse:", textsize);
		tfAddress = ctf.tf("");
		hbAddress.setAlignment(Pos.CENTER);
		hbAddress.getChildren().addAll(lbAddress, tfAddress);
		
		HBox hbBil = new HBox();
		Label lbBil = cl.lb("Bil:", textsize);
		TextField tfBil = ctf.tf("");
		Button btnChooseCar = cb.btn("Vælg bil", knapWidth, knapHeight);
		btnChooseCar.setOnAction(e -> {
			StageChooseCar stCC = new StageChooseCar();
			stCC.init(new Stage(), stage);
			
		});
		hbBil.setAlignment(Pos.CENTER);
		hbBil.getChildren().addAll(lbBil, tfBil, btnChooseCar);
		
		HBox hbUdbetaling = new HBox();
		Label lbUdbetaling = cl.lb("Udbetaling:", textsize);
		TextField tfUdbetaling = ctf.tf("");
		hbUdbetaling.setAlignment(Pos.CENTER);
		hbUdbetaling.getChildren().addAll(lbUdbetaling, tfUdbetaling);
		
		HBox hbLoebetid = new HBox();
		Label lbLoebetid = cl.lb("Løbetid:", textsize);
		TextField tfLoebetid = ctf.tf("");
		hbLoebetid.setAlignment(Pos.CENTER);
		hbLoebetid.getChildren().addAll(lbLoebetid, tfLoebetid);
		
		// Knap funktioner
		btnTilbage.setOnAction(e -> {
			SceneHovedmenu scHM = new SceneHovedmenu();
			scHM.init(stage);
		});
		btnFindCustomer.setOnAction(e -> {
			if(tfCprNumber.getLength()!=10) {
				customerError.setText("Ugyldigt CPR-Nummer");
				clearInfo();
			} else {
			findCustomer(tfCprNumber.getText());
			}
		});

		// Tilføj tl VBox
		vBoxCenter.getChildren().addAll(lbTitel,hbCPRNummer,customerError,hbCreditRating,hbTelefon,hbFornavn,hbEfternavn,
				hbEmail,hbCity,hbPostnummer,hbAddress,hbBil,hbUdbetaling,hbLoebetid,btnTilbage);

		//TESTING PURPOSES
		tfCprNumber.setText("0123456789");
		
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
			offer.getOfferCustomer().setCreditRating(findRating(cprNumber));
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
	
	private Rating findRating (String cprNumber) {
		APIController ac = new APIController();
		Rating creditRate = ac.findRating(cprNumber);
		switch(creditRate) {
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
		return creditRate;	
	}
}
