package sk.upjs.registracia_konferencia.persistent;

import java.util.ArrayList;
import java.util.List;

import sk.upjs.registracia_konferencia.entity.Participant;

/**
 * trieda na vykonavanie CRUD operacii
 *
 */
public class MemoryParticipantDAO implements ParticipantDAO {
	
	private List<Participant> participants = new ArrayList<>();
	private long lastId = 0;
	
	public MemoryParticipantDAO() {
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

	@Override
	public void add(Participant participant) {
		participant.setId(++lastId);
		participants.add(participant);
	}
	
	@Override
	public List<Participant> getAll() {
		return participants;
	}

	@Override
	public void save(Participant participant) {
		if (participant != null) {
			if (participant.getId() != null) { // novy
				add(participant);
			} else {
				for (int i = 0; i < participants.size(); i++) {
					if (participants.get(i).getId().equals(participant.getId())) {
						participants.set(i, participant);
						break;
					}
				}
			}
		}
	}
}
