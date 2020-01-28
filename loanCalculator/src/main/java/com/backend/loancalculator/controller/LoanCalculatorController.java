package com.backend.loancalculator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.loancalculator.model.LoanPlanRequest;
import com.backend.loancalculator.model.LoanPlanResponse;
import com.backend.loancalculator.service.LoanCalculatorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/*
 * 
 * This class contains all the calculation operations.
 *   
 * 	@author  Kannan Maniyan
 * 	@version 1.0
 * 	@since   27-01-2020 
*/
@RestController
@RequestMapping("/loan/v1")
@Api(value = "Loan Calculator Application")
public class LoanCalculatorController {
	
	@Autowired
	LoanCalculatorService service;
	@ApiOperation(value = "Simple Calculator", response = double.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved result"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "generate-plan", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LoanPlanResponse>> generatePlan(@ApiParam(value = "Input request object", required = true) @RequestBody LoanPlanRequest loanPlanRequest)
	{
		List<LoanPlanResponse> response= service.generatePlan(loanPlanRequest);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
