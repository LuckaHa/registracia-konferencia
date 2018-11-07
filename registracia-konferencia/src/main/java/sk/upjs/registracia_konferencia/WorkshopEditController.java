package sk.upjs.registracia_konferencia;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sk.upjs.registracia_konferencia.entity.Workshop;
import sk.upjs.registracia_konferencia.persistent.DAOFactory;
import sk.upjs.registracia_konferencia.persistent.WorkshopDAO;

public class WorkshopEditController {
	
	private WorkshopDAO workshopDAO;
	private WorkshopFXModel workshopModel;

    @FXML
    private ComboBox<Workshop> workshopComboBox;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField priceFullTextField;

    @FXML
    private TextField priceStudentTextField;

    @FXML
    private TextField priceFullLateTextField;

    @FXML
    private TextField priceStudentLateTextField;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private Button saveButton;
    
    public WorkshopEditController() {
    	workshopDAO = DAOFactory.INSTANCE.getWorkshopDAO();
    	workshopModel = new WorkshopFXModel();
    }

    @FXML
    void initialize() {
    	workshopComboBox.setItems(FXCollections.observableArrayList(workshopDAO.getAll()));
    	nameTextField.textProperty().bindBidirectional(workshopModel.nameProperty());
    	//priceFullLateTextField.textProperty().bindBidirectional(workshopModel.getPriceFullLateProperty());
    }
}
