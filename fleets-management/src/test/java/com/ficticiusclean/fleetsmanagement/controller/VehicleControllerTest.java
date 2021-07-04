package com.ficticiusclean.fleetsmanagement.controller;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class VehicleControllerTest {

	private static final String CLEAR_DATA_SCRIPT_PATH = "classpath:test-scripts/clear-data-test.sql";
	private static final String VEHICLE_URI = "/vehicle";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@SqlGroup({
		@Sql(scripts = {"classpath:test-scripts/data-test.sql" }),
		@Sql(scripts = {CLEAR_DATA_SCRIPT_PATH},  executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
	})
	public void listAllVehicles_WithRegistersTest() throws Exception {		
		URI uri = new URI(VEHICLE_URI+"/list");
		mockMvc
			.perform(MockMvcRequestBuilders.get(uri))
		.andExpect(MockMvcResultMatchers.status().isOk())		
		.andExpect(MockMvcResultMatchers.content().json("{\"content\":[{\"id\":7,\"name\":\"Veiculo 7\",\"model\":\"Modelo 7\",\"brand\":\"Brand 4\",\"fabricationDate\":\"04/06/2001\",\"averageCityConsumption\":18.6,\"averageHighwayConsumption\":20.3},{\"id\":6,\"name\":\"Veiculo 6\",\"model\":\"Modelo 5\",\"brand\":\"Brand 5\",\"fabricationDate\":\"13/11/2019\",\"averageCityConsumption\":4.5,\"averageHighwayConsumption\":7.1},{\"id\":5,\"name\":\"Veiculo 5\",\"model\":\"Modelo 7\",\"brand\":\"Brand 2\",\"fabricationDate\":\"26/05/2002\",\"averageCityConsumption\":8.5,\"averageHighwayConsumption\":9.1},{\"id\":4,\"name\":\"Veiculo 4\",\"model\":\"Modelo 1\",\"brand\":\"Brand 1\",\"fabricationDate\":\"21/10/2015\",\"averageCityConsumption\":11.2,\"averageHighwayConsumption\":12.3},{\"id\":3,\"name\":\"Veiculo 3\",\"model\":\"Modelo 3\",\"brand\":\"Brand 7\",\"fabricationDate\":null,\"averageCityConsumption\":13.2,\"averageHighwayConsumption\":14.2},{\"id\":2,\"name\":\"Veiculo 2\",\"model\":\"Modelo 4\",\"brand\":\"Brand 1\",\"fabricationDate\":\"01/06/2010\",\"averageCityConsumption\":15.5,\"averageHighwayConsumption\":18.6},{\"id\":1,\"name\":\"Veiculo 1\",\"model\":\"Modelo 2\",\"brand\":\"Brand 5\",\"fabricationDate\":\"16/02/2000\",\"averageCityConsumption\":10.5,\"averageHighwayConsumption\":12.6}],\"pageable\":{\"sort\":{\"sorted\":true,\"unsorted\":false,\"empty\":false},\"offset\":0,\"pageSize\":50,\"pageNumber\":0,\"unpaged\":false,\"paged\":true},\"totalElements\":7,\"totalPages\":1,\"last\":true,\"size\":50,\"number\":0,\"sort\":{\"sorted\":true,\"unsorted\":false,\"empty\":false},\"numberOfElements\":7,\"first\":true,\"empty\":false}"));
	}
	
	@Test
	@SqlGroup({
		@Sql(scripts = {"classpath:test-scripts/data-test.sql" }),
		@Sql(scripts = {CLEAR_DATA_SCRIPT_PATH},  executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
	})
	public void listAllVehicles_WithoutRegistersTest() throws Exception {		
		URI uri = new URI(VEHICLE_URI+"/list");
		mockMvc
			.perform(MockMvcRequestBuilders.get(uri))
		.andExpect(MockMvcResultMatchers.status().isOk())		
		.andExpect(MockMvcResultMatchers.content().json("{}"));
	}
	
	@Test
	@SqlGroup({
		@Sql(scripts = {"classpath:test-scripts/data-test.sql" }),
		@Sql(scripts = {CLEAR_DATA_SCRIPT_PATH},  executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
	})
	public void postNewVehicle_WithAllRequestAttributesTest() throws Exception {		
		URI uri = new URI(VEHICLE_URI);
		mockMvc
			.perform(MockMvcRequestBuilders.post(uri)
					.content("{\"brand\" : \"Marca nova\",\"model\" : \"Modelo 1\",\"name\" : \"Veiculo novo\",\"averageHighwayConsumption\" : \"20.5\",\"averageCityConsumption\" : \"18.1\",\"fabricationDate\" : \"12/12/2000\"}")
					.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isCreated())		
		.andExpect(MockMvcResultMatchers.content().json("{\"id\":8,\"name\":\"Veiculo novo\",\"model\":\"Modelo 1\",\"brand\":\"Marca nova\",\"fabricationDate\":\"12/12/2000\",\"averageCityConsumption\":18.1,\"averageHighwayConsumption\":20.5}"));
	}
	
	@Test
	@SqlGroup({
		@Sql(scripts = {"classpath:test-scripts/data-test.sql" }),
		@Sql(scripts = {CLEAR_DATA_SCRIPT_PATH},  executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
	})
	public void postNewVehicle_WithoutRequestAttrbibutesTest() throws Exception {		
		URI uri = new URI(VEHICLE_URI);
		mockMvc
			.perform(MockMvcRequestBuilders.post(uri)
					.content("{}")
					.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isBadRequest())		
		.andExpect(MockMvcResultMatchers.content().json("[{\"field\":\"name\",\"error\":\"must not be empty\"},{\"field\":\"averageCityConsumption\",\"error\":\"must not be null\"},{\"field\":\"model\",\"error\":\"must not be null\"},{\"field\":\"brand\",\"error\":\"must not be empty\"},{\"field\":\"name\",\"error\":\"must not be null\"},{\"field\":\"model\",\"error\":\"must not be empty\"},{\"field\":\"averageHighwayConsumption\",\"error\":\"must not be null\"},{\"field\":\"brand\",\"error\":\"must not be null\"}]"));
	}
	
	
	@Test
	@SqlGroup({
		@Sql(scripts = {"classpath:test-scripts/data-test.sql" }),
		@Sql(scripts = {CLEAR_DATA_SCRIPT_PATH},  executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
	})
	public void putVehicle_WithAllRequestAttributesTest() throws Exception {		
		URI uri = new URI(VEHICLE_URI+"/1");
		mockMvc
			.perform(MockMvcRequestBuilders.put(uri)
					.content("{\"name\": \"Veiculo alterado\",\"model\": \"Modelo alterado\",\"brand\": \"Marca alterada\",\"fabricationDate\": \"01/01/2021\",\"averageCityConsumption\": 25.3,\"averageHighwayConsumption\": 18.3}")
					.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())		
		.andExpect(MockMvcResultMatchers.content().json("{\"id\":1,\"name\":\"Veiculo alterado\",\"model\":\"Modelo alterado\",\"brand\":\"Marca alterada\",\"fabricationDate\":\"01/01/2021\",\"averageCityConsumption\":25.3,\"averageHighwayConsumption\":18.3}"));
	}
	
	@Test
	@SqlGroup({
		@Sql(scripts = {"classpath:test-scripts/data-test.sql" }),
		@Sql(scripts = {CLEAR_DATA_SCRIPT_PATH},  executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
	})
	public void putVehicle_AlterNameVehicleTest() throws Exception {		
		URI uri = new URI(VEHICLE_URI+"/3");
		mockMvc
			.perform(MockMvcRequestBuilders.put(uri)
					.content("{\"name\": \"Veiculo alterado\"}")
					.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())		
		.andExpect(MockMvcResultMatchers.content().json("{\"id\":3,\"name\":\"Veiculo alterado\",\"model\":\"Modelo 3\",\"brand\":\"Brand 7\",\"fabricationDate\":null,\"averageCityConsumption\":13.2,\"averageHighwayConsumption\":14.2}"));
	}
	
	@Test
	@SqlGroup({
		@Sql(scripts = {"classpath:test-scripts/data-test.sql" }),
		@Sql(scripts = {CLEAR_DATA_SCRIPT_PATH},  executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
	})
	public void putVehicle_WithoutRequestAttrbibutesTest() throws Exception {		
		URI uri = new URI(VEHICLE_URI+"/2");
		mockMvc
			.perform(MockMvcRequestBuilders.put(uri)
					.content("{}")
					.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())		
		.andExpect(MockMvcResultMatchers.content().json("{\"id\":2,\"name\":\"Veiculo 2\",\"model\":\"Modelo 4\",\"brand\":\"Brand 1\",\"fabricationDate\":\"01/06/2010\",\"averageCityConsumption\":15.5,\"averageHighwayConsumption\":18.6}"));
	}
	
	@Test
	@SqlGroup({
		@Sql(scripts = {"classpath:test-scripts/data-test.sql" }),
		@Sql(scripts = {CLEAR_DATA_SCRIPT_PATH},  executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
	})
	public void putVehicle_NotFoundVehicleTest() throws Exception {		
		URI uri = new URI(VEHICLE_URI+"/62");
		mockMvc
			.perform(MockMvcRequestBuilders.put(uri)
					.content("{\"name\": \"Veiculo alterado\",\"model\": \"Modelo alterado\",\"brand\": \"Marca alterada\",\"fabricationDate\": \"01/01/2021\",\"averageCityConsumption\": 25.3,\"averageHighwayConsumption\": 18.3}")
					.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isNotFound());		
	}
	
	@Test
	@SqlGroup({
		@Sql(scripts = {"classpath:test-scripts/data-test.sql" }),
		@Sql(scripts = {CLEAR_DATA_SCRIPT_PATH},  executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
	})
	public void findVehicleByID_ExistVehicleTest() throws Exception {		
		URI uri = new URI(VEHICLE_URI+"/3");
		mockMvc
			.perform(MockMvcRequestBuilders.get(uri))
		.andExpect(MockMvcResultMatchers.status().isOk())		
		.andExpect(MockMvcResultMatchers.content().json("{\"id\":3,\"name\":\"Veiculo 3\",\"model\":\"Modelo 3\",\"brand\":\"Brand 7\",\"fabricationDate\":null,\"averageCityConsumption\":13.2,\"averageHighwayConsumption\":14.2}"));
	}
	
	@Test
	@SqlGroup({
		@Sql(scripts = {"classpath:test-scripts/data-test.sql" }),
		@Sql(scripts = {CLEAR_DATA_SCRIPT_PATH},  executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
	})
	public void findVehicleByID_NotFoundVehicleTest() throws Exception {		
		URI uri = new URI(VEHICLE_URI+"/62");
		mockMvc
			.perform(MockMvcRequestBuilders.get(uri))
		.andExpect(MockMvcResultMatchers.status().isNotFound());	
	}
	
	@Test
	@SqlGroup({
		@Sql(scripts = {"classpath:test-scripts/data-test.sql" }),
		@Sql(scripts = {CLEAR_DATA_SCRIPT_PATH},  executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
	})
	public void deleteVehicleByID_ExistVehicleTest() throws Exception {		
		URI uri = new URI(VEHICLE_URI+"/1");
		mockMvc
			.perform(MockMvcRequestBuilders.delete(uri))
		.andExpect(MockMvcResultMatchers.status().isOk());	
	}
	
	@Test
	@SqlGroup({
		@Sql(scripts = {"classpath:test-scripts/data-test.sql" }),
		@Sql(scripts = {CLEAR_DATA_SCRIPT_PATH},  executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
	})
	public void deleteVehicleByID_NotExistVehicleTest() throws Exception {		
		URI uri = new URI(VEHICLE_URI+"/62");
		mockMvc
			.perform(MockMvcRequestBuilders.delete(uri))
		.andExpect(MockMvcResultMatchers.status().isNotFound());	
	}

}
