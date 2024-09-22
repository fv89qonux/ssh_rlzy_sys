package com.ssh.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.domain.Post;
import com.ssh.domain.Employee;
import com.ssh.domain.Department;
import com.ssh.domain.Wages;

@Service @Transactional
public class EmployeeDAO {

	@Resource SessionFactory factory;
    /*每页显示记录数目*/
    private final int PAGE_SIZE = 10;

    /*保存查询后总的页数*/
    private int totalPage;
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return totalPage;
    }

    /*保存查询到的总记录数*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*添加Employee信息*/
    public void AddEmployee(Employee employee) throws Exception {
    	Session s = factory.getCurrentSession();
    	s.clear();
    	s.save(employee);
    	
    	
    	
    }

    /*查询Employee信息*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Employee> QueryEmployeeInfo(Post post,Department department,int currentPage) { 
    	Session s = factory.getCurrentSession();
    	String hql = "From Employee employee where 1=1";
    	if(null != post &&post.getPostid()>0) hql += " and employee.post.postid='" + post.getPostid() + "'";
    	if(null != department &&department.getDepartmentid()>0) hql += " and employee.department.departmentid='" + department.getDepartmentid() + "'";
    	 Query q = s.createQuery(hql);
    	/*计算当前显示页码的开始记录*/
    	int startIndex = (currentPage-1) * this.PAGE_SIZE;
    	q.setFirstResult(startIndex);
    	q.setMaxResults(this.PAGE_SIZE);
    	List employeeList = q.list();
    	return (ArrayList<Employee>) employeeList;
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Employee> QueryEmployeeInfo(Post post,Department department) { 
    	Session s = factory.getCurrentSession();
    	String hql = "From Employee employee where 1=1";
    	if(null != post && post.getPostid().equals("")) hql += " and employee.post.postid='" + post.getPostid() + "'";
    	if(null != department &&department.getDepartmentid().equals("")) hql += " and employee.department.departmentid='" + department.getDepartmentid() + "'";
    	Query q = s.createQuery(hql);
    	List employeeList = q.list();
    	return (ArrayList<Employee>) employeeList;
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Employee> QueryAllEmployeeInfo() {
        Session s = factory.getCurrentSession();
        String hql = "From Employee";
        Query q = s.createQuery(hql);
        List employeeList = q.list();
        return (ArrayList<Employee>) employeeList;
    }

    /*计算总的页数和记录数*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public void CalculateTotalPageAndRecordNumber(Post post,Department department) {
        Session s = factory.getCurrentSession();
        String hql = "From Employee employee where 1=1";
        if(null != post &&post.getPostid().equals("")) hql += " and employee.post.postid='" + post.getPostid() + "'";
        if(null != department &&department.getDepartmentid().equals("")) hql += " and employee.department.departmentid='" + department.getDepartmentid() + "'";
        Query q = s.createQuery(hql);
        List employeeList = q.list();
        recordNumber = employeeList.size();
        int mod = recordNumber % this.PAGE_SIZE;
        totalPage = recordNumber / this.PAGE_SIZE;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取对象*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public Employee GetEmployeeByLoadId(int employeeid) {
        Session s = factory.getCurrentSession();
        Employee employee = (Employee)s.get(Employee.class, employeeid);
        return employee;
    }

    /*更新Employee信息*/
    public void UpdateEmployee(Employee employee) throws Exception {
        Session s = factory.getCurrentSession();
        s.update(employee);
    }
    public void UpdateEmployeezh(Employee employee) throws Exception {
        Session s = factory.getCurrentSession();
        //s.update(employee);
        
        String hql = "update Employee employee set employee.zh = :zh where employee.employeeid= :employeeid";
		Query query = s.createQuery(hql);
		query.setString("zh",  employee.getZh());
		query.setInteger("employeeid", employee.getEmployeeid());
    }
    
    public int getid(){
    	 Session s = factory.getCurrentSession();
    	Integer c = (Integer)s.createQuery("select max(a.employeeid) from Employee a " ).uniqueResult();
        return c;
    }

    /*删除Employee信息*/
    public void DeleteEmployee (int employeeid) throws Exception {
        Session s = factory.getCurrentSession(); 
        Object employee = s.load(Employee.class,employeeid);
        s.delete(employee);
    }

}
