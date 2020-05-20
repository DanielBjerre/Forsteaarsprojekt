package presentation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import create.Constants;
import create.CreateButton;
import create.CreateTextField;
import entities.Car;
import entities.Customer;
import entities.Employee;
import entities.Offer;
import entities.Term;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
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

	public void init(Stage stage) throws FileNotFoundException {
//		final Image imageTrue = new Image("file:trueImage.png");
//		final Image imageFalse = new Image("file:falseImage.png", 100, 100, false, false);

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
		cbSearch.getItems().add("Sælger");
		cbSearch.getItems().add("Kunde");
		cbSearch.getItems().add("Bil");	

		tfSearch = ctf.tf();
		tfSearch.setPromptText("Vælg søgekriterie");

		cbSearch.getSelectionModel().selectedItemProperty().addListener(c -> {
			if (cbSearch.getSelectionModel().getSelectedItem() == "Sælger") {
				this.tfSearch.setPromptText("Indtast sælger ID");
			}
			if (cbSearch.getSelectionModel().getSelectedItem() == "Kunde") {
				this.tfSearch.setPromptText("Indtast kundens CPR-Nummer");
			}
			if (cbSearch.getSelectionModel().getSelectedItem() == "Bil") {
				this.tfSearch.setPromptText("Indtast bilens serienummer");
			}
		});
		Button btnSearch = cb.btn("Søg");
		hbSearch.getChildren().addAll(cbSearch, tfSearch, btnSearch);
		vBoxLeft.getChildren().add(hbSearch);
		
		// TABLEVIEW OF ORDERS
		tvOffer = new TableView<Offer>();
		TableColumn<Offer, String> clmCustomer = new TableColumn<>("Kunde");
		TableColumn<Offer, String> clmCar = new TableColumn<>("Bil");
		TableColumn<Offer, String> clmEmployee = new TableColumn<>("Sælger");
		TableColumn<Offer, String> clmLoanValue = new TableColumn<>("Hovedstol");
		TableColumn<Offer, String> clmNumOfTerms = new TableColumn<>("Løbetid");
		TableColumn<Offer, ImageView> clmCustomerAccept = new TableColumn<>("Accepteret");
		TableColumn<Offer, Image> clmManagerAccept = new TableColumn<>("Godkendt");
		
		// ADD COLUMNS TO TABLEVIEW
		tvOffer.getColumns().addAll(clmCustomer,clmCar,clmEmployee,clmLoanValue,clmNumOfTerms,clmCustomerAccept,clmManagerAccept);

		// Add values to columns
		clmCustomer.setCellValueFactory(cellData -> {
			return new ReadOnlyStringWrapper(cellData.getValue().getOfferCustomer().getCprNumber());
		});
		clmCar.setCellValueFactory(cellData -> {
			return new ReadOnlyStringWrapper(cellData.getValue().getOfferCar().getSerialNumber());
		});
		clmEmployee.setCellValueFactory(cellData -> {
			return new ReadOnlyStringWrapper(cellData.getValue().getOfferEmployee().getEmployeeID());
		});
		clmNumOfTerms.setCellValueFactory(new PropertyValueFactory<Offer, String>("numOfTerms"));
		clmLoanValue.setCellValueFactory(new PropertyValueFactory<Offer, String>("loanValue"));
		clmCustomerAccept.setCellValueFactory(new PropertyValueFactory<Offer, ImageView> ("ivTrue"));

	 
		
		
		
		
		
		
//		clmManagerAccept.setCellValueFactory(new PropertyValueFactory<Offer, String>("managerAccept"));

		// Add items to tableview
		ObservableList<Offer> olOffer = FXCollections.observableList(new OfferLogic().completeOfferList());
		tvOffer.setItems(olOffer);
		vBoxLeft.getChildren().add(tvOffer);
		
		// RIGHT SIDE
		VboxRight = new VBox(20);
		root.setRight(VboxRight);

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

		VboxRight.getChildren().addAll(tvTerm);

		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		stage.setScene(scene);

	} 
} 
