package sk.upjs.registracia_konferencia;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.registracia_konferencia.entity.Participant;
import sk.upjs.registracia_konferencia.persistent.DAOFactory;
import sk.upjs.registracia_konferencia.persistent.ParticipantDAO;

public class ParticipantListController {

    private ParticipantDAO participantDAO = DAOFactory.INSTANCE.getParticipantDAO();
    private ObservableList<Participant> participantsModel;
    private Map<String, BooleanProperty> columnsVisibility = new LinkedHashMap<>(); // HashMap prehadzuje poradie
    private ObjectProperty<Participant> selectedPatricipant = new SimpleObjectProperty<>(); // ked sa zmeni referencia na objekt, nova udalost
    private ObjectProperty<Participant> selectedWorkshop = new SimpleObjectProperty<>();
    
    @FXML
    private TableView<Participant> participantsTableView; // listener
    
    @FXML
    private Button editParticipantButton;
 
    @FXML
    private Button editWorkshopsButton;
    
    @FXML
    void initialize() {
    	// TOTO je ak pouzivame ParticipantFXModel
    	// participantDAO.getAll() vrati len list participantov, my chceme list modelov participantov
    	/*List<ParticipantFXModel> models = new ArrayList<>();
    	for (Participant p : participantDAO.getAll()) {
			models.add(new ParticipantFXModel(p));
		}*/
    	
    	participantsModel = FXCollections.observableArrayList(participantDAO.getAll());
    	
    	// OKNO EDITACIE
    	editParticipantButton.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				ParticipantEditController editController = new ParticipantEditController(selectedPatricipant.get());
				showModalWindow(editController, "ParticpiantList.fxml");
				// to iste pre WorkshopEdit ... skontroluj podla Gurskeho
				WorkshopEditController wEditController = new WorkshopEditController();
				showModalWindow(wEditController, "WorkshopEdit.fxml");
				participantsModel.setAll(participantDAO.getAll());
			}
		});
    	
    	// OKNO EDITACIE workshopu
    	editWorkshopsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				WorkshopEditController editController = new WorkshopEditController();            
				showModalWindow(editController, "WorkshopEdit.fxml");
			}
    	});
    	
    	TableColumn<Participant, Long> idCol = new TableColumn<>("ID");
    	TableColumn<Participant, String> nameCol = new TableColumn<>("Meno");
    	TableColumn<Participant, String> surnameCol = new TableColumn<>("Priezvisko");
    	TableColumn<Participant, String> emailCol = new TableColumn<>("E-mail");
    	
    	idCol.setCellValueFactory(new PropertyValueFactory<>("id")); //PropertyValueFactory vytvori getter id
    	nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    	surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
    	emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    	
    	emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
    	emailCol.setEditable(true);
    	// emailCol.setVisible(false); // na tvrdo urobit neviditelnym
    	
    	// VOLBA VIDITELNYCH STLPCOV - vytvorime model viditelnosti pre kazdy stlpec
    	columnsVisibility.put("ID", idCol.visibleProperty());
    	columnsVisibility.put("Meno", nameCol.visibleProperty());
    	columnsVisibility.put("Priezvisko", surnameCol.visibleProperty());
    	columnsVisibility.put("E-mail", emailCol.visibleProperty());
    	
    	participantsTableView.getColumns().add(idCol);
    	participantsTableView.getColumns().add(nameCol);
    	participantsTableView.getColumns().add(surnameCol);
    	participantsTableView.getColumns().add(emailCol);
    	
    	participantsTableView.setItems(participantsModel);
    	participantsTableView.setEditable(true);
    	
    	// MENU - prehlad moznosti je v Scene Builderi
    	ContextMenu contextMenu = new ContextMenu();
    	for (Entry<String, BooleanProperty> entry : columnsVisibility.entrySet()) {
    		CheckMenuItem menuItem = new CheckMenuItem(entry.getKey()); // zisti, ci je viditelny
    		menuItem.selectedProperty().bindBidirectional(entry.getValue()); // prepojenie odskrtnutia so skutocnou viditelnostou
        	contextMenu.getItems().add(menuItem);
		}	
    	participantsTableView.setContextMenu(contextMenu); // vyberieme, kde ma byt
    	
    	// VOLBA UCASTNIKA
    	participantsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Participant>() {

			@Override
			public void changed(ObservableValue<? extends Participant> observable, Participant oldValue,
					Participant newValue) {
				if (newValue == null) {
					editParticipantButton.setDisable(true);
				} else {
					editParticipantButton.setDisable(false);
				}
				selectedPatricipant.set(newValue);
			}
		});
    }

	private void showModalWindow(Object controller, String fxml) {
		/*ParticipantEditController editController = new ParticipantEditController(selectedPatricipant.get());
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ParticipantEdit.fxml"));
		
		WorkshopEditController editController = new WorkshopEditController(selectedPatricipant.get());*/
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
			
			fxmlLoader.setController(controller);
			Parent rootPane = fxmlLoader.load();
			Scene scene = new Scene(rootPane);
			
			// vytvorit modalne okno - neda sa z neho vratit, kym nie su dokoncene zmeny
			Stage dialog = new Stage();
			dialog.setTitle("Úprava účastníka");
			dialog.setScene(scene);
			dialog.initModality(Modality.APPLICATION_MODAL);
			dialog.showAndWait(); // cokolvek za tymto prikazom, az ked sa zavrie okno
			
			// prekreslit ulozene zmeny
			participantsModel.setAll(participantDAO.getAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}