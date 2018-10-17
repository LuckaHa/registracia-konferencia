package sk.upjs.registracia_konferencia;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ParticipantEditController {
	
	private ParticipantDAO participantDAO = ParticipantDAOFactory.INSTANCE.getParticipantDAO();
	private Participant participant;
	private ParticipantFXModel participantFXModel;

    @FXML
    private Button saveButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    public ParticipantEditController(Participant participant) {
		this.participant = participant; // chceme ho prepojit s Textom --> model (ten menim, nie povodneho p.)
		participantFXModel = new ParticipantFXModel(participant);
	}

	@FXML
    void initialize() {
		// zmena vybraneho ucastnika 
       nameTextField.textProperty().bindBidirectional(participantFXModel.nameProperty());
       surnameTextField.textProperty().bindBidirectional(participantFXModel.surnameProperty());
       
       // TLACIDLO ULOZIT - po kliknuti ulozit a zavriet okno
       saveButton.setOnAction(new EventHandler<ActionEvent>() {	
		@Override
		public void handle(ActionEvent event) {
			participantDAO.save(participantFXModel.getParticipant());
			saveButton.getScene().getWindow().hide();
		}
	});
    }
}
