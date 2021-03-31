package com.playzone.medicine.exception;

public class MedicineNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private final String code;
	
	public MedicineNotFoundException(String code, String message) {
		super(message);
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	

}
