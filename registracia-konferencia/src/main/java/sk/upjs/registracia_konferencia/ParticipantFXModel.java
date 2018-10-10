package sk.upjs.registracia_konferencia;

import java.time.LocalDateTime;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * editovatelny model Participanta - menit sa da email
 *
 */
public class ParticipantFXModel {
	
	private Participant participant;
	private StringProperty email = new SimpleStringProperty();
	
	public ParticipantFXModel(Participant participant) {
		this.participant = participant;
		setEmail(participant.getEmail());
	}
	
	public String getEmail() {
		return email.get();
	}
	
	public void setEmail(String email) {
		this.email.set(email);
		participant.setEmail(email);
	}
	
	public StringProperty emailProperty() {
		return this.email;
	}

	// delegovane metody od Participanta
	public String toString() {
		return participant.toString();
	}

	public Long getId() {
		return participant.getId();
	}

	public void setId(Long id) {
		participant.setId(id);
	}

	public String getName() {
		return participant.getName();
	}

	public void setName(String name) {
		participant.setName(name);
	}

	public String getSurname() {
		return participant.getSurname();
	}

	public void setSurname(String surname) {
		participant.setSurname(surname);
	}

	public String getOrganization() {
		return participant.getOrganization();
	}

	public void setOrganization(String organization) {
		participant.setOrganization(organization);
	}

	public String getAddress() {
		return participant.getAddress();
	}

	public void setAddress(String address) {
		participant.setAddress(address);
	}

	public Long getIco() {
		return participant.getIco();
	}

	public void setIco(Long ico) {
		participant.setIco(ico);
	}

	public String getDic() {
		return participant.getDic();
	}

	public void setDic(String dic) {
		participant.setDic(dic);
	}

	public boolean isEarlyRegistrated() {
		return participant.isEarlyRegistrated();
	}

	public void setEarlyRegistrated(boolean earlyRegistrated) {
		participant.setEarlyRegistrated(earlyRegistrated);
	}

	public Tshirt getTshirt() {
		return participant.getTshirt();
	}

	public void setTshirt(Tshirt tshirt) {
		participant.setTshirt(tshirt);
	}

	public boolean isStudent() {
		return participant.isStudent();
	}

	public void setStudent(boolean isStudent) {
		participant.setStudent(isStudent);
	}

	public boolean isSingleRoom() {
		return participant.isSingleRoom();
	}

	public void setSingleRoom(boolean isSingleRoom) {
		participant.setSingleRoom(isSingleRoom);
	}

	public LocalDateTime getStart() {
		return participant.getStart();
	}

	public void setStart(LocalDateTime start) {
		participant.setStart(start);
	}

	public LocalDateTime getEnd() {
		return participant.getEnd();
	}

	public void setEnd(LocalDateTime end) {
		participant.setEnd(end);
	}

	public List<Companion> getCompanions() {
		return participant.getCompanions();
	}

	public void setCompanions(List<Companion> companions) {
		participant.setCompanions(companions);
	}

	public Workshop getWorkshop() {
		return participant.getWorkshop();
	}

	public void setWorkshop(Workshop workshop) {
		participant.setWorkshop(workshop);
	}

	public boolean isCach() {
		return participant.isCach();
	}

	public void setCach(boolean cach) {
		participant.setCach(cach);
	}
	
}
