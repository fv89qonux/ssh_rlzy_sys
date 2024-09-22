package com.ssh.domain;

import java.util.Date;

public class Ccpx {
	//出差培训表
	private Integer ccpxid;
	private String ccpxname;
	private Employee employee;
	private String kssj;
	private String jssj;
	private String sx;
	

	public String getCcpxname() {
		return ccpxname;
	}
	public void setCcpxname(String ccpxname) {
		this.ccpxname = ccpxname;
	}
	public Integer getCcpxid() {
		return ccpxid;
	}
	public void setCcpxid(Integer ccpxid) {
		this.ccpxid = ccpxid;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getSx() {
		return sx;
	}
	public void setSx(String sx) {
		this.sx = sx;
	}
}
