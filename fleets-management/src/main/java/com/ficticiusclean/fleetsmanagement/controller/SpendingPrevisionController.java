package com.ficticiusclean.fleetsmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ficticiusclean.fleetsmanagement.business.SpendingPrevisionBusiness;
import com.ficticiusclean.fleetsmanagement.controller.dto.SpendingPrevisionDto;
import com.ficticiusclean.fleetsmanagement.controller.form.SpendingPrevisionForm;
import com.ficticiusclean.fleetsmanagement.repository.VehicleRepository;

@RestController
@RequestMapping("spending-prevision")
public class SpendingPrevisionController {
	
	@Autowired
	private VehicleRepository vehicleRepository;

	@PostMapping
	public List<SpendingPrevisionDto> calculateSpent(@RequestBody @Valid SpendingPrevisionForm spendingPrevisionForm) {
		return getBusiness().listAllSpent(spendingPrevisionForm);
	}
	
	private SpendingPrevisionBusiness getBusiness() {
		return new SpendingPrevisionBusiness(vehicleRepository);
	}
	
}
