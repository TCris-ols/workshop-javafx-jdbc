package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;

public class DepartmentListController implements Initializable {

	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Department, String> tableColumnName;
	
	@FXML
	private Button btNew;
	
	@FXML
	public void onBTNewAction() {
		System.out.println("onButtonAction");
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}

	  private void initializeNodes() { 
			/*
			 * tableColumnId = new TableColumn<>("id"); tableColumnName = new
			 * TableColumn<>("name");
			 */
		  tableColumnId.setCellValueFactory(new PropertyValueFactory<Department, Integer>("id"));
		  tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		  
		  //tableViewDepartment = new TableView<>();
		  
		  Stage stage = (Stage) Main.getMainScene().getWindow();
		  tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
			 
	  }
	 

}
