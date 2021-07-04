package com.ficticiusclean.fleetsmanagement.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ficticiusclean.fleetsmanagement.model.Vehicle;

public class SpendingPrevisionDto implements Comparable<SpendingPrevisionDto> {

	private String name;
	private String brand;
	private String model;
	private Integer year;
	
	@JsonIgnore
	private Double averageCityConsumption;
	
	@JsonIgnore
	private Double averageHighwayConsumption;
	
	@JsonIgnore
	private Double totalSpentDouble;
	
	private String amountOfFuel;
	private String totalSpent;

	public SpendingPrevisionDto(Vehicle vehicle) {
		this.name = vehicle.getName();
		this.brand = vehicle.getBrand() != null ? vehicle.getBrand().getBrand() : "";
		this.model = vehicle.getModel() != null ? vehicle.getModel().getModel() : "";
		this.year = vehicle.getFabricationDate() != null ? vehicle.getFabricationDate().getYear() : null;
		this.averageCityConsumption = vehicle.getAverageCityConsumption();
		this.averageHighwayConsumption = vehicle.getAverageHighwayConsumption();
		this.amountOfFuel = "";
		this.totalSpent = "";
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getAmountOfFuel() {
		return amountOfFuel;
	}

	public void setAmountOfFuel(String amountOfFuel) {
		this.amountOfFuel = amountOfFuel;
	}

	public String getTotalSpent() {
		return totalSpent;
	}

	public void setTotalSpent(String totalSpent) {
		this.totalSpent = totalSpent;
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
		
	public Double getTotalSpentDouble() {
		return totalSpentDouble;
	}
	
	public void setTotalSpentDouble(Double totalSpentDouble) {
		this.totalSpentDouble = totalSpentDouble;
	}

	@Override
	public String toString() {
		return "SpendingPrevisionDto [name=" + name + ", brand=" + brand + ", model=" + model + ", year=" + year
				+ ", amountOfFuel=" + amountOfFuel + ", totalSpent=" + totalSpent + "]";
	}

	@Override
	public int compareTo(SpendingPrevisionDto o) {
		return o.getTotalSpentDouble().compareTo(this.getTotalSpentDouble());
	}
	
}
