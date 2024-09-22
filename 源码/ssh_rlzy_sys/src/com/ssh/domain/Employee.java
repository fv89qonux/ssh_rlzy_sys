package com.ssh.domain;




public class Employee {

	private Integer employeeid;
	private String name;
	private String password;
	private String birth;
	private String rzTime;
	private Department department;
	private Post post;
	private String employeebz;
	private String zh;

	public String getZh() {
		return zh;
	}
	public void setZh(String zh) {
		this.zh = zh;
	}
	public Integer getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(Integer employeeid) {
		this.employeeid = employeeid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getRzTime() {
		return rzTime;
	}
	public void setRzTime(String rzTime) {
		this.rzTime = rzTime;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public String getEmployeebz() {
		return employeebz;
	}
	public void setEmployeebz(String employeebz) {
		this.employeebz = employeebz;
	}
	
}
