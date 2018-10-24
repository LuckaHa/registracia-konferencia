package persistent;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;

import entity.Workshop;

public enum DAOFactory {
	INSTANCE;
	
	private JdbcTemplate jdbcTemplate;
	private ParticipantDAO participantDAO;
	private WorkshopDAO workshopDAO;
	
	public ParticipantDAO getParticipantDAO() {
		// stale tu istu instanciu, preto nie return new ...
		if (participantDAO == null)
			participantDAO = new MemoryParticipantDAO();
		return participantDAO;
	}
	
	public WorkshopDAO getWorkshopDAO() {
		if (workshopDAO == null) {
			workshopDAO = new MysqlWorkshopDAO(getJdbTemplate());
		}
		return workshopDAO;
	}
	
	private JdbcTemplate getJdbTemplate() {
		// tiez bude vytvoreny len pri 1. volani
		if (jdbcTemplate == null) {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUser("registracia-itat");
			dataSource.setPassword("c0mb0c0deitat");
			//dataSource.setDatabaseName("registracia-itat");
			dataSource.setUrl("jdbx:mysql://localhost/registracia-itat?serverTimezone=Europe/Bratislava");
			jdbcTemplate = new JdbcTemplate(dataSource);
		}
		return jdbcTemplate;
	}
}
