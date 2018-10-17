package sk.upjs.registracia_konferencia;

public enum ParticipantDAOFactory {
	INSTANCE;
	
	ParticipantDAO participantDAO;
	
	public ParticipantDAO getParticipantDAO() {
		// stale tu istu instanciu, preto nie return new ...
		if (participantDAO == null)
			participantDAO = new MemoryParticipantDAO();
		return participantDAO;
	}
}
