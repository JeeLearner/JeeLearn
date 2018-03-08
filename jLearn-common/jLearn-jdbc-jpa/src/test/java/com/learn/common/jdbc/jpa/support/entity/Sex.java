package com.learn.common.jdbc.jpa.support.entity;

public enum Sex {

	male("男"), female("女");
	
	private final String info;

	public String getInfo() {
		return info;
	}

	private Sex(String info) {
		this.info = info;
	}
	
	
}
