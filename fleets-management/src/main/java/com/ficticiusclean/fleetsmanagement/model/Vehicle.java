package com.ficticiusclean.fleetsmanagement.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vehicle {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;	
	private LocalDate fabricationDate;
	@Column(name = "averageCityConsumption")	private Double averageCityConsumption;
	@Column(name = "averageHighwayConsumption") private Double averageHighwayConsumption;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Model model;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Brand brand;
	
	public Vehicle() {}
	
	public Vehicle(String name, LocalDate fabricationDate, Double averageCityConsumption,
			Double averageHighwayConsumption, Model model, Brand brand) {
		
		this.name = name;
		this.fabricationDate = fabricationDate;
		this.averageCityConsumption = averageCityConsumption;
		this.averageHighwayConsumption = averageHighwayConsumption;
		this.model = model;
		this.brand = brand;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getFabricationDate() {
		return fabricationDate;
	}

	public void setFabricationDate(LocalDate fabricationDate) {
		this.fabricationDate = fabricationDate;
	}

	public Double getAverageCityConsumption() {
		return averageCityConsumption;
	}

	public void setAverageCityConsumption(Double averageCityConsumption) {
		this.averageCityConsumption = averageCityConsumption;
	}

	public Double getAverageHighwayConsumption() {
		return averageHighwayConsumption;
	}

	public void setAverageHighwayConsumption(Double averageHighwayConsumption) {
		this.averageHighwayConsumption = averageHighwayConsumption;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", name=" + name + ", fabricationDate=" + fabricationDate
				+ ", averageCityConsumption=" + averageCityConsumption + ", averageHighwayConsumption="
				+ averageHighwayConsumption + ", model=" + model + ", brand=" + brand + "]";
	}	
	
}
