package com.ficticiusclean.fleetsmanagement.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ficticiusclean.fleetsmanagement.model.Brand;
import com.ficticiusclean.fleetsmanagement.model.Model;
import com.ficticiusclean.fleetsmanagement.model.Vehicle;

public class VehicleInsertForm {

	@NotNull
	@NotEmpty
	@Length(min = 2)
	private String name;

	@NotNull
	@NotEmpty
	@Length(min = 2)
	private String model;

	@NotNull
	@NotEmpty
	@Length(min = 2)
	private String brand;

	@JsonFormat(pattern = "dd/MM/yyyy") 
	@DateTimeFormat(pattern = "dd/MM/yyyy")	
	private LocalDate fabricationDate;

	@NotNull	
	@Min(value = 0)
	private Double averageCityConsumption;

	@NotNull
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
