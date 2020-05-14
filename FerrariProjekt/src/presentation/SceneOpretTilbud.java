package presentation;

import FFL.CreditRator;
import FFL.Rating;
import create.CreateButton;
import create.CreateLabel;
import create.CreateTextField;
import entities.Car;
import entities.Employee;
import entities.Kunde;
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
import logic.DBFacade;

public class SceneOpretTilbud {
	Stage stage;
	CreateLabel cl = new CreateLabel();
	CreateButton cb = new CreateButton();
	CreateTextField ctf = new CreateTextField();
	Kunde kunde;
	Car bil;
	Employee bruger;
	TextField tfTelefonnummer, tfCPRNummer, tfCreditrating, tfFornavn, 
	tfEfternavn, tfEmail, tfCity, tfPostnummer;
	
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
		
		HBox hbTelefon = new HBox();
		Label lbTelefonnnummer = cl.lb("Telefonnummer:", textsize);
		tfTelefonnummer = ctf.tf("");
		Button btnTelefonnummer = new Button("Søg");
		hbTelefon.setAlignment(Pos.CENTER);
		hbTelefon.getChildren().addAll(lbTelefonnnummer, tfTelefonnummer, btnTelefonnummer);
		
		HBox hbCPRNummer = new HBox();
		Label lbCPRNummer = cl.lb("CPR Nummer:", textsize);
		tfCPRNummer = ctf.tf("");
		Button btnCPRNummer = new Button("Søg");
		hbCPRNummer.setAlignment(Pos.CENTER);
		hbCPRNummer.getChildren().addAll(lbCPRNummer, tfCPRNummer, btnCPRNummer);
		
		HBox hbCreditRating = new HBox();
		Label lbCreditRating = cl.lb("Kundens Kreditvurdering:", textsize);
		tfCreditrating = ctf.tf("");
		hbCreditRating.setAlignment(Pos.CENTER);
		hbCreditRating.getChildren().addAll(lbCreditRating, tfCreditrating);
		
		HBox hbFornavn = new HBox();
		Label lbFornavn = cl.lb("Fornavn(e)", textsize);
		tfFornavn = ctf.tf("");
		hbFornavn.setAlignment(Pos.CENTER);
		hbFornavn.getChildren().addAll(lbFornavn, tfFornavn);
		
		HBox hbEfternavn = new HBox();
		Label lbEfternavn = cl.lb("Efternavn", textsize);
		tfEfternavn = ctf.tf("");
		hbEfternavn.setAlignment(Pos.CENTER);
		hbEfternavn.getChildren().addAll(lbEfternavn, tfEfternavn);
		
		HBox hbEmail = new HBox();
		Label lbEmail = cl.lb("Email:", textsize);
		tfEmail = ctf.tf("");
		hbEmail.setAlignment(Pos.CENTER);
		hbEmail.getChildren().addAll(lbEmail, tfEmail);
		
		HBox hbCity = new HBox();
		Label lbCity = cl.lb("By:", textsize);
		tfCity = ctf.tf("");
		hbCity.setAlignment(Pos.CENTER);
		hbCity.getChildren().addAll(lbCity, tfCity);
		
		HBox hbPostnummer = new HBox();
		Label lbPostnummer = cl.lb("Postnummer:", textsize);
		tfPostnummer = ctf.tf("");
		hbPostnummer.setAlignment(Pos.CENTER);
		hbPostnummer.getChildren().addAll(lbPostnummer, tfPostnummer);
		
		HBox hbBil = new HBox();
		Label lbBil = cl.lb("Bil:", textsize);
		TextField tfBil = ctf.tf("");
		hbBil.setAlignment(Pos.CENTER);
		hbBil.getChildren().addAll(lbBil, tfBil);
		
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
		btnTelefonnummer.setOnAction(e -> {
			FindCustomer(tfTelefonnummer.getText());
		});
		btnCPRNummer.setOnAction(e -> {
			getCreditRating(tfCPRNummer.getText());
		});

		// Tilføj tl VBox
		vBoxCenter.getChildren().addAll(lbTitel,hbTelefon,hbCPRNummer,hbCreditRating,hbFornavn,hbEfternavn,
				hbEmail,hbCity,hbPostnummer,hbBil,hbUdbetaling,hbLoebetid,btnTilbage);

		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		stage.setScene(scene);

	}
	private void FindCustomer(String cprNummer) {
		DBFacade kl = new DBFacade();
		kunde = kl.findKunde(cprNummer);
		tfCPRNummer.setText(kunde.getCprnummer());
		tfFornavn.setText(kunde.getTelefonnummer());
		tfEfternavn.setText(kunde.getEfternavn());
		tfCity.setText(kunde.getCity());
		tfPostnummer.setText(kunde.getPostnummer());
		tfEmail.setText(kunde.getEmail());
		getCreditRating(cprNummer);
	}
	private void getCreditRating (String cprNummer) {
		CreditRator cr = CreditRator.i();
		Rating creditRate = cr.rate(cprNummer);
		
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
			break;
		}
		
		

		
	}
	
	
}
