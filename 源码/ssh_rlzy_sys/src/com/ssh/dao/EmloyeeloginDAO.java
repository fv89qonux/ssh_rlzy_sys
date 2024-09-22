package com.ssh.dao;

import javax.annotation.Resource;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.domain.Employee;

@Service @Transactional
public class EmloyeeloginDAO {
	@Resource  SessionFactory factory;

	/*保存业务逻辑错误信息字段*/
	private String errMessage;
	public String getErrMessage() { return this.errMessage; }
	
	/*验证用户登录*/
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public boolean CheckLogin(Employee employee) { 
		Session s = factory.getCurrentSession(); 

		Employee db_admin = (Employee)s.get(Employee.class, employee.getEmployeeid());
		if(db_admin == null) { 
			this.errMessage = " 账号不存在 ";
			System.out.print(this.errMessage);
			return false;
		} else if( !db_admin.getPassword().equals(employee.getPassword())) {
			this.errMessage = " 密码不正确! ";
			System.out.print(this.errMessage);
			return false;
		}
		
		return true;
	}
	

	/*修改用户登录密码*/
	public void ChangePassword(int employeeid, String newPassword) {
		Session s = factory.getCurrentSession();
		
		Employee db_employee = (Employee)s.get(Employee.class, employeeid);
		db_employee.setPassword(newPassword);
		s.save(db_employee);
		
	}
	
	/*根据用户名获取管理员对象*/
	public Employee GetEmployee(String name) {
		Session s = factory.getCurrentSession();
		Employee db_employee = null;
		db_employee = (Employee)s.get(Employee.class, name); 
		return db_employee;
	}
	
	
}
