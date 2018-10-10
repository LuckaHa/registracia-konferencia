package sk.upjs.registracia_konferencia;

import java.util.ArrayList;
import java.util.List;

/**
 * trieda na vykonavanie CRUD operacii
 *
 */
public class ParticipantDAO {
	
	private List<Participant> participants = new ArrayList<>();
	private long lastId = 0;
	
	public ParticipantDAO() {
		//TODO potom zmazat
		
		Participant p = new Participant();
		p.setName("Andrej");
		p.setSurname("Kiska");
		p.setEmail("andrej@prezident.sk");
		this.add(p);
		
		Participant pa = new Participant();
		pa.setName("Peter");
		pa.setSurname("Gurský");
		pa.setEmail("peter@gursky.sk");
		this.add(pa);
	}

	public void add(Participant participant) {
		participant.setId(++lastId);
		participants.add(participant);
	}
	
	public List<Participant> getAll() {
		return participants;
	}
}
