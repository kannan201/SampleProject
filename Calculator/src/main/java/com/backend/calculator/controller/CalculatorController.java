/**
 * 
 */
package com.backend.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.calculator.service.CalculatorService;
import com.backend.calculator.utils.SciOperationEnum;
import com.backend.calculator.utils.SimpleOperationEnum;

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
 * 	@since   268-01-2020 
*/
@RestController
@RequestMapping("/calc/v1")
@Api(value = "Calculator Application")
public class CalculatorController {
	@Autowired
	CalculatorService service;

	@ApiOperation(value = "Simple Calculator", response = double.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved result"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping(value = "/{action}/{a}/{b}")
	public ResponseEntity<Double> calculator(
			@ApiParam(value = "Operation to be performed", required = true) @PathVariable("action") SimpleOperationEnum action,
			@ApiParam(value = "Input variable", required = true) @PathVariable("a") double a,
			@ApiParam(value = "Input variable", required = true) @PathVariable("b") double b) {
		return new ResponseEntity<Double>(service.calculate(action, a, b), HttpStatus.OK);

	}

	@ApiOperation(value = "Scientific Calculator", response = double.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved result"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping(value = "/{action}/{a}")
	public ResponseEntity<Double> sciCalculator(
			@ApiParam(value = "Operation to be performed", required = true) @PathVariable("action") SciOperationEnum action,
			@ApiParam(value = "Input variable", required = true) @PathVariable("a") double a) {
		return new ResponseEntity<Double>(service.calculate(action, a), HttpStatus.OK);

	}
}
