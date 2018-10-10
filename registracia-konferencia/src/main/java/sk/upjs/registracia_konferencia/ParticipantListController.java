package sk.upjs.registracia_konferencia;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class ParticipantListController {

    private ParticipantDAO participantDAO = new ParticipantDAO();
    private ObservableList<ParticipantFXModel> participantsModel;

    @FXML
    private ListView<ParticipantFXModel> participantsListView; // listener
    
    @FXML
    private TableView<ParticipantFXModel> participantsTableView; // listener
 
    @FXML
    void initialize() {
    	// participantDAO.getAll() vrati len list participantov, my chceme list modelov participantov
    	List<ParticipantFXModel> models = new ArrayList<>();
    	for (Participant p : participantDAO.getAll()) {
			models.add(new ParticipantFXModel(p));
		}
    	
    	participantsModel = FXCollections.observableArrayList(models);
    	participantsListView.setItems(participantsModel);
    	
    	TableColumn<ParticipantFXModel, Long> idCol = new TableColumn<>("ID");
    	TableColumn<ParticipantFXModel, String> nameCol = new TableColumn<>("Meno");
    	TableColumn<ParticipantFXModel, String> surnameCol = new TableColumn<>("Priezvisko");
    	TableColumn<ParticipantFXModel, String> emailCol = new TableColumn<>("E-mail");
    	
    	idCol.setCellValueFactory(new PropertyValueFactory<>("id")); //PropertyValueFactory vytvori getter id
    	nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    	surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
    	emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    	
    	emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
    	emailCol.setEditable(true);
    	
    	participantsTableView.getColumns().add(idCol);
    	participantsTableView.getColumns().add(nameCol);
    	participantsTableView.getColumns().add(surnameCol);
    	participantsTableView.getColumns().add(emailCol);
    	
    	participantsTableView.setItems(participantsModel);
    }
}