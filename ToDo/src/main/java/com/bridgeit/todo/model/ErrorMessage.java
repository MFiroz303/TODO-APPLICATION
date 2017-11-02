package com.bridgeit.todo.model;

public class ErrorMessage {
	
	private String responseMessage;

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	@Override
	public String toString() {
		return "ErrorMessage [responseMessage=" + responseMessage + "]";
	}

}
