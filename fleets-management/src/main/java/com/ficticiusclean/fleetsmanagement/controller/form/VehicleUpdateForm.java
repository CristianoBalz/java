package com.ficticiusclean.fleetsmanagement.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ficticiusclean.fleetsmanagement.model.Brand;
import com.ficticiusclean.fleetsmanagement.model.Model;
import com.ficticiusclean.fleetsmanagement.model.Vehicle;

public class VehicleUpdateForm {
	
	@Length(min = 2)
	private String name;
	
	@Length(min = 2)
	private String model;

	@Length(min = 2)
	private String brand;

	@JsonFormat(pattern = "dd/MM/yyyy") 
	@DateTimeFormat(pattern = "dd/MM/yyyy")	
	private LocalDate fabricationDate;

	@Min(value = 0)
	private Double averageCityConsumption;

	@Min(value = 0)
	private Double averageHighwayConsumption;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
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

	public Vehicle converter(Model model, Brand brand) {
		return new Vehicle(name, fabricationDate, averageCityConsumption, averageHighwayConsumption, model, brand);
	}
}
