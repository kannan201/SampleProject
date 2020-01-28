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
@ApiModel(description = "Response object which contains the monthly loan payment details. ")
public class LoanPlanResponse {
	
	@ApiModelProperty(notes = "Total montly payment")
	private double borrowerPaymentAmount;
	
	@ApiModelProperty(notes = "Date of payment")
	private LocalDateTime date;
	
	@ApiModelProperty(notes = "Total Principal before payment")
	private double initialOutstandingPrincipal;
	
	@ApiModelProperty(notes = "Total interest amount")
	private double interest;

	@ApiModelProperty(notes = "Total principal amount")
	private double principal;

	@ApiModelProperty(notes = "Total Principal after payment")
	private double remainingOutstandingPrincipal;
}
