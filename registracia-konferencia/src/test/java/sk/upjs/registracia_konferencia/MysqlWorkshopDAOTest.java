package sk.upjs.registracia_konferencia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entity.Workshop;

import java.util.List;

import persistent.DAOFactory;

class MysqlWorkshopDAOTest {

	@Test
	void testGetAll() {
		List<Workshop> workshops = DAOFactory.INSTANCE.getWorkshopDAO().getAll();
		assertNotNull(workshops);
		assertTrue(workshops.size() > 0);
	}

}
