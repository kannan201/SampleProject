package com.backend.loancalculator;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.backend.loancalculator.model.LoanPlanRequest;
import com.backend.loancalculator.model.LoanPlanResponse;
import com.backend.loancalculator.service.LoanCalculatorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * 
 * The LoanCalculatorIntegrationTest performs the end to end integration testing of the application.
 * This test class contains one sample test cases.
 *   
 * 	@author  Kannan Maniyan
 * 	@version 1.0
 * 	@since   27-01-2020
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class LoanCalculatorIntegrationTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LoanCalculatorService loanCalculatorService;

	@BeforeEach
	public void init() throws ParseException, JsonMappingException, JsonProcessingException {
		when(loanCalculatorService.generatePlan(Mockito.any(LoanPlanRequest.class))).thenReturn(generateResponse());
	}

	@Test
	public void testGeneratePlan() throws Exception {
		mockMvc.perform(post("/loan/v1/generate-plan").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n" + "	\"loanAmount\": \"5000\",\r\n" + "\"nominalRate\": \"5.0\",\r\n"
						+ "\"duration\": 24,\r\n" + "\"startDate\": \"2018-01-01T00:00:01Z\"\r\n" + "}"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].borrowerPaymentAmount", is(219.36)))
				.andExpect(jsonPath("$.[0].initialOutstandingPrincipal", is(5000)))
				.andExpect(jsonPath("$.[0].interest", is(20.83)))
				.andExpect(jsonPath("$.[0].remainingOutstandingPrincipal", is(4801.48)))
				.andExpect(jsonPath("$.[0].principal", is(198.52)));
		verify(loanCalculatorService, times(1)).generatePlan(Mockito.any(LoanPlanRequest.class));
	}

	private List<LoanPlanResponse> generateResponse() throws JsonMappingException, JsonProcessingException {
		String responseString = "[\r\n" + "    {\r\n" + "        \"borrowerPaymentAmount\": 219.36,\r\n"
				+ "        \"date\": \"2018-01-01T00:00:01\",\r\n"
				+ "        \"initialOutstandingPrincipal\": 5000,\r\n" + "        \"interest\": 20.83,\r\n"
				+ "        \"principal\": 198.52,\r\n" + "        \"remainingOutstandingPrincipal\": 4801.48 \r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"borrowerPaymentAmount\": 219.36,\r\n"
				+ "        \"date\": \"2018-02-01T00:00:01\",\r\n"
				+ "        \"initialOutstandingPrincipal\": 4801.48,\r\n" + "        \"interest\": 20.01,\r\n"
				+ "        \"principal\": 199.35,\r\n" + "        \"remainingOutstandingPrincipal\": 4602.13 \r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"borrowerPaymentAmount\": 219.36,\r\n"
				+ "        \"date\": \"2018-03-01T00:00:01\",\r\n"
				+ "        \"initialOutstandingPrincipal\": 4602.13,\r\n" + "        \"interest\": 19.18,\r\n"
				+ "        \"principal\": 200.18,\r\n" + "        \"remainingOutstandingPrincipal\": 4401.94 \r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"borrowerPaymentAmount\": 219.36,\r\n"
				+ "        \"date\": \"2018-04-01T00:00:01\",\r\n"
				+ "        \"initialOutstandingPrincipal\": 4401.94,\r\n" + "        \"interest\": 18.34,\r\n"
				+ "        \"principal\": 201.02,\r\n" + "        \"remainingOutstandingPrincipal\": 4200.93 \r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"borrowerPaymentAmount\": 219.36,\r\n"
				+ "        \"date\": \"2018-05-01T00:00:01\",\r\n"
				+ "        \"initialOutstandingPrincipal\": 4200.93,\r\n" + "        \"interest\": 17.5,\r\n"
				+ "        \"principal\": 201.85,\r\n" + "        \"remainingOutstandingPrincipal\": 3999.08 \r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"borrowerPaymentAmount\": 219.36,\r\n"
				+ "        \"date\": \"2018-06-01T00:00:01\",\r\n"
				+ "        \"initialOutstandingPrincipal\": 3999.08,\r\n" + "        \"interest\": 16.66,\r\n"
				+ "        \"principal\": 202.69,\r\n" + "        \"remainingOutstandingPrincipal\": 3796.38 \r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"borrowerPaymentAmount\": 219.36,\r\n"
				+ "        \"date\": \"2018-07-01T00:00:01\",\r\n"
				+ "        \"initialOutstandingPrincipal\": 3796.38,\r\n" + "        \"interest\": 15.82,\r\n"
				+ "        \"principal\": 203.54,\r\n" + "        \"remainingOutstandingPrincipal\": 3592.84 \r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"borrowerPaymentAmount\": 219.36,\r\n"
				+ "        \"date\": \"2018-08-01T00:00:01\",\r\n"
				+ "        \"initialOutstandingPrincipal\": 3592.84,\r\n" + "        \"interest\": 14.97,\r\n"
				+ "        \"principal\": 204.39,\r\n" + "        \"remainingOutstandingPrincipal\": 3388.46 \r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"borrowerPaymentAmount\": 219.36,\r\n"
				+ "        \"date\": \"2018-09-01T00:00:01\",\r\n"
				+ "        \"initialOutstandingPrincipal\": 3388.46,\r\n" + "        \"interest\": 14.12,\r\n"
				+ "        \"principal\": 205.24,\r\n" + "        \"remainingOutstandingPrincipal\": 3183.22 \r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"borrowerPaymentAmount\": 219.36,\r\n"
				+ "        \"date\": \"2018-10-01T00:00:01\",\r\n"
				+ "        \"initialOutstandingPrincipal\": 3183.22,\r\n" + "        \"interest\": 13.26,\r\n"
				+ "        \"principal\": 206.09,\r\n" + "        \"remainingOutstandingPrincipal\": 2977.12 \r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"borrowerPaymentAmount\": 219.36,\r\n"
				+ "        \"date\": \"2018-11-01T00:00:01\",\r\n"
				+ "        \"initialOutstandingPrincipal\": 2977.12,\r\n" + "        \"interest\": 12.4,\r\n"
				+ "        \"principal\": 206.95,\r\n" + "        \"remainingOutstandingPrincipal\": 2770.17 \r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"borrowerPaymentAmount\": 219.36,\r\n"
				+ "        \"date\": \"2018-12-01T00:00:01\",\r\n"
				+ "        \"initialOutstandingPrincipal\": 2770.17,\r\n" + "        \"interest\": 11.54,\r\n"
				+ "        \"principal\": 207.81,\r\n" + "        \"remainingOutstandingPrincipal\": 2562.36 \r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"borrowerPaymentAmount\": 219.36,\r\n"
				+ "        \"date\": \"2019-01-01T00:00:01\",\r\n"
				+ "        \"initialOutstandingPrincipal\": 2562.36,\r\n" + "        \"interest\": 10.68,\r\n"
				+ "        \"principal\": 208.68,\r\n" + "        \"remainingOutstandingPrincipal\": 2353.68 \r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"borrowerPaymentAmount\": 219.36,\r\n"
				+ "        \"date\": \"2019-02-01T00:00:01\",\r\n"
				+ "        \"initialOutstandingPrincipal\": 2353.68,\r\n" + "        \"interest\": 9.81,\r\n"
				+ "        \"principal\": 209.55,\r\n" + "        \"remainingOutstandingPrincipal\": 2144.13 \r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"borrowerPaymentAmount\": 219.36,\r\n"
				+ "        \"date\": \"2019-03-01T00:00:01\",\r\n"
				+ "        \"initialOutstandingPrincipal\": 2144.13,\r\n" + "        \"interest\": 8.93,\r\n"
				+ "        \"principal\": 210.42,\r\n" + "        \"remainingOutstandingPrincipal\": 1933.7 \r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"borrowerPaymentAmount\": 219.36,\r\n"
				+ "        \"date\": \"2019-04-01T00:00:01\",\r\n"
				+ "        \"initialOutstandingPrincipal\": 1933.7,\r\n" + "        \"interest\": 8.06,\r\n"
				+ "        \"principal\": 211.3,\r\n" + "        \"remainingOutstandingPrincipal\": 1722.4 \r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"borrowerPaymentAmount\": 219.36,\r\n"
				+ "        \"date\": \"2019-05-01T00:00:01\",\r\n"
				+ "        \"initialOutstandingPrincipal\": 1722.4,\r\n" + "        \"interest\": 7.18,\r\n"
				+ "        \"principal\": 212.18,\r\n" + "        \"remainingOutstandingPrincipal\": 1510.22 \r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"borrowerPaymentAmount\": 219.36,\r\n"
				+ "        \"date\": \"2019-06-01T00:00:01\",\r\n"
				+ "        \"initialOutstandingPrincipal\": 1510.22,\r\n" + "        \"interest\": 6.29,\r\n"
				+ "        \"principal\": 213.06,\r\n" + "        \"remainingOutstandingPrincipal\": 1297.16 \r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"borrowerPaymentAmount\": 219.36,\r\n"
				+ "        \"date\": \"2019-07-01T00:00:01\",\r\n"
				+ "        \"initialOutstandingPrincipal\": 1297.16,\r\n" + "        \"interest\": 5.4,\r\n"
				+ "        \"principal\": 213.95,\r\n" + "        \"remainingOutstandingPrincipal\": 1083.21 \r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"borrowerPaymentAmount\": 219.36,\r\n"
				+ "        \"date\": \"2019-08-01T00:00:01\",\r\n"
				+ "        \"initialOutstandingPrincipal\": 1083.21,\r\n" + "        \"interest\": 4.51,\r\n"
				+ "        \"principal\": 214.84,\r\n" + "        \"remainingOutstandingPrincipal\": 868.36\r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"borrowerPaymentAmount\": 219.36,\r\n"
				+ "        \"date\": \"2019-09-01T00:00:01\",\r\n"
				+ "        \"initialOutstandingPrincipal\": 868.36,\r\n" + "        \"interest\": 3.62,\r\n"
				+ "        \"principal\": 215.74,\r\n" + "        \"remainingOutstandingPrincipal\": 652.62\r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"borrowerPaymentAmount\": 219.36,\r\n"
				+ "        \"date\": \"2019-10-01T00:00:01\",\r\n"
				+ "        \"initialOutstandingPrincipal\": 652.62,\r\n" + "        \"interest\": 2.72,\r\n"
				+ "        \"principal\": 216.64,\r\n" + "        \"remainingOutstandingPrincipal\": 435.99 \r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"borrowerPaymentAmount\": 219.36,\r\n"
				+ "        \"date\": \"2019-11-01T00:00:01\",\r\n"
				+ "        \"initialOutstandingPrincipal\": 435.99,\r\n" + "        \"interest\": 1.82,\r\n"
				+ "        \"principal\": 217.54,\r\n" + "        \"remainingOutstandingPrincipal\": 218.45 \r\n"
				+ "    },\r\n" + "    {\r\n" + "        \"borrowerPaymentAmount\": 219.36,\r\n"
				+ "        \"date\": \"2019-12-01T00:00:01\",\r\n"
				+ "        \"initialOutstandingPrincipal\": 218.45,\r\n" + "        \"interest\": 0.91,\r\n"
				+ "        \"principal\": 218.45,\r\n" + "        \"remainingOutstandingPrincipal\": 0 \r\n"
				+ "    }\r\n" + "]";
		return new ObjectMapper().readValue(responseString, ArrayList.class);

	}
}
