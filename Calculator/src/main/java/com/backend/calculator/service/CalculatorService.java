package com.backend.calculator.service;

import org.apache.commons.math3.util.CombinatoricsUtils;
import org.springframework.stereotype.Service;

import com.backend.calculator.utils.SciOperationEnum;
import com.backend.calculator.utils.SimpleOperationEnum;

/*
 * 
 * This class contains all the logics pertaining to Calculator Application.
 *   
 * 	@author  Kannan Maniyan
 * 	@version 1.0
 * 	@since   26-01-2020 
*/

@Service
public class CalculatorService {

	public double calculate(SimpleOperationEnum action, double a, double b) {
		double result = 0;

		switch (action) {
		case ADD:
			result = a + b;
			break;
		case SUBTRACT:
			result = a - b;
			break;
		case DIVIDE:
			result = a / b;
			break;
		case MULTIPLY:
			result = a * b;
			break;
		}
		return result;
	}

	public double calculate(SciOperationEnum action, double a) {
		double result = 0;

		switch (action) {
		case SQUARE:
			result = a * a;
			break;
		case SQUAREROOT:
			result = Math.sqrt(a);
			break;
		case FACTORIAL:
			result = CombinatoricsUtils.factorial((int) a);
			break;
		}
		return result;
	}

}
