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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.ListSort;
import logic.OfferLogic;

public class SceneLookUpOffer {
	TextField tfSearch;
	Stage stage;
	VBox vBoxLeft, vBoxRight;
	HBox hBoxCenter;
	Insets insets = new Insets(15, 15, 15, 15);
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
		int spacing = 15;
		BorderPane root = new BorderPane();
		root.setPadding(insets);
		vBoxLeft = new VBox(20);
		vBoxLeft.setAlignment(Pos.TOP_CENTER);
		vBoxLeft.setPrefWidth(stage.getWidth()/2);
		vBoxRight = new VBox(20);
		vBoxRight.setAlignment(Pos.TOP_CENTER);
		vBoxRight.setPrefWidth(stage.getWidth()/2);	
		hBoxCenter = new HBox(20);
		hBoxCenter.setAlignment(Pos.TOP_CENTER);
		hBoxCenter.getChildren().addAll(vBoxLeft, vBoxRight);
		
		root.setCenter(hBoxCenter);
	
		// LEFT SIDE
		Button btnAllOffers = cb.btn("Se alle ordrer", 4, 1);
		Button btnMissingCustomer = cb.btn("Order der mangler kundeaccept", 4, 1);
		Button btnMissingManager = cb.btn("Order der mangler godkendelse", 4, 1);
		vBoxLeft.getChildren().addAll(btnAllOffers, btnMissingCustomer, btnMissingManager);

		// SEARCH
		HBox hbSearch = new HBox();
		cbSearch = new ComboBox<>();
		cbSearch.setPrefSize(Constants.stageWidth / 10, Constants.stageHeight / 20);
		cbSearch.getItems().add("Sælger");
		cbSearch.getItems().add("Kunde");
		cbSearch.getItems().add("Bil");

		tfSearch = ctf.tf();
		tfSearch.setPromptText("Vælg søgekriterie");

		cbSearch.getSelectionModel().selectedItemProperty().addListener(c -> {
			tfSearch.clear();
			if (cbSearch.getSelectionModel().getSelectedItem() != null) {
				switch (cbSearch.getSelectionModel().getSelectedItem()) {
				case "Sælger":
					tfSearch.setPromptText("Indtast sælger ID");
					break;
				case "Kunde":
					tfSearch.setPromptText("Indtast kundens CPR-Nummer");
					break;
				case "Bil":
					tfSearch.setPromptText("Indtast bilens serienummer");
					break;
				}
			} else
				tfSearch.setPromptText("Vælg søgekriterie");
		});
		Button btnSearch = cb.btn("Søg");
		hbSearch.getChildren().addAll(cbSearch, tfSearch, btnSearch);
		vBoxLeft.getChildren().add(hbSearch);

		// TABLEVIEW OF ORDERS
		tvOffer = new TableView<Offer>();
		tvOffer.setPlaceholder(new Label("Ingen tilbud fundet"));
		TableColumn<Offer, String> clmCustomer = new TableColumn<>("Kunde");
		TableColumn<Offer, String> clmCar = new TableColumn<>("Bil");
		TableColumn<Offer, String> clmEmployee = new TableColumn<>("Sælger");
		TableColumn<Offer, String> clmLoanValue = new TableColumn<>("Hovedstol");
		TableColumn<Offer, String> clmNumOfTerms = new TableColumn<>("Løbetid");
		TableColumn<Offer, String> clmCustomerAccept = new TableColumn<>("Accepteret");
		TableColumn<Offer, String> clmManagerAccept = new TableColumn<>("Godkendt");

		// ADD COLUMNS TO TABLEVIEW
		tvOffer.getColumns().addAll(clmCustomer, clmCar, clmEmployee, clmLoanValue, clmNumOfTerms, clmCustomerAccept,
				clmManagerAccept);

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
		clmCustomerAccept.setCellValueFactory(cellData -> {
			return new ReadOnlyStringWrapper(cellData.getValue().isCustomerAccept() ? "Ja" : "Nej");
		});
		clmManagerAccept.setCellValueFactory(cellData -> {
			return new ReadOnlyStringWrapper(cellData.getValue().isCustomerAccept() ? "Ja" : "Nej");
		});

		// ADD TABLEVIEW TO VBOX
		vBoxLeft.getChildren().add(tvOffer);

		// ADD LIST TO TABLEVIEW
		populateTableView(offerLogic.completeOfferList());

		// RIGHT SIDE
		HBox hBoxTitle = new HBox(10);
		hBoxTitle.setAlignment(Pos.TOP_CENTER);
		Label lbTitle = cl.lb("Lånetilbud:");
		Label lbTitleValue = cl.lb();
		hBoxTitle.getChildren().addAll(lbTitle,lbTitleValue);
		vBoxRight.getChildren().add(hBoxTitle);
		
		Label lbCustomer = cl.lb("Kunde");
		vBoxRight.getChildren().add(lbCustomer);		
		HBox hBoxCustomer = new HBox();
		
		VBox vBoxCustomer1 = new VBox(spacing);
		vBoxCustomer1.setPrefWidth(stage.getWidth()*0.1);
		Label lbCustomerName = cl.lb("Navn:");
		Label lbCustomerPhone = cl.lb("Telefonnummer:");
		Label lbCustomerAddress = cl.lb("Addresse");
		vBoxCustomer1.getChildren().addAll(lbCustomerName,lbCustomerPhone, lbCustomerAddress);
		
		VBox vBoxCustomer2 = new VBox(spacing);
		vBoxCustomer2.setPrefWidth(stage.getWidth()*0.2);
		Label lbCustomerNameValue = cl.lb();
		Label lbCustomerPhoneValue = cl.lb();
		Label lbCustomerAddressValue = cl.lb();
		vBoxCustomer2.getChildren().addAll(lbCustomerNameValue, lbCustomerPhoneValue, lbCustomerAddressValue);
		
		VBox vBoxCustomer3 = new VBox(spacing);
		vBoxCustomer3.setPrefWidth(stage.getWidth()*0.1);
		Label lbCustomerCprNumber = cl.lb("CPR-Nummer:");
		Label lbCustomerEMail = cl.lb("eMail:");
		vBoxCustomer3.getChildren().addAll(lbCustomerCprNumber, lbCustomerEMail);
		
		VBox vBoxCustomer4 = new VBox(spacing);
		vBoxCustomer4.setPrefWidth(stage.getWidth()*0.15);
		Label lbCustomerCprNumberValue = cl.lb();
		Label lbCustomerEMailValue = cl.lb();
		vBoxCustomer4.getChildren().addAll(lbCustomerCprNumberValue, lbCustomerEMailValue);
		
		hBoxCustomer.getChildren().addAll(vBoxCustomer1,vBoxCustomer2,vBoxCustomer3,vBoxCustomer4);
		vBoxRight.getChildren().add(hBoxCustomer);
				
		Label lbCar = cl.lb("Bil");
		vBoxRight.getChildren().add(lbCar);
		
		HBox hBoxCar = new HBox();
		VBox vBoxCar1 = new VBox();
		vBoxCar1.setPrefWidth(stage.getWidth()*0.1);
		Label lbCarModel = cl.lb("Model");
		Label lbCarMileage = cl.lb("Kilometer");
		vBoxCar1.getChildren().addAll(lbCarModel, lbCarMileage);
		
		VBox vBoxCar2 = new VBox(spacing); 
		vBoxCar2.setPrefWidth(stage.getWidth()*0.2);
		Label lbCarModelValue = cl.lb();
		Label lbCarMileageValue = cl.lb();
		vBoxCar2.getChildren().addAll(lbCarModelValue, lbCarMileageValue);
		
		VBox vBoxCar3 = new VBox(spacing);
		vBoxCar3.setPrefWidth(stage.getWidth()*0.1);
		Label lbCarModelYear = cl.lb("Årgang");
		Label lbCarSerialNumber = cl.lb("Serienummer");
		vBoxCar3.getChildren().addAll(lbCarModelYear, lbCarSerialNumber);
		
		VBox vBoxCar4 = new VBox(spacing);
		vBoxCar4.setPrefWidth(stage.getWidth()*0.15);	
		Label lbCarModelYearValue = cl.lb();
		Label lbCarSerialNumberValue = cl.lb();
		vBoxCar4.getChildren().addAll(lbCarModelYearValue, lbCarSerialNumberValue);
		
		hBoxCar.getChildren().addAll(vBoxCar1,vBoxCar2,vBoxCar3,vBoxCar4);
		vBoxRight.getChildren().add(hBoxCar);
		
		HBox hBoxEmployee = new HBox();
		Label lbEmployeeName = cl.lb("Sælger");
		Label lbEmployeeNameValue = cl.lb();
		hBoxEmployee.getChildren().addAll(lbEmployeeName,lbEmployeeNameValue);
		vBoxRight.getChildren().add(hBoxEmployee);
		
		HBox hBoxInfo = new HBox();
		VBox vBoxInfo1 = new VBox(spacing);
		vBoxInfo1.setPrefWidth(stage.getWidth()*0.1);
		Label lbPrice = cl.lb("Pris");
		Label lbDownPayment = cl.lb("Udbetaling");
		Label lbLoanValue = cl.lb("Lånebeløb");
		vBoxInfo1.getChildren().addAll(lbPrice, lbDownPayment, lbLoanValue);
		
		VBox vBoxInfo2 = new VBox(spacing);
		vBoxInfo2.setPrefWidth(stage.getWidth()*0.2);
		Label lbPriceValue = cl.lb();
		Label lbDownPaymentValue = cl.lb();
		Label lbLoanValueValue = cl.lb();
		vBoxInfo2.getChildren().addAll(lbPriceValue, lbDownPaymentValue, lbLoanValueValue);
		
		VBox vBoxInfo3 = new VBox(spacing);
		vBoxInfo3.setPrefWidth(stage.getWidth()*0.1);
		Label lbRate = cl.lb("Rente:");
		Label lbNumOfTerms = cl.lb("Løbetid");
		vBoxInfo3.getChildren().addAll(lbRate, lbNumOfTerms);
		
		VBox vBoxInfo4 = new VBox(spacing);
		vBoxInfo4.setPrefWidth(stage.getWidth()*0.15);
		Label lbRateValue = cl.lb();
		Label lbNumOfTermsValue = cl.lb();
		vBoxInfo4.getChildren().addAll(lbRateValue, lbNumOfTermsValue);
		hBoxInfo.getChildren().addAll(vBoxInfo1,vBoxInfo2,vBoxInfo3,vBoxInfo4);
		vBoxRight.getChildren().add(hBoxInfo);
		
		// CREATE TABLEVIEW
		tvTerm = new TableView<Term>();
		TableColumn<Term, String> clmTermNumber = new TableColumn<>("Termin nr:");
		TableColumn<Term, String> clmPreviousBalance = new TableColumn<>("Primo Restgæld");
		TableColumn<Term, String> clmPayment = new TableColumn<>("Ydelse");
		TableColumn<Term, String> clmInterest = new TableColumn<>("Rente");
		TableColumn<Term, String> clmPrincipal = new TableColumn<>("Afdrag");
		TableColumn<Term, String> clmNewBalance = new TableColumn<>("Ultimo Restgæld");

		// ADD COLUMNS TO TABLEVIEW
		tvTerm.getColumns().addAll(clmTermNumber, clmPreviousBalance, clmPayment, clmInterest, clmPrincipal,
				clmNewBalance);

		// ADD VALUES TO COLUMNS
		clmTermNumber.setCellValueFactory(new PropertyValueFactory<Term, String>("termNumber"));
		clmPreviousBalance.setCellValueFactory(new PropertyValueFactory<Term, String>("previousBalance"));
		clmPayment.setCellValueFactory(new PropertyValueFactory<Term, String>("payment"));
		clmInterest.setCellValueFactory(new PropertyValueFactory<Term, String>("interest"));
		clmPrincipal.setCellValueFactory(new PropertyValueFactory<Term, String>("principal"));
		clmNewBalance.setCellValueFactory(new PropertyValueFactory<Term, String>("newBalance"));

		// BtnSearch function
		btnSearch.setOnAction(e -> {
			tvOffer.getItems().clear();
			populateTableView(ls.sortOffer(offerLogic.getCompleteList(), cbSearch.getSelectionModel().getSelectedItem(),
					tfSearch.getText()));
		});
		btnAllOffers.setOnAction(e -> {
			clear();
			populateTableView(offerLogic.getCompleteList());
		});
		btnMissingCustomer.setOnAction(e -> {
			populateTableView(ls.sortOffer(offerLogic.getCompleteList(), "CustomerAccept", ""));
		});
		btnMissingManager.setOnAction(e -> {
			populateTableView(ls.sortOffer(offerLogic.getCompleteList(), "ManagerAccept", ""));
		});

		vBoxRight.getChildren().add(tvTerm);
		
		
		// LISTENER TO CHANGE RIGHTSIDE BASED ON SELECTION LEFT SIDE
		tvOffer.getSelectionModel().selectedItemProperty().addListener(c -> {
			Offer o = tvOffer.getSelectionModel().getSelectedItem();
			lbTitleValue.setText(o.getOfferID());
			lbCustomerNameValue.setText(o.getOfferCustomer().getFirstName()+o.getOfferCustomer().getLastName());
			lbCustomerPhoneValue.setText(o.getOfferCustomer().getPhoneNumber());
			lbCustomerCprNumberValue.setText(o.getOfferCustomer().getCprNumber());
			lbCustomerEMailValue.setText(o.getOfferCustomer().geteMail());
			lbCustomerAddressValue.setText(o.getOfferCustomer().getAddress()+o.getOfferCustomer().getZipCode()+o.getOfferCustomer().getCity());
			
			lbCarModelValue.setText(o.getOfferCar().getModel());
			lbCarMileageValue.setText(o.getOfferCar().getMileage());
			lbCarModelYearValue.setText(o.getOfferCar().getModelYear());
			lbCarSerialNumberValue.setText(o.getOfferCar().getSerialNumber());
			
			lbEmployeeNameValue.setText(o.getOfferEmployee().getFirstName()+ " " + o.getOfferEmployee().getLastName());
			
			lbPriceValue.setText(o.getOfferCar().getPrice());
			lbDownPaymentValue.setText(o.getDownPayment());
			lbLoanValueValue.setText(o.getLoanValue());
			lbRateValue.setText(o.getRate());
			lbNumOfTermsValue.setText(Integer.toString(o.getNumOfTerms()));
			tvTerm.setItems(FXCollections.observableArrayList(o.getPeriods()));
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
