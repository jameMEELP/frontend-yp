package com.yodsarun.yp.fn.web;

public class NotificationService {
	private long id;
	private String type;
	private String message;

	public NotificationService(long id, String type, String message) {
		this.id = id;
		this.type = type;
		this.message = message;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
