package sk.upjs.registracia_konferencia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import sk.upjs.registracia_konferencia.entity.Companion;
import sk.upjs.registracia_konferencia.entity.CompanionCategory;
import sk.upjs.registracia_konferencia.entity.Participant;
import sk.upjs.registracia_konferencia.entity.Workshop;

class ParticipantTest {
	
	Workshop cidm;
	Workshop slonlp;
	
	public ParticipantTest() {
		cidm = new Workshop();
		cidm.setPriceFull(355);
		cidm.setPriceStudent(295);
		cidm.setPriceFullLate(380);
		cidm.setPriceStudentLate(380);
		slonlp = new Workshop();
		slonlp.setPriceFull(245);
		slonlp.setPriceStudent(195);
		slonlp.setPriceFullLate(265);
		slonlp.setPriceStudentLate(210);
	}

	@Test
	void testFinalPrice1() {
		Participant petra = new Participant();
		petra.setEarlyRegistrated(true);
		petra.setWorkshop(cidm);
		petra.setStudent(false);
		petra.setSingleRoom(false);
		petra.setCach(false);
		assertEquals(355, petra.finalPrice());
	}
	
	@Test
	void testFinalPrice2() {
		Participant petra = new Participant();
		petra.setEarlyRegistrated(false);
		petra.setWorkshop(cidm);
		petra.setStudent(false);
		petra.setSingleRoom(false);
		petra.setCach(false);
		assertEquals(380, petra.finalPrice());
	}

	@Test
	void testFinalPrice3() {
		Participant adam = new Participant();
		adam.setEarlyRegistrated(true);
		adam.setWorkshop(slonlp);
		adam.setStudent(false);
		adam.setSingleRoom(false);
		adam.setCach(false);
		System.out.println(adam.finalPrice());
		
		List<Companion> companions = new ArrayList<>();
		Companion c1 = new Companion();
		c1.setCategory(CompanionCategory.ADULT);
		c1.setStart(LocalDateTime.of(2019, 9, 21, 17, 0));
		c1.setEnd(LocalDateTime.of(2019, 9, 23, 10, 0));
		companions.add(c1);
		adam.setCompanions(companions);
		System.out.println(adam.finalPrice());
		
		assertEquals(245 + 110, adam.finalPrice());
	}
}
