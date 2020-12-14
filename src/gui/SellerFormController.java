package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.services.DepartmentService;

public class SellerFormController implements Initializable {
	
	DepartmentService service;
	
	private ObservableList<Department> obslist;

	@FXML
	private TextField txtName;
	
	@FXML
	private TextField txtEmail;
	
	@FXML
	private TextField txtBirthDate;
	
	@FXML
	private TextField txtBaseSalary;
	
	@FXML
	private ComboBox<Department> cbDepartment;
	
	@FXML
	private Button btSave;
		
	@FXML
	private Button btCancel;
	
	@FXML
	private void onCbDepartmentAction(ActionEvent event) {
		//Department department = cbDepartment.getSelectionModel().getSelectedItem();
		System.out.println("OK");
		//cbDepartment.getValue().getName();
	}
	@FXML
	private void onBtSaveAction() {
		System.out.println("Dados Salvos");
	}
	
	@FXML
	private void onBtCancelAction() {
		System.out.println("Inserção de Dados Cencelada");
	}
	
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
		
	private void initializeNodes() {

		setDepartmentService(new DepartmentService());
		updateComboBoxDepartment();
		
		}
	
	private void updateComboBoxDepartment() {
		 if(service == null) {
			  throw new IllegalStateException("Service was null");
		  }
		List<Department> list = service.findAll();
		
		obslist = FXCollections.observableArrayList(list);
		cbDepartment.setItems(obslist);
		
	}
}


