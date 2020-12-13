package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.entities.Seller;
import model.services.SellerService;

public class SellerListController implements Initializable {
	
	private SellerService service;
	private ObservableList<Seller> obslist;

	@FXML
	private TableView<Seller> tbVSeller;
	
	@FXML
	private Button btNew;
	
	@FXML
	private TableColumn<Seller, Integer> tbColumnId;

	@FXML
	private TableColumn<Seller, String> tbColumnName;
	
	@FXML
	private TableColumn<Seller, String> tbColumnEmail;
	
	@FXML
	private TableColumn<Seller, Date> tbColumnBDate;
	
	@FXML
	private TableColumn<Seller, Double> tbColumnBSalary;
	
	@FXML
	private TableColumn<Seller, String> tbColumnDepartment;
	
	@FXML
	public void onBtAction() {
		loadView("/gui/SellerNew.fxml");
	}
	
	private void loadView(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			
			 Node mainMenu = mainVBox.getChildren().get(0);
			 mainVBox.getChildren().clear(); mainVBox.getChildren().add(mainMenu);
			 mainVBox.getChildren().addAll(newVBox.getChildren());
			 			
			
	}
	catch (IOException e) {
		//Alerts.showAlert("IO Exception","Error loading view", e.getMessage(),AlertType.ERROR);
		e.printStackTrace();
		}
	}
		
	

	public void setSellerService(SellerService service) {
		
		this.service = service;	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodesSeller();
		
	}
	
	 private void initializeNodesSeller() { 
			
		  tbColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		  tbColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		  tbColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));		
		  tbColumnBDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));		
		  tbColumnBSalary.setCellValueFactory(new PropertyValueFactory<>("baseSalary"));
		  //tbColumnDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));		
		  tbColumnDepartment.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getDepartment().getName()));
		  
		  Stage stage = (Stage) Main.getMainScene().getWindow();
		  tbVSeller.prefHeightProperty().bind(stage.heightProperty());
			 
	  }
	  
	 public void updateTableViewSeller() {
		  if(service == null) {
			  throw new IllegalStateException("Service was null");
		  }
		  List<Seller> list = service.findAll();
		  obslist = FXCollections.observableArrayList(list);
		  tbVSeller.setItems(obslist);
		  
	  }
	

}
