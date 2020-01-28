package com.backend.loancalculator.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

import com.backend.loancalculator.model.LoanPlanRequest;
import com.backend.loancalculator.model.LoanPlanResponse;


/*
 * 
 * This class contains all the business logics for the loan payment calculation.
 *   
 * 	@author  Kannan Maniyan
 * 	@version 1.0
 * 	@since   27-01-2020 
*/
@Service
public class LoanCalculatorService {
	private double principalBalance = 0;
	private int daysInYear = 360;
	private int daysInMonth = 30;

	public List<LoanPlanResponse> generatePlan(LoanPlanRequest loanPlanRequest) {
		List<LoanPlanResponse> responseList = new ArrayList<>();
		LoanPlanResponse response;
		LocalDateTime paymentDate = loanPlanRequest.getStartDate();
		principalBalance = loanPlanRequest.getLoanAmount();
		for (int i = 0; i < loanPlanRequest.getDuration(); i++) {

			response = calculateMonthlyPayment(loanPlanRequest.getLoanAmount(), loanPlanRequest.getDuration(),
					loanPlanRequest.getNominalRate());
			response.setDate(paymentDate);
			paymentDate = paymentDate.plusMonths(1);
			responseList.add(response);
		}

		return responseList;
	}

	public LoanPlanResponse calculateMonthlyPayment(double loanAmount, int termInMonths, double interestRate) {
		LoanPlanResponse response = new LoanPlanResponse();
		interestRate /= 100;
		double monthlyInterestRate = interestRate / 12.0;
		double monthlyPayment = (loanAmount * monthlyInterestRate)
				/ (1 - Math.pow(1 + monthlyInterestRate, -termInMonths));
		response.setInitialOutstandingPrincipal(Precision.round(principalBalance, 2));
		double monthlyInterest = (interestRate * daysInMonth * principalBalance) / daysInYear;
		double monthlyPrincipal = (monthlyPayment > principalBalance) ? principalBalance
				: (monthlyPayment - monthlyInterest);
		response.setInterest(Precision.round(monthlyInterest, 2));
		response.setPrincipal(Precision.round(monthlyPrincipal, 2));
		response.setBorrowerPaymentAmount(Precision.round((monthlyInterest + monthlyPrincipal), 2));

		principalBalance -= monthlyPrincipal;

		response.setRemainingOutstandingPrincipal(Precision.round(principalBalance, 2));
		return response;
	}
}
