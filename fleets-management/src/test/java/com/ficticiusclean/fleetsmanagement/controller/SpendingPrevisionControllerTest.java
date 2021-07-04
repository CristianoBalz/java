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
class SpendingPrevisionControllerTest {

	private static final String CLEAR_DATA_SCRIPT_PATH = "classpath:test-scripts/clear-data-test.sql";
	private static final String SPENDING_PREVISION_URI = "/spending-prevision";
	
	@Autowired
	private MockMvc mockMvc;	
	
	@Test
	@SqlGroup({
		@Sql(scripts = {"classpath:test-scripts/data-test.sql" }),
		@Sql(scripts = {CLEAR_DATA_SCRIPT_PATH},  executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
	})
	public void getAllSpendingPrevision_WithVehiclesTest() throws Exception {		
		URI uri = new URI(SPENDING_PREVISION_URI);
		mockMvc
			.perform(MockMvcRequestBuilders.post(uri)
					.content("{\"gasPrice\" : 10.5,\"highwayQuantity\": 323.5,\"cityQuantity\" : 125.3}")
					.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())		
		.andExpect(MockMvcResultMatchers.content().json("[{\"name\":\"Veiculo 6\",\"brand\":\"Brand 5\",\"model\":\"Modelo 5\",\"year\":2019,\"amountOfFuel\":\"73,41\",\"totalSpent\":\"R$ 770,78\"},{\"name\":\"Veiculo 5\",\"brand\":\"Brand 2\",\"model\":\"Modelo 7\",\"year\":2002,\"amountOfFuel\":\"50,29\",\"totalSpent\":\"R$ 528,05\"},{\"name\":\"Veiculo 1\",\"brand\":\"Brand 5\",\"model\":\"Modelo 2\",\"year\":2000,\"amountOfFuel\":\"37,61\",\"totalSpent\":\"R$ 394,88\"},{\"name\":\"Veiculo 4\",\"brand\":\"Brand 1\",\"model\":\"Modelo 1\",\"year\":2015,\"amountOfFuel\":\"37,49\",\"totalSpent\":\"R$ 393,63\"},{\"name\":\"Veiculo 3\",\"brand\":\"Brand 7\",\"model\":\"Modelo 3\",\"year\":null,\"amountOfFuel\":\"32,27\",\"totalSpent\":\"R$ 338,88\"},{\"name\":\"Veiculo 2\",\"brand\":\"Brand 1\",\"model\":\"Modelo 4\",\"year\":2010,\"amountOfFuel\":\"25,48\",\"totalSpent\":\"R$ 267,50\"},{\"name\":\"Veiculo 7\",\"brand\":\"Brand 4\",\"model\":\"Modelo 7\",\"year\":2001,\"amountOfFuel\":\"22,67\",\"totalSpent\":\"R$ 238,06\"}]"));
	}
	
	@Test
	@SqlGroup({	
		@Sql(scripts = {CLEAR_DATA_SCRIPT_PATH},  executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
	})
	public void getAllSpendingPrevision_WithoutVehiclesTest() throws Exception {		
		URI uri = new URI(SPENDING_PREVISION_URI);
		mockMvc
			.perform(MockMvcRequestBuilders.post(uri)
					.content("{\"gasPrice\" : 10.5,\"highwayQuantity\": 323.5,\"cityQuantity\" : 125.3}")
					.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())		
		.andExpect(MockMvcResultMatchers.content().json("[]"));
	}
	
	@Test
	@SqlGroup({	
		@Sql(scripts = {"classpath:test-scripts/data-test.sql" }),
		@Sql(scripts = {CLEAR_DATA_SCRIPT_PATH},  executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
	})
	public void getAllSpendingPrevision_RequestBodyEmptyTest() throws Exception {		
		URI uri = new URI(SPENDING_PREVISION_URI);
		mockMvc
			.perform(MockMvcRequestBuilders.post(uri)
					.content("{}")
					.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isBadRequest())		
		.andExpect(MockMvcResultMatchers.content().json("[{\"field\":\"cityQuantity\",\"error\":\"must not be null\"},{\"field\":\"gasPrice\",\"error\":\"must not be null\"},{\"field\":\"highwayQuantity\",\"error\":\"must not be null\"}]"));
	}

}
