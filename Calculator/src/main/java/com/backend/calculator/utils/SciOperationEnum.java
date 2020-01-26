package com.backend.calculator.utils;

public enum SciOperationEnum {
	
	SQUARE("square"), SQUAREROOT("squareroot"), FACTORIAL("factorial") ;
	public final String label;
	 
    private SciOperationEnum(String label) {
        this.label = label;
    }
	
}
