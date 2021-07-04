package com.ficticiusclean.fleetsmanagement.controller.form;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class SpendingPrevisionForm {
	
	@NotNull
	@DecimalMin("0.1")
	private Double gasPrice;

	@NotNull
	private Double cityQuantity;
	
	@NotNull
	private Double highwayQuantity;

	public Double getGasPrice() {
		return gasPrice;
	}

	public void setGasPrice(Double gasPrice) {
		this.gasPrice = gasPrice;
	}

	public Double getCityQuantity() {
		return cityQuantity;
	}

	public void setCityQuantity(Double cityQuantity) {
		this.cityQuantity = cityQuantity;
	}

	public Double getHighwayQuantity() {
		return highwayQuantity;
	}

	public void setHighwayQuantity(Double highwayQuantity) {
		this.highwayQuantity = highwayQuantity;
	}
	
	
	
}
