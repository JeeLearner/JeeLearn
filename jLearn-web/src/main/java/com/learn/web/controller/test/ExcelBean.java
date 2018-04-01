package com.learn.web.controller.test;

public class ExcelBean {
	/*
	 * 设备名称
	 */
	private String deviceName;
	/*
	 * 设备型号
	 */
	private String modelCode;
	/*
	 * 用户名
	 */
	private String username;
	/*
	 * 密码
	 */
	private String password;
	/*
	 * 管理地址
	 */
	private String mngIP;
	/*
	 * 互联地址
	 */
	private String interIP;
	/*
	 * 区域号
	 */
	private String areaID;
	/*
	 * 核心标志位
	 */
	private String coreFlag;
	/*
	 * 下连内网地址
	 */
	private String privateIP;
	
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMngIP() {
		return mngIP;
	}
	public void setMngIP(String mngIP) {
		this.mngIP = mngIP;
	}
	public String getInterIP() {
		return interIP;
	}
	public void setInterIP(String interIP) {
		this.interIP = interIP;
	}
	public String getAreaID() {
		return areaID;
	}
	public void setAreaID(String areaID) {
		this.areaID = areaID;
	}
	public String getCoreFlag() {
		return coreFlag;
	}
	public void setCoreFlag(String coreFlag) {
		this.coreFlag = coreFlag;
	}
	public String getPrivateIP() {
		return privateIP;
	}
	public void setPrivateIP(String privateIP) {
		this.privateIP = privateIP;
	}
	public String getModelCode() {
		return modelCode;
	}
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	
}
