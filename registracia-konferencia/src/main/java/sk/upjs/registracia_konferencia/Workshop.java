package sk.upjs.registracia_konferencia;

import java.time.LocalDateTime;

public class Workshop {
	
	private String name;
	private LocalDateTime start;
	private LocalDateTime end;
	private double priceFull;
	private double priceStudent;
	private double priceFullLate;
	private double priceStudentLate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public double getPriceFull() {
		return priceFull;
	}
	public void setPriceFull(double priceFull) {
		this.priceFull = priceFull;
	}
	public double getPriceStudent() {
		return priceStudent;
	}
	public void setPriceStudent(double priceStudent) {
		this.priceStudent = priceStudent;
	}
	public double getPriceFullLate() {
		return priceFullLate;
	}
	public void setPriceFullLate(double priceFullLate) {
		this.priceFullLate = priceFullLate;
	}
	public double getPriceStudentLate() {
		return priceStudentLate;
	}
	public void setPriceStudentLate(double priceStudentLate) {
		this.priceStudentLate = priceStudentLate;
	}

}
