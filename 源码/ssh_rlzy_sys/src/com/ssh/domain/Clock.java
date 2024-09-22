package com.ssh.domain;

import java.util.Date;

public class Clock {
	
	//打开考勤表
	private String clockid;
	private Employee employee;
	private String riqi;
	private String nyr;
	private String sbdate;
	
	private String xbdate;
	private String clockbz;
	
	
	
	public String getNyr() {
		return nyr;
	}
	public void setNyr(String nyr) {
		this.nyr = nyr;
	}
	public String getRiqi() {
		return riqi;
	}
	public void setRiqi(String riqi) {
		this.riqi = riqi;
	}


	public String getClockid() {
		return clockid;
	}
	public void setClockid(String clockid) {
		this.clockid = clockid;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getSbdate() {
		return sbdate;
	}
	public void setSbdate(String sbdate) {
		this.sbdate = sbdate;
	}
	public String getXbdate() {
		return xbdate;
	}
	public void setXbdate(String xbdate) {
		this.xbdate = xbdate;
	}
	public String getClockbz() {
		return clockbz;
	}
	public void setClockbz(String clockbz) {
		this.clockbz = clockbz;
	}
	
}
