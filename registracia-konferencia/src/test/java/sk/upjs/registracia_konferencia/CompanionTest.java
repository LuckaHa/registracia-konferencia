package sk.upjs.registracia_konferencia;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import sk.upjs.registracia_konferencia.entity.Companion;
import sk.upjs.registracia_konferencia.entity.CompanionCategory;
import sk.upjs.registracia_konferencia.entity.Tshirt;

class CompanionTest {

	@Test
	void testGetPrice() {
		Companion c = new Companion();
		c.setCategory(CompanionCategory.ADULT);
		c.setStart(LocalDateTime.of(2019, 9, 21, 17, 0));
		c.setEnd(LocalDateTime.of(2019, 9, 23, 10, 0));
		c.setTshirt(Tshirt.M);
		assertEquals(115, c.getPrice());
	}

}
