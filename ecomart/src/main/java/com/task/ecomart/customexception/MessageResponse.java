package com.task.ecomart.customexception;

import java.time.LocalDateTime;

public class MessageResponse {
	
	private LocalDateTime timestamp;
	private String status;
	private String msg ;
	
	public MessageResponse() {
		// TODO Auto-generated constructor stub
	}

	public MessageResponse(LocalDateTime timestamp, String status, String msg) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.msg = msg;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	

}
