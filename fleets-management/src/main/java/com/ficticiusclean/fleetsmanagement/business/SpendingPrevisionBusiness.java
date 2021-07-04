package com.ficticiusclean.fleetsmanagement.business;

import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.ficticiusclean.fleetsmanagement.controller.dto.SpendingPrevisionDto;
import com.ficticiusclean.fleetsmanagement.controller.form.SpendingPrevisionForm;
import com.ficticiusclean.fleetsmanagement.model.Vehicle;
import com.ficticiusclean.fleetsmanagement.repository.VehicleRepository;
import com.ficticiusclean.fleetsmanagement.utils.FormatUtils;
import com.ficticiusclean.fleetsmanagement.utils.MathOperationUtils;

public class SpendingPrevisionBusiness {

	private VehicleRepository vehicleRepository;

	public SpendingPrevisionBusiness(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}
	
	public List<SpendingPrevisionDto> listAllSpent(SpendingPrevisionForm spendingPrevisionForm) {
		List<Vehicle> listAllVehicles = vehicleRepository.findAll();
		List<SpendingPrevisionDto> list = listAllVehicles.stream()
				.map(SpendingPrevisionDto::new)
				.map(spd -> calculateSpent(spd, spendingPrevisionForm))
				.collect(Collectors.toList());
		Collections.sort(list);
		return list;
	}
	
	private SpendingPrevisionDto calculateSpent(SpendingPrevisionDto spendingPrevisionDto, SpendingPrevisionForm spendingPrevisionForm) {	
		double amountOfFuelCity = 0.0;
		double amountOfFuelHighway = 0.0;
		if (spendingPrevisionDto.getAverageCityConsumption() != null 
				&& spendingPrevisionDto.getAverageCityConsumption() > 0) {
			amountOfFuelCity = MathOperationUtils.divideDouble(spendingPrevisionForm.getCityQuantity(),
					spendingPrevisionDto.getAverageCityConsumption(), 3, RoundingMode.HALF_UP);
		}

		if (spendingPrevisionDto.getAverageHighwayConsumption() != null 
				&& spendingPrevisionDto.getAverageHighwayConsumption() > 0) {
			amountOfFuelHighway = MathOperationUtils.divideDouble(spendingPrevisionForm.getHighwayQuantity(),
					spendingPrevisionDto.getAverageHighwayConsumption(), 3, RoundingMode.HALF_UP);
		}

		double totalFuel = MathOperationUtils.somaDouble(amountOfFuelCity,amountOfFuelHighway,3,RoundingMode.HALF_UP);		
		double totalSpent = MathOperationUtils.multiplDouble(totalFuel,spendingPrevisionForm.getGasPrice(),2, RoundingMode.HALF_UP);		
		
		spendingPrevisionDto.setAmountOfFuel(FormatUtils.formatDecimal(totalFuel,3));
		spendingPrevisionDto.setTotalSpent(FormatUtils.formatCurrency(totalSpent));
		spendingPrevisionDto.setTotalSpentDouble(totalSpent);
		return spendingPrevisionDto;
	}		
	
}
