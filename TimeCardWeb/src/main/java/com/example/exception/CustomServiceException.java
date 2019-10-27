package com.example.exception;


/**
 * 業務例外クラス
 */
public class CustomServiceException extends Exception{
	private static final long serialVersionUID = 1L;

	public CustomServiceException(String message){
		super(message);
	}
}