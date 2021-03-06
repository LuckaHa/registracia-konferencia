package sk.upjs.registracia_konferencia;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sk.upjs.registracia_konferencia.entity.Participant;

/**
 * editovatelny model Participanta - menit sa da email
 *
 */
public class ParticipantFXModel {
	
	private Participant participant;
	private StringProperty name = new SimpleStringProperty();
	private StringProperty surname = new SimpleStringProperty();
	private StringProperty email = new SimpleStringProperty();
	
	public ParticipantFXModel(Participant participant) {
		this.participant = participant;
		setName(participant.getName());
		setSurname(participant.getSurname());
		setEmail(participant.getEmail());
	}
	
	public Participant getParticipant() {
		participant.setName(getName());
		participant.setSurname(getSurname());
		participant.setEmail(getEmail());
		return participant;
	}
	
	// NAME
	public String getName() {
		return name.get();
	}	
	public void setName(String name) {
		this.name.set(name);
	}	
	public StringProperty nameProperty() {
		return this.name;
	}
	
	// SURNAME
	public String getSurname() {
		return surname.get();
	}	
	public void setSurname(String name) {
		this.surname.set(name);
	}	
	public StringProperty surnameProperty() {
		return this.surname;
	}
	
	// MAIL
	public String getEmail() {
		return email.get();
	}	
	public void setEmail(String email) {
		this.email.set(email);
	}	
	public StringProperty emailProperty() {
		return this.email;
	}

	// delegovane metody od Participanta sme zmazali
}
