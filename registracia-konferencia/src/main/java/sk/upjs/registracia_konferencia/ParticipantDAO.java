package sk.upjs.registracia_konferencia;

import java.util.List;

public interface ParticipantDAO {

	void add(Participant participant);

	List<Participant> getAll();
	
	void save(Participant participant);
}