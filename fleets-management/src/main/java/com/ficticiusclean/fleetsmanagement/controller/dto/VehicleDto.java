package com.ficticiusclean.fleetsmanagement.controller.dto;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ficticiusclean.fleetsmanagement.model.Vehicle;

public class VehicleDto {	
	
	private Long id;
	
	private String name;	
	
	private String model;
	
	private String brand;
	
	@JsonFormat(pattern = "dd/MM/yyyy") 
	@DateTimeFormat(pattern = "dd/MM/yyyy")	
	private LocalDate fabricationDate;
	
	private Double averageCityConsumption;
	
	private Double averageHighwayConsumption;

	public VehicleDto(Vehicle vehicle) {
		if(vehicle == null) {
			return;
		}
		this.id = vehicle.getId();
		this.name = vehicle.getName();
		this.model = vehicle.getModel() == null ? "" : vehicle.getModel().getModel();
		this.brand = vehicle.getBrand() == null ? "" : vehicle.getBrand().getBrand();
		this.fabricationDate = vehicle.getFabricationDate();
		this.averageCityConsumption = vehicle.getAverageCityConsumption();
		this.averageHighwayConsumption = vehicle.getAverageHighwayConsumption();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getModel() {
		return model;
	}

	public String getBrand() {
		return brand;
	}

	public LocalDate getFabricationDate() {
		return fabricationDate;
	}

	public Double getAverageCityConsumption() {
		return averageCityConsumption;
	}

	public Double getAverageHighwayConsumption() {
		return averageHighwayConsumption;
	}	
	
	public static Page<VehicleDto> converter(Page<Vehicle> vehicles) {
		return vehicles.map(VehicleDto::new);
	}
	
}
