package com.ssh.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.ssh.dao.EmloyeeloginDAO;
import com.ssh.dao.EmployeeDAO;
import com.ssh.domain.Employee;

@Controller @Scope("prototype")
public class ChangeEmployeePasswordAction {
	
	private Employee employee;
	
	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	private int employeeid;
	

	public int getEmployeeid() {
		return employeeid;
	}


	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}

	private String username;
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	private String oldPassword;
	private String newPassword;
	private String newPassword2;

	@Resource EmloyeeloginDAO emloyeeloginDAO; 	
	@Resource EmployeeDAO employeeDAO; 	
	
	public String getOldPassword() {
		return oldPassword;
	}


	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}


	public String getNewPassword() {
		return newPassword;
	}


	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


	public String getNewPassword2() {
		return newPassword2;
	}


	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}


	/*修改密码*/
	public String execute() {
		ActionContext ctx = ActionContext.getContext(); 
		if(oldPassword.equals("")) {
			ctx.put("error",  java.net.URLEncoder.encode("旧密码必须输入!"));
			return "error";
		}
		if(newPassword.equals("")) {
			ctx.put("error",  java.net.URLEncoder.encode("新密码必须输入!"));
			return "error";
		}
		if(!newPassword2.equals(newPassword)) {
			ctx.put("error",  java.net.URLEncoder.encode("两次密码输入不一致!"));
			return "error";
		}
		//String username = (String)ctx.getSession().get("username"); 
		Employee emloyee = employeeDAO.GetEmployeeByLoadId(employeeid); 
		if(!emloyee.getPassword().equals(oldPassword)) {
			ctx.put("error",  java.net.URLEncoder.encode("旧密码不正确!"));
			return "error";
		}
		
		try { 
			emloyeeloginDAO.ChangePassword(employeeid,newPassword);
			ctx.put("message",  java.net.URLEncoder.encode("密码修改成功!"));
			return "change_success";
		} catch (Exception e) {
			e.printStackTrace();
			ctx.put("error",  java.net.URLEncoder.encode("密码更新失败!"));
			return "error";
		}  
		
	}

}
