package com.backend.loancalculator.model;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/*
 * 	@author  Kannan Maniyan
 * 	@version 1.0
 * 	@since   27-01-2020 
*/
@Data
@ApiModel(description="Request object which contains the loan details. ")
public class LoanPlanRequest {
		
		
	@ApiModelProperty(notes = "Total principal amount")
	private double loanAmount;
	
	@ApiModelProperty(notes = "Nominal interest rate")
	private double nominalRate;
	
	@ApiModelProperty(notes = "Total Principal amount")
	private int duration;
	
	@ApiModelProperty(notes = "Total Principal amount")
	private LocalDateTime startDate;
}
