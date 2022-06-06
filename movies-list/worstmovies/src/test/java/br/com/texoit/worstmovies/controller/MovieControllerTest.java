package br.com.texoit.worstmovies.controller;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MovieControllerTest {
	
	private static final String CLEAR_DATA_SCRIPT_PATH = "classpath:test-scripts/clear-data-test.sql";
	private static final String PRIZE_RANGE_URI = "/producers/prize-range";
	
	@Autowired
	private MockMvc mockMvc;	
	
	@Test
	@SqlGroup({
		@Sql(scripts = {"classpath:test-scripts/data-test.sql" }),
		@Sql(scripts = {CLEAR_DATA_SCRIPT_PATH},  executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
	})
	public void listProducers_WithMinAndMaxProducersWinnerTest() throws Exception {		
		URI uri = new URI(PRIZE_RANGE_URI);
		mockMvc
			.perform(MockMvcRequestBuilders.get(uri))
		.andExpect(MockMvcResultMatchers.status().isOk())		
		.andExpect(MockMvcResultMatchers.content().json("{\"min\":[{\"producer\":\"Joel Silver\",\"interval\":1,\"previousWin\":1990,\"followingWin\":1991},{\"producer\":\"Allan Carr\",\"interval\":1,\"previousWin\":1980,\"followingWin\":1981}],\"max\":[{\"producer\":\"Matthew Vaughn\",\"interval\":13,\"previousWin\":2002,\"followingWin\":2015}]}"));
	}
	
	@Test	
	public void listProducers_EmptyListTest() throws Exception {		
		URI uri = new URI(PRIZE_RANGE_URI);
		mockMvc
			.perform(MockMvcRequestBuilders.get(uri))
		.andExpect(MockMvcResultMatchers.status().isOk())		
		.andExpect(MockMvcResultMatchers.content().json("{}"));
	}
	
	
	@Test
	@SqlGroup({
		@Sql({"classpath:test-scripts/one-record-winner.sql" }),
		@Sql(scripts = {CLEAR_DATA_SCRIPT_PATH},  executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
	})
	public void listProducers_WithOneRecordWinnerNoIntervalTest() throws Exception {		
		URI uri = new URI(PRIZE_RANGE_URI);
		mockMvc
			.perform(MockMvcRequestBuilders.get(uri))
		.andExpect(MockMvcResultMatchers.status().isOk())		
		.andExpect(MockMvcResultMatchers.content().json("{\"min\":[],\"max\":[]}"));
	}
	
	@Test
	@SqlGroup({
		@Sql({"classpath:test-scripts/two-records-winner.sql" }),
		@Sql(scripts = {CLEAR_DATA_SCRIPT_PATH},  executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
	})
	public void listProducers_WithTwoRecordsWinnerWithIntervalTest() throws Exception {		
		URI uri = new URI(PRIZE_RANGE_URI);
		mockMvc
			.perform(MockMvcRequestBuilders.get(uri))
		.andExpect(MockMvcResultMatchers.status().isOk())		
		.andExpect(MockMvcResultMatchers.content().json("{\"min\":[{\"producer\":\"Bob Cavallo\",\"interval\":4,\"previousWin\":1986,\"followingWin\":1990}],\"max\":[{\"producer\":\"Bob Cavallo\",\"interval\":4,\"previousWin\":1986,\"followingWin\":1990}]}"));
	}
	
	@Test
	@SqlGroup({
		@Sql({"classpath:test-scripts/nothing-records-winner.sql" }),
		@Sql(scripts = {CLEAR_DATA_SCRIPT_PATH},  executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
	})
	public void listProducers_NothingRecordsWinnerTest() throws Exception {		
		URI uri = new URI(PRIZE_RANGE_URI);
		mockMvc
			.perform(MockMvcRequestBuilders.get(uri))
		.andExpect(MockMvcResultMatchers.status().isOk())		
		.andExpect(MockMvcResultMatchers.content().json("{\"min\":[],\"max\":[]}"));
	}

}
