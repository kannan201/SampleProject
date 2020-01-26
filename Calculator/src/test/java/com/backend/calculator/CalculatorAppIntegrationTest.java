package com.backend.calculator;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/*
 * 
 * The CalculatorAppIntegrationTest performs the end to end integration testing of the application.
 * This test class contains two sample test cases.
 *   
 * 	@author  Kannan Maniyan
 * 	@version 1.0
 * 	@since   26-01-2020
*/

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CalculatorAppIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	
	@BeforeEach
	public void init() throws ParseException {
		
	}

	@Test
	public void testSimpleCalculator() throws Exception {
		mockMvc.perform(get("/calc/v1/{action}/{a}/{b}", "add", 5,4))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", is(9.0)));
	}
	@Test
	public void testScientificCalculator() throws Exception {
		mockMvc.perform(get("/calc/v1/{action}/{a}", "square",5))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", is(25.0)));
	}

	
}
