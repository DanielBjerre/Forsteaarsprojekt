package presentation;

import FFL.Rating;
import create.Constants;
import create.CreateButton;
import create.CreateLabel;
import create.CreateTextField;
import entities.Customer;
import entities.Offer;
import entities.Term;
import exception.CustomException;
import exception.IncorrectInputException;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.APIController;
import logic.ActiveEmployee;
import logic.CalculateLoan;
import logic.CustomerController;
import logic.OfferLogic;
import styles.JavaFXStyles;

public class SceneCreateOffer {
	Stage stage;
	CreateLabel cl = new CreateLabel();
	CreateButton cb = new CreateButton();
	CreateTextField ctf = new CreateTextField();
	Offer offer = new Offer();
	TextField tfPhoneNumber, tfCprNumber, tfCreditrating, tfFirstName, tfLastName, tfEMail, tfCity, tfZipCode,
			tfAddress, tfDownpayment, tfNumOfTerms;
	Label customerError;
	VBox vBoxLeft, vBoxRight;
	HBox hBoxCenter;
	Insets insets = new Insets(5, 15, 5, 15);
	Insets insets2 = new Insets(5, 5, 5, 5);
	Button btnFindCustomer, btnChooseCar, btnCalculate, btnCreateOffer;
	APIController ac = new APIController();
	TableView<Term> tvTerm;
	
	public void init(Stage stage) {
		offer.setOfferEmployee(ActiveEmployee.getInstance().getEmployee());
		double spacing = 10;
		this.stage = stage;
		double textsize = Constants.textSize;
		double width = 0.15;
		// Get daily rate when opening page
		ac.findDailyRate(offer);

		// Setup
		BorderPane root = new BorderPane();
		root.setPadding(insets);
		root.setStyle(JavaFXStyles.backgroundStyle1);	
		vBoxLeft = new VBox(7);
		vBoxLeft.setPrefWidth(stage.getWidth()/2);
		vBoxLeft.setStyle(JavaFXStyles.backgroundStyle2);
		vBoxLeft.setPadding(insets);
		vBoxLeft.setMaxHeight(stage.getHeight()/2);
		vBoxLeft.setAlignment(Pos.CENTER);

		vBoxRight = new VBox(20);
		vBoxRight.setAlignment(Pos.CENTER);
		vBoxRight.setPrefWidth(stage.getWidth()/2);	
		vBoxRight.setStyle(JavaFXStyles.backgroundStyle2);
		vBoxRight.setPadding(insets);
		hBoxCenter = new HBox(20);
		hBoxCenter.setAlignment(Pos.CENTER);
		hBoxCenter.getChildren().addAll(vBoxLeft, vBoxRight);

		root.setCenter(hBoxCenter);
		
		// STAGE TITLE
		Label lbTitle = cl.lb("Opret Tilbud", 50);
		lbTitle.setPrefHeight(stage.getHeight() / 20);
		vBoxLeft.getChildren().add(lbTitle);

		// CPR NUMBER
		tfCprNumber = ctf.tf();
		tfCprNumber.setMinWidth(stage.getWidth()*0.06);
		Label lbCprNumber = cl.lb("CPR Nummer:", textsize);
		lbCprNumber.setMinWidth(stage.getWidth()*0.1);
		btnFindCustomer = cb.btn("Søg", 1, 1);
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
		customerError = cl.lb();
		customerError.setAlignment(Pos.CENTER);
		vBoxLeft.getChildren().add(customerError);

		// CREDITRATING
		Label lbCreditRating = cl.lb("Kundens Kreditvurdering:", textsize);
		lbCreditRating.setMinWidth(stage.getWidth()*width);
		tfCreditrating = ctf.tf();
		tfCreditrating.setMinWidth(stage.getWidth()*width);
		makeHbox(lbCreditRating, tfCreditrating);

		// PHONENUMBER
		Label lbPhoneNumber = cl.lb("Telefonnummer:", textsize);
		lbPhoneNumber.setMinWidth(stage.getWidth()*width);
		tfPhoneNumber = ctf.tf();
		tfPhoneNumber.setMinWidth(stage.getWidth()*width);
		makeHbox(lbPhoneNumber, tfPhoneNumber);

		// FIRSTNAME
		Label lbFirstName = cl.lb("Fornavn(e)", textsize);
		lbFirstName.setMinWidth(stage.getWidth()*width);
		tfFirstName = ctf.tf();
		tfFirstName.setMinWidth(stage.getWidth()*width);
		makeHbox(lbFirstName, tfFirstName);

		// LASTNAME
		Label lbLastName = cl.lb("Efternavn", textsize);
		lbLastName.setMinWidth(stage.getWidth()*width);
		tfLastName = ctf.tf();
		tfLastName.setMinWidth(stage.getWidth()*width);
		makeHbox(lbLastName, tfLastName);

		// EMAIL
		Label lbEMail = cl.lb("eMail:", textsize);
		lbEMail.setMinWidth(stage.getWidth()*width);
		tfEMail = ctf.tf();
		tfEMail.setMinWidth(stage.getWidth()*width);
		makeHbox(lbEMail, tfEMail);

		// CITY
		Label lbCity = cl.lb("By:", textsize);
		lbCity.setMinWidth(stage.getWidth()*width);
		tfCity = ctf.tf();
		tfCity.setMinWidth(stage.getWidth()*width);
		makeHbox(lbCity, tfCity);

		// ZIPCODE
		Label lbZipCode = cl.lb("Postnummer:", textsize);
		lbZipCode.setMinWidth(stage.getWidth()*width);
		tfZipCode = ctf.tf();
		tfZipCode.setMinWidth(stage.getWidth()*width);
		makeHbox(lbZipCode, tfZipCode);

		// ADDRESS
		Label lbAddress = cl.lb("Adresse:", textsize);
		lbAddress.setMinWidth(stage.getWidth()*width);
		tfAddress = ctf.tf();
		tfAddress.setMinWidth(stage.getWidth()*width);
		makeHbox(lbAddress, tfAddress);

		// CAR
		Label lbCar = cl.lb("Bil:", textsize);
		lbCar.setMinWidth(stage.getWidth()*width);
		TextField tfCarModel = ctf.tf();
		tfCarModel.setMinWidth(stage.getWidth()*width);
		tfCarModel.setPromptText("Bilmodel");
		TextField tfCarPrice = ctf.tf();
		tfCarPrice.setMinWidth(stage.getWidth()*width);
		tfCarPrice.setPromptText("Bilens pris");
		tfCarModel.setEditable(false);
		tfCarPrice.setEditable(false);
		btnChooseCar = cb.btn("Vælg bil", 4, 1);

		tfCarPrice.textProperty().addListener(c-> {
			btnCalculate.setDisable(false);
		});

		btnChooseCar.setOnAction(e -> {
			StageChooseCar stCC = new StageChooseCar();
			stCC.init(new Stage(), stage, offer, tfCarModel, tfCarPrice);

		});
		vBoxLeft.getChildren().add(btnChooseCar);
		makeHbox(tfCarModel, tfCarPrice);

		// DOWNPAYMENT
		Label lbDownpayment = cl.lb("Udbetaling:", textsize);
		lbDownpayment.setMinWidth(stage.getWidth()*width);
		tfDownpayment = ctf.tf();
		tfDownpayment.setMinWidth(stage.getWidth()*width);
		makeHbox(lbDownpayment, tfDownpayment);

		// NUMBER OF TERMS
		Label lbNumOfTerms = cl.lb("Løbetid:", textsize);
		lbNumOfTerms.setMinWidth(stage.getWidth()*width);
		tfNumOfTerms = ctf.tf();
		tfNumOfTerms.setMinWidth(stage.getWidth()*width);
		makeHbox(lbNumOfTerms, tfNumOfTerms);

		// ERROR LABEL
		Label errorMessage = cl.lb();
		errorMessage.setAlignment(Pos.CENTER);
		vBoxLeft.getChildren().add(errorMessage);
		
		// BUTTONS
		btnCalculate = cb.btn("Udregn", 5,1);
		Button btnBack = cb.btn("Tilbage", 5, 1);

		btnBack.setOnAction(e -> {
			SceneMainMenu scHM = new SceneMainMenu();
			scHM.init(stage);
		});
		vBoxLeft.getChildren().addAll(btnCalculate, btnBack);

		// TESTING PURPOSES
		tfCprNumber.setText("0123456789");
		tfDownpayment.setText("100000");
		tfNumOfTerms.setText("24");

		// RIGHT SIDE
		HBox hBoxTitle = new HBox(10);
		hBoxTitle.setAlignment(Pos.TOP_CENTER);
		Label lbTitleRight = cl.lb("Lånetilbud:");
		Label lbTitleValue = cl.lb();
		hBoxTitle.getChildren().addAll(lbTitleRight,lbTitleValue);
		vBoxRight.getChildren().add(hBoxTitle);
		
		Label lbCustomer = cl.lb("Kunde");
		vBoxRight.getChildren().add(lbCustomer);		
		HBox hBoxCustomer = new HBox();
		
		VBox vBoxCustomer1 = new VBox(spacing);
		vBoxCustomer1.setPrefWidth(stage.getWidth()*0.10);
		Label lbCustomerName = cl.lb("Navn:", Constants.textSize*0.75);
		Label lbCustomerPhone = cl.lb("Telefonnummer:", Constants.textSize*0.75);
		Label lbCustomerAddress = cl.lb("Addresse:", Constants.textSize*0.75);
		vBoxCustomer1.getChildren().addAll(lbCustomerName,lbCustomerPhone, lbCustomerAddress);
		
		VBox vBoxCustomer2 = new VBox(spacing);
		vBoxCustomer2.setPrefWidth(stage.getWidth()*0.2);
		Label lbCustomerNameValue = cl.lb("", Constants.textSize*0.75);
		Label lbCustomerPhoneValue = cl.lb("", Constants.textSize*0.75);
		Label lbCustomerAddressValue = cl.lb("", Constants.textSize*0.75);
		vBoxCustomer2.getChildren().addAll(lbCustomerNameValue, lbCustomerPhoneValue, lbCustomerAddressValue);
		
		VBox vBoxCustomer3 = new VBox(spacing);
		vBoxCustomer3.setPrefWidth(stage.getWidth()*0.1);
		Label lbCustomerCprNumber = cl.lb("CPR-Nummer:", Constants.textSize*0.75);
		Label lbCustomerEMail = cl.lb("eMail:", Constants.textSize*0.75);
		vBoxCustomer3.getChildren().addAll(lbCustomerCprNumber, lbCustomerEMail);
		
		VBox vBoxCustomer4 = new VBox(spacing);
		vBoxCustomer4.setPrefWidth(stage.getWidth()*0.15);
		Label lbCustomerCprNumberValue = cl.lb("", Constants.textSize*0.75);
		Label lbCustomerEMailValue = cl.lb("", Constants.textSize*0.75);
		vBoxCustomer4.getChildren().addAll(lbCustomerCprNumberValue, lbCustomerEMailValue);
		
		hBoxCustomer.getChildren().addAll(vBoxCustomer1,vBoxCustomer2,vBoxCustomer3,vBoxCustomer4);
		hBoxCustomer.setStyle(JavaFXStyles.HBoxStyle);
		hBoxCustomer.setPadding(insets2);
		vBoxRight.getChildren().add(hBoxCustomer);
				
		Label lbCarRight = cl.lb("Bil");
		vBoxRight.getChildren().add(lbCarRight);
		
		HBox hBoxCar = new HBox(spacing);
		VBox vBoxCar1 = new VBox();
		vBoxCar1.setPrefWidth(stage.getWidth()*0.1);
		Label lbCarModel = cl.lb("Model:", Constants.textSize*0.75);
		Label lbCarMileage = cl.lb("Kilometer:", Constants.textSize*0.75);
		vBoxCar1.getChildren().addAll(lbCarModel, lbCarMileage);
		
		VBox vBoxCar2 = new VBox(spacing); 
		vBoxCar2.setPrefWidth(stage.getWidth()*0.2);
		Label lbCarModelValue = cl.lb("", Constants.textSize*0.75);
		Label lbCarMileageValue = cl.lb("", Constants.textSize*0.75);
		vBoxCar2.getChildren().addAll(lbCarModelValue, lbCarMileageValue);
		
		VBox vBoxCar3 = new VBox(spacing);
		vBoxCar3.setPrefWidth(stage.getWidth()*0.1);
		Label lbCarModelYear = cl.lb("Årgang:", Constants.textSize*0.75);
		Label lbCarSerialNumber = cl.lb("Serienummer:", Constants.textSize*0.75);
		vBoxCar3.getChildren().addAll(lbCarModelYear, lbCarSerialNumber);
		
		VBox vBoxCar4 = new VBox(spacing);
		vBoxCar4.setPrefWidth(stage.getWidth()*0.15);	
		Label lbCarModelYearValue = cl.lb("", Constants.textSize*0.75);
		Label lbCarSerialNumberValue = cl.lb("", Constants.textSize*0.75);
		vBoxCar4.getChildren().addAll(lbCarModelYearValue, lbCarSerialNumberValue);
		
		hBoxCar.getChildren().addAll(vBoxCar1,vBoxCar2,vBoxCar3,vBoxCar4);
		hBoxCar.setStyle(JavaFXStyles.HBoxStyle);
		hBoxCar.setPadding(insets2);
		vBoxRight.getChildren().add(hBoxCar);
		
		HBox hBoxEmployee = new HBox(30);
		Label lbEmployeeName = cl.lb("Sælger:", Constants.textSize*0.75);
		Label lbEmployeeNameValue = cl.lb("", Constants.textSize*0.75);
		hBoxEmployee.getChildren().addAll(lbEmployeeName,lbEmployeeNameValue);
		hBoxEmployee.setPadding(insets2);
		vBoxRight.getChildren().add(hBoxEmployee);
		
		HBox hBoxInfo = new HBox();
		VBox vBoxInfo1 = new VBox(spacing);
		vBoxInfo1.setPrefWidth(stage.getWidth()*0.1);
		Label lbPrice = cl.lb("Pris:", Constants.textSize*0.75);
		Label lbDownPayment = cl.lb("Udbetaling:", Constants.textSize*0.75);
		Label lbLoanValue = cl.lb("Lånebeløb:", Constants.textSize*0.75);
		vBoxInfo1.getChildren().addAll(lbPrice, lbDownPayment, lbLoanValue);
		
		VBox vBoxInfo2 = new VBox(spacing);
		vBoxInfo2.setPrefWidth(stage.getWidth()*0.2);
		Label lbPriceValue = cl.lb("", Constants.textSize*0.75);
		Label lbDownPaymentValue = cl.lb("", Constants.textSize*0.75);
		Label lbLoanValueValue = cl.lb("", Constants.textSize*0.75);
		vBoxInfo2.getChildren().addAll(lbPriceValue, lbDownPaymentValue, lbLoanValueValue);
		
		VBox vBoxInfo3 = new VBox(spacing);
		vBoxInfo3.setPrefWidth(stage.getWidth()*0.1);
		Label lbRate = cl.lb("Rente:", Constants.textSize*0.75);
		Label lbNumOfTermsRight = cl.lb("Løbetid:", Constants.textSize*0.75);
		vBoxInfo3.getChildren().addAll(lbRate, lbNumOfTermsRight);
		
		VBox vBoxInfo4 = new VBox(spacing);
		vBoxInfo4.setPrefWidth(stage.getWidth()*0.15);
		Label lbRateValue = cl.lb("", Constants.textSize*0.75);
		Label lbNumOfTermsValue = cl.lb("", Constants.textSize*0.75);
		vBoxInfo4.getChildren().addAll(lbRateValue, lbNumOfTermsValue);
		hBoxInfo.getChildren().addAll(vBoxInfo1,vBoxInfo2,vBoxInfo3,vBoxInfo4);
		hBoxInfo.setStyle(JavaFXStyles.HBoxStyle);
		hBoxInfo.setPadding(insets2);
		vBoxRight.getChildren().add(hBoxInfo);

		// CREATE TABLEVIEW
		tvTerm = new TableView<Term>();
		TableColumn<Term, String> clmTermNumber = new TableColumn<>("Termin nr:");
		TableColumn<Term, String> clmPreviousBalance = new TableColumn<>("Primo Restgï¿½ld");
		TableColumn<Term, String> clmPayment = new TableColumn<>("Ydelse");
		TableColumn<Term, String> clmInterest = new TableColumn<>("Rente");
		TableColumn<Term, String> clmPrincipal = new TableColumn<>("Afdrag");
		TableColumn<Term, String> clmNewBalance = new TableColumn<>("Ultimo Restgï¿½ld");

		// COLUMN WIDTH & ALIGNMENT
        clmTermNumber.prefWidthProperty().bind(tvTerm.widthProperty().multiply(0.11));
		clmTermNumber.setStyle("-fx-alignment: CENTER");
        clmPreviousBalance.prefWidthProperty().bind(tvTerm.widthProperty().multiply(0.17));
        clmPreviousBalance.setStyle("-fx-alignment: CENTER");
        clmPayment.prefWidthProperty().bind(tvTerm.widthProperty().multiply(0.17));
        clmPayment.setStyle("-fx-alignment: CENTER");
        clmInterest.prefWidthProperty().bind(tvTerm.widthProperty().multiply(0.17));
        clmInterest.setStyle("-fx-alignment: CENTER");
        clmPrincipal.prefWidthProperty().bind(tvTerm.widthProperty().multiply(0.17));
        clmPrincipal.setStyle("-fx-alignment: CENTER");
        clmNewBalance.prefWidthProperty().bind(tvTerm.widthProperty().multiply(0.18));
        clmNewBalance.setStyle("-fx-alignment: CENTER");
		
		// ADD COLUMNS TO TABLEVIEW
		tvTerm.getColumns().addAll(clmTermNumber, clmPreviousBalance, clmPayment, clmInterest, clmPrincipal,
				clmNewBalance);

		// ADD VALUES TO COLUMNS
		clmTermNumber.setCellValueFactory(new PropertyValueFactory<Term, String>("termNumber"));
		clmPreviousBalance.setCellValueFactory(cellData -> {
			return new ReadOnlyStringWrapper(String.format("%.2f", Double.parseDouble(cellData.getValue().getPreviousBalance()))+"kr");
		});
		clmPayment.setCellValueFactory(cellData -> {
			return new ReadOnlyStringWrapper(String.format("%.2f", Double.parseDouble(cellData.getValue().getPayment()))+"kr");
		});
		clmInterest.setCellValueFactory(cellData -> {
			return new ReadOnlyStringWrapper(String.format("%.2f", Double.parseDouble(cellData.getValue().getInterest()))+"kr");
		});
		clmPrincipal.setCellValueFactory(cellData -> {
			return new ReadOnlyStringWrapper(String.format("%.2f", Double.parseDouble(cellData.getValue().getPrincipal()))+"kr");
		});
		clmNewBalance.setCellValueFactory(cellData -> {
			return new ReadOnlyStringWrapper(String.format("%.2f", Double.parseDouble(cellData.getValue().getNewBalance()))+"kr");
		});
		// BUTTONS BELOW TABLEVIEW
		btnCreateOffer = cb.btn("Opret Tilbud" , 5, 1);
		btnCreateOffer.setOnAction(e -> {
			new OfferLogic().offerCreate(offer);
			StagePopUp stPP = new StagePopUp();
			stPP.init(new Stage(), "Tilbud oprettet", stage);
		});
		vBoxRight.getChildren().addAll(tvTerm, btnCreateOffer);
		tfKundeIsNotHandel(false);
		btnReset();
		
		btnCalculate.setOnAction(e -> {
			errorMessage.setText("");
			double terms = Double.parseDouble(tfNumOfTerms.getText());
			double price = Double.parseDouble(tfCarPrice.getText());
			double downpayment = Double.parseDouble(tfDownpayment.getText());
			try {
			if (price < downpayment) {
				throw new IncorrectInputException("Udbetaling kan ikke være højere end pris");
			}
			if(terms > 84) {
				throw new IncorrectInputException("Løbetid kan ikke være højere end 84");
			}
			if (!offer.getOfferCustomer().isExists()) {
				fillCustomer();
				new CustomerController().createCustomer(offer);
			}
			offer.setNumOfTerms(Integer.parseInt(tfNumOfTerms.getText()));
			offer.setDownPayment(tfDownpayment.getText());
			Double loanValue = offer.getOfferCar().getPriceDouble() - offer.getDownPaymentDouble();
			offer.setLoanValue(loanValue.toString());
			new CalculateLoan(offer);
			populateTableView();
			btnCreateOffer.setDisable(false);
			lbTitleValue.setText(offer.getOfferID());
			lbCustomerNameValue.setText(offer.getOfferCustomer().getFirstName()+" "+offer.getOfferCustomer().getLastName());
			lbCustomerPhoneValue.setText(offer.getOfferCustomer().getPhoneNumber());
			lbCustomerCprNumberValue.setText(offer.getOfferCustomer().getCprNumber());
			lbCustomerEMailValue.setText(offer.getOfferCustomer().geteMail());
			lbCustomerAddressValue.setText(offer.getOfferCustomer().getAddress()+" "+offer.getOfferCustomer().getZipCode()+" "+offer.getOfferCustomer().getCity());
			
			lbCarModelValue.setText(offer.getOfferCar().getModel());
			lbCarMileageValue.setText(offer.getOfferCar().getMileage());
			lbCarModelYearValue.setText(offer.getOfferCar().getModelYear());
			lbCarSerialNumberValue.setText(offer.getOfferCar().getSerialNumber());
			
			lbEmployeeNameValue.setText(offer.getOfferEmployee().getFirstName()+ " " + offer.getOfferEmployee().getLastName());
			
			lbPriceValue.setText(offer.getOfferCar().getPrice());
			lbDownPaymentValue.setText(offer.getDownPayment());
			lbLoanValueValue.setText(offer.getLoanValue());
			lbRateValue.setText(String.format("%.2f",Double.parseDouble(offer.getRate()))+" %");
			lbNumOfTermsValue.setText(Integer.toString(offer.getNumOfTerms())+" måneder");
			} catch (IncorrectInputException e2) {
				errorMessage.setText(e2.getMessage());
			}
		});

		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		stage.setScene(scene);

	}
	// Help methods

	/**
	 * Searches for customer in Database If customer exists, fills information in
	 * textboxes and sets the customer entity on the offer If customer doesn't
	 * exist, creates an empty customer entity and sets the empty customer entity on
	 * the offer Finally, puts the cprNumber through the API and gets the
	 * creditrating
	 * 
	 * @param cprNumber
	 */
	private void findCustomer(String cprNumber) {
		customerError.setText("");
		try {
			CustomerController kl = new CustomerController();
			offer.setOfferCustomer(kl.findCustomer(cprNumber));
			offer.getOfferCustomer().setExists(true);
			tfPhoneNumber.setText(offer.getOfferCustomer().getPhoneNumber());
			tfFirstName.setText(offer.getOfferCustomer().getFirstName());
			tfLastName.setText(offer.getOfferCustomer().getLastName());
			tfCity.setText(offer.getOfferCustomer().getCity());
			tfZipCode.setText(offer.getOfferCustomer().getZipCode());
			tfEMail.setText(offer.getOfferCustomer().geteMail());
			tfAddress.setText(offer.getOfferCustomer().getAddress());
		} catch (CustomException e) {
			offer.setOfferCustomer(new Customer());
			offer.getOfferCustomer().setExists(false);
			if(!offer.getOfferCustomer().isExists())
			{
				tfKundeIsNotHandel(true);
			}
			customerError.setText(e.getMessage());
			clearInfo();
		} finally {
			if(offer.getOfferCustomer().isBadStanding())
			{
				customerError.setText("Vi kan ikke oprette et lÃ¥n til denne kunde (bad standing)");
			} else {
				ac.findRating(cprNumber, this::fillRating);
			}
		}

	}

	/**
	 * Method that clears customer information textfields
	 */
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

	/**
	 * Method that sets the creditrating in the textfield
	 * 
	 * @param creditRate
	 */
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
				btnChooseCar.setDisable(false);
			}
		});
	}

	/**
	 * Help method to create hboxes
	 * 
	 * @param nodes
	 * @return HBox with all the nodes added in order
	 */
	private HBox makeHbox(Node... nodes) {
		HBox tempHBox = new HBox(20);
		for (Node node : nodes) {
			tempHBox.getChildren().add(node);
		}
		tempHBox.setAlignment(Pos.CENTER);
		vBoxLeft.getChildren().add(tempHBox);
		return tempHBox;
	}

	private void populateTableView() {
		ObservableList<Term> olTerm = FXCollections.observableList(offer.getPeriods());
		tvTerm.setItems(olTerm);
	}

	/**
	 * Fills the empty customer entity with the information from the textfields
	 */
	private void fillCustomer() {
		offer.getOfferCustomer().setCprNumber(tfCprNumber.getText());
		offer.getOfferCustomer().setPhoneNumber(tfPhoneNumber.getText());
		offer.getOfferCustomer().setFirstName(tfFirstName.getText());
		offer.getOfferCustomer().setLastName(tfLastName.getText());
		offer.getOfferCustomer().seteMail(tfEMail.getText());
		offer.getOfferCustomer().setAddress(tfAddress.getText());
		offer.getOfferCustomer().setZipCode(tfZipCode.getText());
		offer.getOfferCustomer().setCity(tfCity.getText());
	}

	private void tfKundeIsNotHandel(boolean state)
	{	
		tfPhoneNumber.setEditable(state);
		tfFirstName.setEditable(state);
		tfLastName.setEditable(state);
		tfEMail.setEditable(state);
		tfCity.setEditable(state);
		tfZipCode.setEditable(state);
		tfCreditrating.setEditable(state);
	}

	private void btnReset()
	{
		btnCalculate.setDisable(true);
		btnChooseCar.setDisable(true);
		btnCreateOffer.setDisable(true);
	}
}
