package com.ssh.domain;

public class Post {
	//岗位表
	private Integer postid;
	private String  postname;
	//部门外键
/*	private Department department;*/



	public Integer getPostid() {
		return postid;
	}
	public void setPostid(Integer postid) {
		this.postid = postid;
	}
	public String getPostname() {
		return postname;
	}
	public void setPostname(String postname) {
		this.postname = postname;
	}
/*	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}*/


}
