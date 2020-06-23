package myfirstproject.exchangeservice;

import java.util.Arrays;

public class RestResponse {
	private String messages;
	private Result result;

	public RestResponse() {
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	
}
