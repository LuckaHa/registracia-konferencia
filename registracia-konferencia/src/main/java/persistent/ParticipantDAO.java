package persistent;

import java.util.List;

import entity.Participant;

public interface ParticipantDAO {

	void add(Participant participant);

	List<Participant> getAll();
	
	void save(Participant participant);
}