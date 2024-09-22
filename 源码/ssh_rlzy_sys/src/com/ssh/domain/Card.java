package com.ssh.domain;

public class Card {
	//岗位表
	private Integer Cardid;
	private Employee  employee;
	private String ykh;
	private String xkh;
	private String   zf;
	public Integer getCardid() {
		return Cardid;
	}
	public void setCardid(Integer cardid) {
		Cardid = cardid;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getYkh() {
		return ykh;
	}
	public void setYkh(String ykh) {
		this.ykh = ykh;
	}
	public String getXkh() {
		return xkh;
	}
	public void setXkh(String xkh) {
		this.xkh = xkh;
	}
	public String getZf() {
		return zf;
	}
	public void setZf(String zf) {
		this.zf = zf;
	}
	
}
