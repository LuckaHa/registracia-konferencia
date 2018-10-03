package sk.upjs.registracia_konferencia;

import java.time.LocalDateTime;

public class Companion {
	
	public static final double[] COMPANION_FEES = new double[] {0, 60, 110, 165, 210};
	public static final double BANKET_FEE = 20;
	public static final double TSHIRT_FEE = 5;
	public static final LocalDateTime BANKET_DATETIME = LocalDateTime.of(2019, 9, 23, 19, 0);
	
	private CompanionCategory category;
	private LocalDateTime start;
	private LocalDateTime end;
	private Tshirt tshirt;
	
	public double getPrice() {
		int nights = start.toLocalDate().until(end.toLocalDate()).getDays();
		double price = COMPANION_FEES[nights];
		if (start.isBefore(BANKET_DATETIME) && end.isAfter(BANKET_DATETIME)) {
			price += BANKET_FEE;
		} 
		if (tshirt != null) {
			price += TSHIRT_FEE;
		}
		return price;
	}
	
	public CompanionCategory getCategory() {
		return category;
	}
	public void setCategory(CompanionCategory category) {
		this.category = category;
	}
	public LocalDateTime getStart() {
		return start;
	}
	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	public LocalDateTime getEnd() {
		return end;
	}
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	public Tshirt getTshirt() {
		return tshirt;
	}
	public void setTshirt(Tshirt tshirt) {
		this.tshirt = tshirt;
	}

}
