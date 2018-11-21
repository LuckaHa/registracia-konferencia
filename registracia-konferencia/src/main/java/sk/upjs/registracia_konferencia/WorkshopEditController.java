package sk.upjs.registracia_konferencia;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
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
    	workshopComboBox.getSelectionModel().selectedItemProperty()
    		.addListener(new ChangeListener<Workshop>() {

			@Override
			public void changed(ObservableValue<? extends Workshop> observable, Workshop oldValue, Workshop newValue) {
					workshopModel.setWorkshop(newValue);			
			}
		});
    	if(!workshopComboBox.getItems().isEmpty()) {
    		workshopComboBox.getSelectionModel().select(workshopComboBox.getItems().get(0));
    	}
    	nameTextField.textProperty().bindBidirectional(workshopModel.nameProperty());
    	priceFullLateTextField.textProperty().bindBidirectional(workshopModel.priceFullProperty(), new NumberStringConverter());
    	saveButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				workshopDAO.save(workshopModel.getWorkshop());
				saveButton.getScene().getWindow().hide();
			}
		});
    }
}
