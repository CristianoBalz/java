package com.ficticiusclean.fleetsmanagement.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ficticiusclean.fleetsmanagement.business.VehicleBusiness;
import com.ficticiusclean.fleetsmanagement.controller.dto.VehicleDto;
import com.ficticiusclean.fleetsmanagement.controller.form.VehicleInsertForm;
import com.ficticiusclean.fleetsmanagement.controller.form.VehicleUpdateForm;
import com.ficticiusclean.fleetsmanagement.model.Vehicle;
import com.ficticiusclean.fleetsmanagement.repository.BrandRepository;
import com.ficticiusclean.fleetsmanagement.repository.ModelRepository;
import com.ficticiusclean.fleetsmanagement.repository.VehicleRepository;

@RestController
@RequestMapping("vehicle")
public class VehicleController {
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private ModelRepository modelRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@GetMapping("list")
	public Page<VehicleDto> listAll(@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 50) Pageable pagination) {
		return getBusiness().listAll(pagination);
	}	
	
	@PostMapping
	@Transactional
	public ResponseEntity<VehicleDto> save(@RequestBody @Valid VehicleInsertForm vehicleForm, UriComponentsBuilder uriBuilder) {		
		Vehicle vehicle = getBusiness().save(vehicleForm, modelRepository, brandRepository);

		URI uri = uriBuilder.path("{id}").buildAndExpand(vehicle.getId()).toUri();
		return ResponseEntity.created(uri).body(new VehicleDto(vehicle));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VehicleDto> findById(@PathVariable("id") Long id) {
		Vehicle vehicle = getBusiness().findById(id);		
		if (vehicle != null) {
			return ResponseEntity.ok(new VehicleDto(vehicle));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<VehicleDto> update(@PathVariable Long id, @RequestBody @Valid VehicleUpdateForm form) {
		Vehicle vehicle = getBusiness().update(id, form, modelRepository, brandRepository);		
		if (vehicle != null) {			
			return ResponseEntity.ok(new VehicleDto(vehicle));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if(getBusiness().delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	private VehicleBusiness getBusiness() {
		return new VehicleBusiness(vehicleRepository);
	}
}
