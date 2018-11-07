package sk.upjs.registracia_konferencia;

import java.time.LocalDate;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sk.upjs.registracia_konferencia.entity.Workshop;

public class WorkshopFXModel {
	
	private Long id;
	private StringProperty name = new SimpleStringProperty();
	private LocalDate start;
	private LocalDate end;
	private DoubleProperty priceFull = new SimpleDoubleProperty(); 
	private DoubleProperty priceStudent = new SimpleDoubleProperty();
	private DoubleProperty priceFullLate = new SimpleDoubleProperty();
	private DoubleProperty priceStudentLate = new SimpleDoubleProperty();
	
	public void setWorkshop(Workshop w) {
		setId(w.getId());
		setName(w.getName()); // ... vsetky sety
	}
	
	public Workshop getWorkshop() {
		Workshop w = new Workshop();
		w.setId(getId());
		w.setName(getName());
		w.setStart(getStart());
		w.setEnd(getEnd());
		//w.setPriceFull(getPriceFull()); ... vsetky + getPriceFull musi vraciat SimpleDoubleProperty
		return w;
	}
	
	// zober od Gurskeho
	// NAME
	public String getName() {
		return name.get();
	}	
	public void setName(String name) {
		this.name.set(name);
	}	
	public StringProperty nameProperty() {
		return this.name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}

	public DoubleProperty getPriceFull() {
		return priceFull;
	}

	public void setPriceFull(DoubleProperty priceFull) {
		this.priceFull = priceFull;
	}

	public DoubleProperty getPriceStudent() {
		return priceStudent;
	}

	public void setPriceStudent(DoubleProperty priceStudent) {
		this.priceStudent = priceStudent;
	}

	public DoubleProperty getPriceFullLate() {
		return priceFullLate;
	}

	public void setPriceFullLate(DoubleProperty priceFullLate) {
		this.priceFullLate = priceFullLate;
	}

	public DoubleProperty getPriceStudentLate() {
		return priceStudentLate;
	}

	public void setPriceStudentLate(DoubleProperty priceStudentLate) {
		this.priceStudentLate = priceStudentLate;
	}

	
}
