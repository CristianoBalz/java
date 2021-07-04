package com.ficticiusclean.fleetsmanagement.business;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ficticiusclean.fleetsmanagement.controller.dto.VehicleDto;
import com.ficticiusclean.fleetsmanagement.controller.form.VehicleInsertForm;
import com.ficticiusclean.fleetsmanagement.controller.form.VehicleUpdateForm;
import com.ficticiusclean.fleetsmanagement.model.Brand;
import com.ficticiusclean.fleetsmanagement.model.Model;
import com.ficticiusclean.fleetsmanagement.model.Vehicle;
import com.ficticiusclean.fleetsmanagement.repository.BrandRepository;
import com.ficticiusclean.fleetsmanagement.repository.ModelRepository;
import com.ficticiusclean.fleetsmanagement.repository.VehicleRepository;

public class VehicleBusiness {

	private VehicleRepository vehicleRepository;

	public VehicleBusiness(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}	
	
	public Vehicle save(VehicleInsertForm vehicleForm, ModelRepository modelRepos, BrandRepository brandRepos) {		
		Model model = createModel(modelRepos, vehicleForm.getModel());
		Brand brand = createBrand(brandRepos, vehicleForm.getBrand());
		
		Vehicle vehicle = vehicleForm.converter(model, brand);
		vehicleRepository.save(vehicle);
		
		return vehicle;
	}	
	
	public Vehicle update(Long id, VehicleUpdateForm vehicleForm, ModelRepository modelRepos, BrandRepository brandRepos) {
		Optional<Vehicle> vehicleOpt = vehicleRepository.findById(id);
		if (vehicleOpt.isPresent()) {
			Vehicle vehicle = vehicleRepository.getById(id);
			
			updateVehicleAttributes(vehicle, vehicleForm);
			
			String modelStr = vehicleForm.getModel();			
			if(modelStr != null && !modelStr.trim().isEmpty()) {
				Model model = updateModel(modelRepos, modelStr);
				vehicle.setModel(model);
			}
			
			String brandStr = vehicleForm.getBrand();			
			if(brandStr != null && !brandStr.trim().isEmpty()) {
				Brand brand = updateBrand(brandRepos, brandStr);
				vehicle.setBrand(brand);
			}
			
			return vehicle;
		}
		return null;
	}	
	
	public Vehicle findById(Long id) {
		Optional<Vehicle> vehicleOpt = vehicleRepository.findById(id);
		if (vehicleOpt.isPresent()) {
			return vehicleOpt.get();
		}
		return null;
	}
	
	public Page<VehicleDto> listAll(Pageable pagination) {
		Page<Vehicle> vehicles = vehicleRepository.findAll(pagination);
		return VehicleDto.converter(vehicles);
	}
	
	public boolean delete(Long id) {
		Optional<Vehicle> optional = vehicleRepository.findById(id);
		if (optional.isPresent()) {
			vehicleRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	private void updateVehicleAttributes(Vehicle vehicle, VehicleUpdateForm vehicleForm) {
		if(vehicleForm.getAverageCityConsumption() != null) {
			vehicle.setAverageCityConsumption(vehicleForm.getAverageCityConsumption());
		}
		
		if(vehicleForm.getAverageHighwayConsumption() != null) {
			vehicle.setAverageHighwayConsumption(vehicleForm.getAverageHighwayConsumption());
		}
		
		if(vehicleForm.getName() != null 
				&& !vehicleForm.getName().trim().isEmpty()) {
			vehicle.setName(vehicleForm.getName());
		}
		
		if(vehicleForm.getFabricationDate() != null) {
			vehicle.setFabricationDate(vehicleForm.getFabricationDate());
		}
	}		
	
	private Model createModel(ModelRepository modelRepos, String model) {		
		if(model == null || model.trim().isEmpty()) {
			return null;
		}
		return modelRepos.findByModel(model).orElse(new Model(model));
	}
	
	private Brand createBrand(BrandRepository brandRepos, String brand) {		
		if(brand == null || brand.trim().isEmpty()) {
			return null;
		}
		return brandRepos.findByBrand(brand).orElse(new Brand(brand));
	}	
	
	private Model updateModel(ModelRepository modelRepos, String modelStr) {
		Optional<Model> modelOpt = modelRepos.findByModel(modelStr);
		Model model;
		if(modelOpt.isPresent()) {
			model = modelOpt.get();
		} else {
			model = new Model(modelStr);
			model = modelRepos.save(model);
		}
		return model;
	}
	
	private Brand updateBrand(BrandRepository brandRepos, String brandStr) {
		Optional<Brand> brandOpt = brandRepos.findByBrand(brandStr);
		Brand brand;
		if(brandOpt.isPresent()) {
			brand = brandOpt.get();
		} else {
			brand = new Brand(brandStr);
			brand = brandRepos.save(brand);
		}
		return brand;
	}
}
