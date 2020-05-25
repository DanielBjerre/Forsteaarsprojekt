package presentation;

import java.util.ArrayList;

import create.Constants;
import create.CreateButton;
import create.CreateLabel;
import create.CreateTextField;

import entities.Offer;
import entities.Term;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.ActiveEmployee;
import logic.ListSort;
import logic.OfferLogic;
import logic.PrintCSV;
import styles.JavaFXStyles;

public class SceneLookUpOffer {
	TextField tfSearch;
	Stage stage;
	VBox vBoxLeft, vBoxRight;
	HBox hBoxCenter;
	Insets insets = new Insets(15, 15, 15, 15);
	Insets insets2 = new Insets(5, 5, 5, 5);

	TableView<Term> tvTerm;
	TableView<Offer> tvOffer;
	CreateButton cb = new CreateButton();
	CreateTextField ctf = new CreateTextField();
	OfferLogic offerLogic = new OfferLogic();
	ListSort ls = new ListSort();
	ComboBox<String> cbSearch;
	CreateLabel cl = new CreateLabel();

	public void init(Stage stage) {

		this.stage = stage;

		// Setup
		int spacing = 5;
		BorderPane root = new BorderPane();
		root.setPadding(insets);
		root.setStyle(JavaFXStyles.backgroundStyle1);
		vBoxLeft = new VBox(20);
		vBoxLeft.setPrefWidth(stage.getWidth() / 2);
		vBoxLeft.setStyle(JavaFXStyles.backgroundStyle2);
		vBoxLeft.setPadding(insets);
		vBoxLeft.setMaxHeight(stage.getHeight() / 2);
		vBoxLeft.setAlignment(Pos.CENTER);

		vBoxRight = new VBox(20);
		vBoxRight.setAlignment(Pos.CENTER);
		vBoxRight.setPrefWidth(stage.getWidth() / 2);
		vBoxRight.setStyle(JavaFXStyles.backgroundStyle2);
		vBoxRight.setPadding(insets);
		hBoxCenter = new HBox(20);
		hBoxCenter.setAlignment(Pos.CENTER);
		hBoxCenter.getChildren().addAll(vBoxLeft, vBoxRight);

		root.setCenter(hBoxCenter);

		// LEFT SIDE
		HBox hBoxButtons1 = new HBox();
		Button btnAllOffers = cb.btn("Se alle ordrer", 4, 1);
		btnAllOffers.setOnAction(e -> {
			clear();
			populateTableView(ls.sortOffer(offerLogic.getCompleteList(), "", ""));
		});
		Button btnReadyToProcess = cb.btn("Ordrer godkendt og accepteret", 4, 1);
		btnReadyToProcess.setOnAction(e -> {
			// ORDRER HVOR ALT ER ACCEPTERET
			populateTableView(ls.sortOffer(offerLogic.getCompleteList(), "C-and-M-Accept", ""));
		});
		hBoxButtons1.getChildren().addAll(btnAllOffers, btnReadyToProcess);
		hBoxButtons1.setAlignment(Pos.CENTER);
		hBoxButtons1.setSpacing(30);

		HBox hBoxButtons2 = new HBox();
		Button btnMissingCustomer = cb.btn("Ordrer der mangler kundeaccept", 4, 1);
		btnMissingCustomer.setOnAction(e -> {
			populateTableView(ls.sortOffer(offerLogic.getCompleteList(), "CustomerAccept", ""));
		});
		Button btnMissingManager = cb.btn("Ordrer der mangler godkendelse", 4, 1);
		btnMissingManager.setOnAction(e -> {
			populateTableView(ls.sortOffer(offerLogic.getCompleteList(), "ManagerAccept", ""));
		});
		hBoxButtons2.getChildren().addAll(btnMissingCustomer, btnMissingManager);
		hBoxButtons2.setAlignment(Pos.CENTER);
		hBoxButtons2.setSpacing(30);

		vBoxLeft.getChildren().addAll(hBoxButtons1, hBoxButtons2);

		// SEARCH
		HBox hbSearch = new HBox();
		cbSearch = new ComboBox<>();
		cbSearch.setStyle(JavaFXStyles.ComboBoxStyle1);
		cbSearch.setPrefSize(Constants.stageWidth / 10, Constants.stageHeight / 25);
		cbSearch.getItems().add("Sï¿½lger");
		cbSearch.getItems().add("Kunde");
		cbSearch.getItems().add("Bil");

		tfSearch = ctf.tf();
		tfSearch.setMinWidth(stage.getWidth() * 0.20);
		tfSearch.setPromptText("Vï¿½lg sï¿½gekriterie");

		cbSearch.getSelectionModel().selectedItemProperty().addListener(c -> {
			tfSearch.clear();
			if (cbSearch.getSelectionModel().getSelectedItem() != null) {
				switch (cbSearch.getSelectionModel().getSelectedItem()) {
					case "Sï¿½lger":
						tfSearch.setPromptText("Indtast sï¿½lger ID");
						break;
					case "Kunde":
						tfSearch.setPromptText("Indtast kundens CPR-Nummer");
						break;
					case "Bil":
						tfSearch.setPromptText("Indtast bilens serienummer");
						break;
				}
			} else
				tfSearch.setPromptText("Vï¿½lg sï¿½gekriterie");
		});
		Button btnSearch = cb.btn("Sï¿½g", 2, 1);
		hbSearch.getChildren().addAll(cbSearch, tfSearch, btnSearch);
		hbSearch.setSpacing(20);
		hbSearch.setAlignment(Pos.CENTER);
		vBoxLeft.getChildren().add(hbSearch);

		// TABLEVIEW OF ORDERS
		tvOffer = new TableView<Offer>();
		tvOffer.setMinHeight(stage.getHeight()*0.3);
		tvOffer.setPlaceholder(new Label("Ingen tilbud fundet"));
		TableColumn<Offer, String> clmCustomer = new TableColumn<>("Kunde");
		TableColumn<Offer, String> clmCar = new TableColumn<>("Bil");
		TableColumn<Offer, String> clmEmployee = new TableColumn<>("Sï¿½lger");
		TableColumn<Offer, String> clmLoanValue = new TableColumn<>("Hovedstol");
		TableColumn<Offer, String> clmNumOfTerms = new TableColumn<>("Lï¿½betid");
		TableColumn<Offer, String> clmCustomerAccept = new TableColumn<>("Accepteret");
		TableColumn<Offer, String> clmManagerAccept = new TableColumn<>("Godkendt");

		// ADD COLUMNS TO TABLEVIEW
		tvOffer.getColumns().addAll(clmCustomer, clmCar, clmEmployee, clmLoanValue, clmNumOfTerms, clmCustomerAccept,
				clmManagerAccept);
		
		// COLUMN WIDTH AND ALIGNMENT
		clmCustomer.prefWidthProperty().bind(tvOffer.widthProperty().multiply(0.14));
		clmCustomer.setStyle("-fx-alignment: CENTER");
		clmCar.prefWidthProperty().bind(tvOffer.widthProperty().multiply(0.13));
		clmCar.setStyle("-fx-alignment: CENTER");
		clmEmployee.prefWidthProperty().bind(tvOffer.widthProperty().multiply(0.13));
		clmEmployee.setStyle("-fx-alignment: CENTER");
		clmLoanValue.prefWidthProperty().bind(tvOffer.widthProperty().multiply(0.14));
		clmLoanValue.setStyle("-fx-alignment: CENTER");
		clmNumOfTerms.prefWidthProperty().bind(tvOffer.widthProperty().multiply(0.14));
		clmNumOfTerms.setStyle("-fx-alignment: CENTER");
		clmCustomerAccept.prefWidthProperty().bind(tvOffer.widthProperty().multiply(0.14));
		clmCustomerAccept.setStyle("-fx-alignment: CENTER");
		clmManagerAccept.prefWidthProperty().bind(tvOffer.widthProperty().multiply(0.14));
		clmManagerAccept.setStyle("-fx-alignment: CENTER");
		
		// ADD VALUES TO COLUMNS
		clmCustomer.setCellValueFactory(cellData -> {
			return new ReadOnlyStringWrapper(cellData.getValue().getOfferCustomer().getCprNumber());
		});
		clmCar.setCellValueFactory(cellData -> {
			return new ReadOnlyStringWrapper(cellData.getValue().getOfferCar().getSerialNumber());
		});
		clmEmployee.setCellValueFactory(cellData -> {
			return new ReadOnlyStringWrapper(cellData.getValue().getOfferEmployee().getEmployeeID());
		});
		clmLoanValue.setCellValueFactory(new PropertyValueFactory<Offer, String>("loanValue"));
		clmNumOfTerms.setCellValueFactory(new PropertyValueFactory<Offer, String>("numOfTerms"));
		clmCustomerAccept.setCellValueFactory(cellData -> {
			return new ReadOnlyStringWrapper(cellData.getValue().isCustomerAccept() ? "Ja" : "Nej");
		});
		clmManagerAccept.setCellValueFactory(cellData -> {
			return new ReadOnlyStringWrapper(cellData.getValue().isManagerAccept() ? "Ja" : "Nej");
		});

		// ADD TABLEVIEW TO VBOX
		vBoxLeft.getChildren().add(tvOffer);
		vBoxLeft.setAlignment(Pos.CENTER);

		// ADD LIST TO TABLEVIEW
		populateTableView(offerLogic.completeOfferList());

		// BACK BUTTON
		Button btnBack = cb.btn("Tilbage", 3, 1);
		btnBack.setOnAction(e -> {
			SceneMainMenu scMainMenu = new SceneMainMenu();
			scMainMenu.init(stage);
		});
		btnBack.setAlignment(Pos.CENTER);
		Button btnPrintCSV = cb.btn("Print til CSV-Fil", 3, 1);
		btnPrintCSV.setOnAction(e -> {
			new PrintCSV(tvOffer.getSelectionModel().getSelectedItem());
			StagePopUp stPP = new StagePopUp();
			stPP.init(new Stage(), "CSV-Fil printet", null);
		});
		vBoxLeft.getChildren().add(btnPrintCSV);
		vBoxLeft.getChildren().add(btnBack);

		// RIGHT SIDE
		HBox hBoxTitle = new HBox(10);
		hBoxTitle.setAlignment(Pos.TOP_CENTER);
		Label lbTitle = cl.lb("Lï¿½netilbud:");
		Label lbTitleValue = cl.lb();
		hBoxTitle.getChildren().addAll(lbTitle, lbTitleValue);
		vBoxRight.getChildren().add(hBoxTitle);

		Label lbCustomer = cl.lb("Kunde");
		vBoxRight.getChildren().add(lbCustomer);
		HBox hBoxCustomer = new HBox();

		VBox vBoxCustomer1 = new VBox(spacing);
		vBoxCustomer1.setPrefWidth(stage.getWidth() * 0.10);
		Label lbCustomerName = cl.lb("Navn:", Constants.textSize * 0.75);
		Label lbCustomerPhone = cl.lb("Telefonnummer:", Constants.textSize * 0.75);
		Label lbCustomerAddress = cl.lb("Addresse:", Constants.textSize * 0.75);
		vBoxCustomer1.getChildren().addAll(lbCustomerName, lbCustomerPhone, lbCustomerAddress);

		VBox vBoxCustomer2 = new VBox(spacing);
		vBoxCustomer2.setPrefWidth(stage.getWidth() * 0.2);
		Label lbCustomerNameValue = cl.lb("", Constants.textSize * 0.75);
		Label lbCustomerPhoneValue = cl.lb("", Constants.textSize * 0.75);
		Label lbCustomerAddressValue = cl.lb("", Constants.textSize * 0.75);
		vBoxCustomer2.getChildren().addAll(lbCustomerNameValue, lbCustomerPhoneValue, lbCustomerAddressValue);

		VBox vBoxCustomer3 = new VBox(spacing);
		vBoxCustomer3.setPrefWidth(stage.getWidth() * 0.1);
		Label lbCustomerCprNumber = cl.lb("CPR-Nummer:", Constants.textSize * 0.75);
		Label lbCustomerEMail = cl.lb("eMail:", Constants.textSize * 0.75);
		vBoxCustomer3.getChildren().addAll(lbCustomerCprNumber, lbCustomerEMail);

		VBox vBoxCustomer4 = new VBox(spacing);
		vBoxCustomer4.setPrefWidth(stage.getWidth() * 0.15);
		Label lbCustomerCprNumberValue = cl.lb("", Constants.textSize * 0.75);
		Label lbCustomerEMailValue = cl.lb("", Constants.textSize * 0.75);
		vBoxCustomer4.getChildren().addAll(lbCustomerCprNumberValue, lbCustomerEMailValue);

		hBoxCustomer.getChildren().addAll(vBoxCustomer1, vBoxCustomer2, vBoxCustomer3, vBoxCustomer4);
		hBoxCustomer.setStyle(JavaFXStyles.HBoxStyle);
		hBoxCustomer.setPadding(insets2);
		vBoxRight.getChildren().add(hBoxCustomer);

		Label lbCar = cl.lb("Bil");
		vBoxRight.getChildren().add(lbCar);

		HBox hBoxCar = new HBox(spacing);
		VBox vBoxCar1 = new VBox();
		vBoxCar1.setPrefWidth(stage.getWidth() * 0.1);
		Label lbCarModel = cl.lb("Model:", Constants.textSize * 0.75);
		Label lbCarMileage = cl.lb("Kilometer:", Constants.textSize * 0.75);
		vBoxCar1.getChildren().addAll(lbCarModel, lbCarMileage);

		VBox vBoxCar2 = new VBox(spacing);
		vBoxCar2.setPrefWidth(stage.getWidth() * 0.2);
		Label lbCarModelValue = cl.lb("", Constants.textSize * 0.75);
		Label lbCarMileageValue = cl.lb("", Constants.textSize * 0.75);
		vBoxCar2.getChildren().addAll(lbCarModelValue, lbCarMileageValue);

		VBox vBoxCar3 = new VBox(spacing);
		vBoxCar3.setPrefWidth(stage.getWidth() * 0.1);
		Label lbCarModelYear = cl.lb("ï¿½rgang:", Constants.textSize * 0.75);
		Label lbCarSerialNumber = cl.lb("Serienummer:", Constants.textSize * 0.75);
		vBoxCar3.getChildren().addAll(lbCarModelYear, lbCarSerialNumber);

		VBox vBoxCar4 = new VBox(spacing);
		vBoxCar4.setPrefWidth(stage.getWidth() * 0.15);
		Label lbCarModelYearValue = cl.lb("", Constants.textSize * 0.75);
		Label lbCarSerialNumberValue = cl.lb("", Constants.textSize * 0.75);
		vBoxCar4.getChildren().addAll(lbCarModelYearValue, lbCarSerialNumberValue);

		hBoxCar.getChildren().addAll(vBoxCar1, vBoxCar2, vBoxCar3, vBoxCar4);
		hBoxCar.setStyle(JavaFXStyles.HBoxStyle);
		hBoxCar.setPadding(insets2);
		vBoxRight.getChildren().add(hBoxCar);

		HBox hBoxEmployee = new HBox(30);
		Label lbEmployeeName = cl.lb("Sï¿½lger:", Constants.textSize * 0.75);
		Label lbEmployeeNameValue = cl.lb("", Constants.textSize * 0.75);
		hBoxEmployee.getChildren().addAll(lbEmployeeName, lbEmployeeNameValue);
		hBoxEmployee.setPadding(insets2);
		vBoxRight.getChildren().add(hBoxEmployee);

		HBox hBoxInfo = new HBox();
		VBox vBoxInfo1 = new VBox(spacing);
		vBoxInfo1.setPrefWidth(stage.getWidth() * 0.1);
		Label lbPrice = cl.lb("Pris:", Constants.textSize * 0.75);
		Label lbDownPayment = cl.lb("Udbetaling:", Constants.textSize * 0.75);
		Label lbLoanValue = cl.lb("Lï¿½nebelï¿½b:", Constants.textSize * 0.75);
		vBoxInfo1.getChildren().addAll(lbPrice, lbDownPayment, lbLoanValue);

		VBox vBoxInfo2 = new VBox(spacing);
		vBoxInfo2.setPrefWidth(stage.getWidth() * 0.2);
		Label lbPriceValue = cl.lb("", Constants.textSize * 0.75);
		Label lbDownPaymentValue = cl.lb("", Constants.textSize * 0.75);
		Label lbLoanValueValue = cl.lb("", Constants.textSize * 0.75);
		vBoxInfo2.getChildren().addAll(lbPriceValue, lbDownPaymentValue, lbLoanValueValue);

		VBox vBoxInfo3 = new VBox(spacing);
		vBoxInfo3.setPrefWidth(stage.getWidth() * 0.1);
		Label lbRate = cl.lb("Rente:", Constants.textSize * 0.75);
		Label lbNumOfTerms = cl.lb("Lï¿½betid:", Constants.textSize * 0.75);
		vBoxInfo3.getChildren().addAll(lbRate, lbNumOfTerms);

		VBox vBoxInfo4 = new VBox(spacing);
		vBoxInfo4.setPrefWidth(stage.getWidth() * 0.15);
		Label lbRateValue = cl.lb("", Constants.textSize * 0.75);
		Label lbNumOfTermsValue = cl.lb("", Constants.textSize * 0.75);
		vBoxInfo4.getChildren().addAll(lbRateValue, lbNumOfTermsValue);
		hBoxInfo.getChildren().addAll(vBoxInfo1, vBoxInfo2, vBoxInfo3, vBoxInfo4);
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
			return new ReadOnlyStringWrapper(cellData.getValue().getPreviousBalance() + " kr");
		});
		clmPayment.setCellValueFactory(cellData -> {
			return new ReadOnlyStringWrapper(cellData.getValue().getPayment() + " kr");
		});
		clmInterest.setCellValueFactory(cellData -> {
			return new ReadOnlyStringWrapper(cellData.getValue().getInterest() + " kr");
		});
		clmPrincipal.setCellValueFactory(cellData -> {
			return new ReadOnlyStringWrapper(cellData.getValue().getPrincipal() + " kr");
		});
		clmNewBalance.setCellValueFactory(cellData -> {
			return new ReadOnlyStringWrapper(cellData.getValue().getNewBalance() + " kr");
		});

		// BtnSearch function
		btnSearch.setOnAction(e -> {
			if (cbSearch.getSelectionModel().getSelectedIndex() != -1 && !tfSearch.getText().trim().isEmpty()) {
				tvOffer.getItems().clear();
				populateTableView(ls.sortOffer(offerLogic.getCompleteList(),
						cbSearch.getSelectionModel().getSelectedItem(), tfSearch.getText()));
			}
		});
		vBoxRight.getChildren().add(tvTerm);

		HBox hBoxConfirmCustomer = new HBox();
		hBoxConfirmCustomer.setSpacing(20);
		Label lbAcceptCustomer = cl.lb("Kunde: ");
		lbAcceptCustomer.setMinWidth(stage.getWidth() / 10);
		Button btnAcceptCustomer = cb.btn("Accepteret", 3, 1);
		btnAcceptCustomer.setDisable(true);
		btnAcceptCustomer.setOnAction(e -> {
			// OPDATER BOOLEAN VALUE I DATABASE
			tvOffer.getSelectionModel().getSelectedItem().setCustomerAccept(true);
			new OfferLogic().offerUpdate(tvOffer.getSelectionModel().getSelectedItem());
			tvOffer.refresh();
		});
		Button btnDeclineCustomer = cb.btn("Ikke Accepteret", 3, 1);
		btnDeclineCustomer.setDisable(true);
		btnDeclineCustomer.setOnAction(e -> {
			// OPDATER BOOLEAN VALUE I DATABSE
			tvOffer.getSelectionModel().getSelectedItem().setCustomerAccept(false);
			new OfferLogic().offerUpdate(tvOffer.getSelectionModel().getSelectedItem());
			tvOffer.refresh();
		});
		hBoxConfirmCustomer.getChildren().addAll(lbAcceptCustomer, btnAcceptCustomer, btnDeclineCustomer);
		hBoxConfirmCustomer.setAlignment(Pos.CENTER);
		vBoxRight.getChildren().add(hBoxConfirmCustomer);

		HBox hBoxConfirmManager = new HBox();
		hBoxConfirmManager.setSpacing(20);
		Label lbAcceptManager = cl.lb("Salgschef: ");
		lbAcceptManager.setMinWidth(stage.getWidth() / 10);

		Button btnAcceptManager = cb.btn("Godkend", 3, 1);
		btnAcceptManager.setDisable(true);
		btnAcceptManager.setOnAction(e -> {
			// OPDATER BOOLEAN VALUE I DATABASE
			tvOffer.getSelectionModel().getSelectedItem().setManagerAccept(true);
			new OfferLogic().offerUpdate(tvOffer.getSelectionModel().getSelectedItem());
			tvOffer.refresh();
		});
		Button btnDeclineManager = cb.btn("Afslï¿½", 3, 1);
		btnDeclineManager.setDisable(true);
		btnDeclineManager.setOnAction(e -> {
			// OPDATER BOOLEAN VALUE I DATABSE
			tvOffer.getSelectionModel().getSelectedItem().setManagerAccept(false);
			new OfferLogic().offerUpdate(tvOffer.getSelectionModel().getSelectedItem());
			tvOffer.refresh();
		});
		hBoxConfirmManager.getChildren().addAll(lbAcceptManager, btnAcceptManager, btnDeclineManager);
		hBoxConfirmManager.setAlignment(Pos.CENTER);
		if(ActiveEmployee.getInstance().getEmployee().getTitle().equals("Manager")) {
		vBoxRight.getChildren().add(hBoxConfirmManager);
		}

		// LISTENER TO CHANGE RIGHTSIDE BASED ON SELECTION LEFT SIDE
		tvOffer.getSelectionModel().selectedItemProperty().addListener(c -> {
			if (tvOffer.getSelectionModel().getSelectedIndex() != -1) {
				Offer o = tvOffer.getSelectionModel().getSelectedItem();
				lbTitleValue.setText(o.getOfferID());
				lbCustomerNameValue.setText(o.getOfferCustomer().getFirstName() + o.getOfferCustomer().getLastName());
				lbCustomerPhoneValue.setText(o.getOfferCustomer().getPhoneNumber());
				lbCustomerCprNumberValue.setText(o.getOfferCustomer().getCprNumber());
				lbCustomerEMailValue.setText(o.getOfferCustomer().geteMail());
				lbCustomerAddressValue.setText(o.getOfferCustomer().getAddress() + o.getOfferCustomer().getZipCode()
						+ o.getOfferCustomer().getCity());

				lbCarModelValue.setText(o.getOfferCar().getModel());
				lbCarMileageValue.setText(o.getOfferCar().getMileage());
				lbCarModelYearValue.setText(o.getOfferCar().getModelYear());
				lbCarSerialNumberValue.setText(o.getOfferCar().getSerialNumber());

				lbEmployeeNameValue.setText(o.getOfferEmployee().getFirstName() + " " + o.getOfferEmployee().getLastName());

				lbPriceValue.setText(o.getOfferCar().getPrice());
				lbDownPaymentValue.setText(o.getDownPayment());
				lbLoanValueValue.setText(o.getLoanValue());
				lbRateValue.setText(o.getRate()+" %");
				lbNumOfTermsValue.setText(Integer.toString(o.getNumOfTerms())+" måneder");
				tvTerm.setItems(FXCollections.observableArrayList(o.getPeriods()));
				btnAcceptCustomer.setDisable(false);
				btnAcceptManager.setDisable(false);
				btnDeclineCustomer.setDisable(false);
				btnDeclineManager.setDisable(false);
			}

		});

		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		stage.setScene(scene);
	}

	private void populateTableView(ArrayList<Offer> arrayList) {
		ObservableList<Offer> olOffer = FXCollections.observableArrayList(arrayList);
		tvOffer.setItems(olOffer);
	}

	private void clear() {
		tfSearch.clear();
		cbSearch.valueProperty().set(null);
	}
}
