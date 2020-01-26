package com.backend.calculator.utils;

public enum SimpleOperationEnum {
	
	ADD("add"), SUBTRACT ("subtract"), DIVIDE("divide"), MULTIPLY("multiply") ;
	public final String label;
	 
    private SimpleOperationEnum(String label) {
        this.label = label;
    }
	
}
