package sk.upjs.registracia_konferencia.persistent;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import sk.upjs.registracia_konferencia.entity.Workshop;
import sk.upjs.registracia_konferencia.persistent.DAOFactory;

class MysqlWorkshopDAOTest {
	
	// testy by mali za sebou mazat vytvorene entity... pomale riesenie
	// alebo 2 databazy, v 1 zive data, v 2. testovacie data... v DAOFactory getWorkshopDAOTest, getJdbcTemplateTest + nova databaza, ku ktorej ma ten isty pouzivatel prava
	// v projekte by sme to mali robit tym 2. sposobom

	@Test
	void testGetAll() {
		List<Workshop> workshops = DAOFactory.INSTANCE.getWorkshopDAO().getAll();
		assertNotNull(workshops);
		assertTrue(workshops.size() > 0);
	}
	
	@Test
	void testSave() {
		Workshop cidm = new Workshop();
		cidm.setName("CIDM");
		cidm.setStart(LocalDate.of(2018,  10, 5));
		cidm.setEnd(LocalDate.of(2018, 11, 5));
		cidm.setPriceFull(355);
		cidm.setPriceFullLate(380);
		cidm.setPriceStudent(295);
		cidm.setPriceStudentLate(320);
		// CREATE
		DAOFactory.INSTANCE.getWorkshopDAO().save(cidm);
		assertNotNull(cidm.getId());
		// UPDATE
		cidm.setName("CIDM_novy");
		DAOFactory.INSTANCE.getWorkshopDAO().save(cidm);
		List<Workshop> all = DAOFactory.INSTANCE.getWorkshopDAO().getAll();
		for (Workshop w : all) {
			if (w.getId() == cidm.getId()) {
				assertEquals("CIDM_novy", w.getName());
				DAOFactory.INSTANCE.getWorkshopDAO().delete(w.getId());
				return;
			}
		}
		assertTrue(false, "update sa nepodaril"); // nenasiel taky w, ze w.getId == cidm.getId
	}
	
	@Test
	void testDelete() {
		Workshop cidm = new Workshop();
		cidm.setName("CIDM");
		cidm.setStart(LocalDate.of(2018,  10, 5));
		cidm.setEnd(LocalDate.of(2018, 11, 5));
		cidm.setPriceFull(355);
		cidm.setPriceFullLate(380);
		cidm.setPriceStudent(295);
		cidm.setPriceStudentLate(320);
		DAOFactory.INSTANCE.getWorkshopDAO().save(cidm);
		Long id = cidm.getId();
		DAOFactory.INSTANCE.getWorkshopDAO().delete(id);
		List<Workshop> all = DAOFactory.INSTANCE.getWorkshopDAO().getAll();
		for (Workshop w : all) {
			assertNotEquals(id, w.getId());
		}
	}

}
