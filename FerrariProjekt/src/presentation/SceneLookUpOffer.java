package presentation;

import create.Constants;
import create.CreateButton;
import create.CreateTextField;
import entities.Car;
import entities.Customer;
import entities.Employee;
import entities.Offer;
import entities.Term;
import javafx.beans.value.ChangeListener;
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
import logic.CalculateLoan;
import logic.CustomerController;
import logic.OfferLogic;

public class SceneLookUpOffer {
	TextField tfSearch;
	Stage stage;
	VBox vBoxLeft, VboxRight;
	Insets insets = new Insets(15, 15, 15, 15);
	TableView<Term> tvTerm;
	TableView<Offer> tvOffer;
	CreateButton cb = new CreateButton();
	CreateTextField ctf = new CreateTextField();

	public void init(Stage stage) {
		this.stage = stage;

		// Setup
		BorderPane root = new BorderPane();
		root.setPadding(insets);
		vBoxLeft = new VBox(20);
		root.setLeft(vBoxLeft);
		vBoxLeft.setAlignment(Pos.TOP_CENTER);

		// LEFT SIDE
		Button btnAllOffers = cb.btn("Se alle ordrer", 4, 1);
		Button btnMissingCustomer = cb.btn("Order der mangler kundeaccept", 4, 1);
		Button btnMissingManager = cb.btn("Order der mangler godkendelse", 4, 1);
		vBoxLeft.getChildren().addAll(btnAllOffers, btnMissingCustomer, btnMissingManager);

		// SEARCH
		HBox hbSearch = new HBox();	
		ComboBox<String> cbSearch = new ComboBox<>();
		cbSearch.setPrefSize(Constants.stageWidth/10, Constants.stageHeight/20);
		cbSearch.getItems().add("S�lger");
		cbSearch.getItems().add("Kunde");
		cbSearch.getItems().add("Bil");

		tfSearch = ctf.tf();
		tfSearch.setPromptText("V�lg s�gekriterie");

		cbSearch.getSelectionModel().selectedItemProperty().addListener(c -> {
			if (cbSearch.getSelectionModel().getSelectedItem() == "S�lger") {
				this.tfSearch.setPromptText("Indtast s�lger ID");
			}
			if (cbSearch.getSelectionModel().getSelectedItem() == "Kunde") {
				this.tfSearch.setPromptText("Indtast kundens CPR-Nummer");
			}
			if (cbSearch.getSelectionModel().getSelectedItem() == "Bil") {
				this.tfSearch.setPromptText("Indtast bilens serienummer");
			}
		});
		Button btnSearch = cb.btn("S�g");
		hbSearch.getChildren().addAll(cbSearch, tfSearch, btnSearch);
		vBoxLeft.getChildren().add(hbSearch);
		
		// TABLEVIEW OF ORDERS
		tvOffer = new TableView<Offer>();
		TableColumn<Offer, Customer> clmCustomer = new TableColumn<>("Kunde");
		TableColumn<Offer, Car> clmCar = new TableColumn<>("Bil");
		TableColumn<Offer, Employee> clmEmployee = new TableColumn<>("S�lger");
		TableColumn<Offer, String> clmLoanValue = new TableColumn<>("Hovedstol");
		TableColumn<Offer, String> clmNumOfTerms = new TableColumn<>("L�betid");
		TableColumn<Offer, String> clmCustomerAccept = new TableColumn<>("Accepteret");
		TableColumn<Offer, String> clmManagerAccept = new TableColumn<>("Godkendt");
		
		// ADD COLUMNS TO TABLEVIEW
		tvOffer.getColumns().addAll(clmCustomer,clmCar,clmEmployee,clmLoanValue,clmNumOfTerms,clmCustomerAccept,clmManagerAccept);

		clmCustomer.setCellValueFactory(new PropertyValueFactory<Offer, Customer>("customerID"));
		clmCar.setCellValueFactory(new PropertyValueFactory<Offer, Car>("serialNumber"));
		clmEmployee.setCellValueFactory(new PropertyValueFactory<Offer, Employee>("employeeID"));
		clmLoanValue.setCellValueFactory(new PropertyValueFactory<Offer, String>("loanValue"));
		clmCustomerAccept.setCellValueFactory(new PropertyValueFactory<Offer, String>("customerAccept"));
		clmManagerAccept.setCellValueFactory(new PropertyValueFactory<Offer, String>("managerAccept"));

		// RIGHT SIDE
		VboxRight = new VBox(20);
		root.setRight(VboxRight);

		// CREATE TABLEVIEW
		tvTerm = new TableView<Term>();
		TableColumn<Term, String> clmTermNumber = new TableColumn<>("Termin nr:");
		TableColumn<Term, String> clmPreviousBalance = new TableColumn<>("Primo Restg�ld");
		TableColumn<Term, String> clmPayment = new TableColumn<>("Ydelse");
		TableColumn<Term, String> clmInterest = new TableColumn<>("Rente");
		TableColumn<Term, String> clmPrincipal = new TableColumn<>("Afdrag");
		TableColumn<Term, String> clmNewBalance = new TableColumn<>("Ultimo Restg�ld");

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

		VboxRight.getChildren().addAll(tvTerm);

		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		stage.setScene(scene);

	}
}
