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

import com.ssh.domain.Wages;
import com.ssh.domain.Employee;

@Service @Transactional
public class WagesDAO {

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

    /*添加Wages信息*/
    public void AddWages(Wages wages) throws Exception {
    	Session s = factory.getCurrentSession();
    	s.save(wages);
    }

    /*查询Wages信息*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Wages> QueryWagesInfo(String wagesid,String wagesname,Employee employee,int currentPage) { 
    	Session s = factory.getCurrentSession();
    	String hql = "From Wages wages where 1=1";
    	if(!wagesid.equals("")) hql = hql + " and wages.wagesid like '%" + wagesid + "%'";
    	if(!wagesname.equals("")) hql = hql + " and wages.wagesname like '%" + wagesname + "%'";
    	if(null != employee && employee.getEmployeeid()!=0) hql += " and wages.employee.employeeid=" + employee.getEmployeeid();
    	 Query q = s.createQuery(hql);
    	/*计算当前显示页码的开始记录*/
    	int startIndex = (currentPage-1) * this.PAGE_SIZE;
    	q.setFirstResult(startIndex);
    	q.setMaxResults(this.PAGE_SIZE);
    	List wagesList = q.list();
    	return (ArrayList<Wages>) wagesList;
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Wages> QueryWagesInfo(String wagesid,String wagesName,Employee employee) { 
    	Session s = factory.getCurrentSession();
    	String hql = "From Wages wages where 1=1";
    	if(!wagesid.equals("")) hql = hql + " and wages.wagesid like '%" + wagesid + "%'";
    	if(!wagesName.equals("")) hql = hql + " and wages.wagesName like '%" + wagesName + "%'";
    	if(null != employee && employee.getEmployeeid()!=0) hql += " and wages.employee.employeeid=" + employee.getEmployeeid();
    	Query q = s.createQuery(hql);
    	List wagesList = q.list();
    	return (ArrayList<Wages>) wagesList;
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Wages> QueryAllWagesInfo() {
        Session s = factory.getCurrentSession();
        String hql = "From Wages";
        Query q = s.createQuery(hql);
        List wagesList = q.list();
        return (ArrayList<Wages>) wagesList;
    }

    /*计算总的页数和记录数*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public void CalculateTotalPageAndRecordNumber(String wagesid,String wagesname,Employee employee) {
        Session s = factory.getCurrentSession();
        String hql = "From Wages wages where 1=1";
        if(!wagesid.equals("")) hql = hql + " and wages.wagesid like '%" + wagesid + "%'";
        if(!wagesname.equals("")) hql = hql + " and wages.wagesname like '%" + wagesname + "%'";
        if(null != employee && employee.getEmployeeid()!=0) hql += " and wages.employee.employeeid=" + employee.getEmployeeid();
       Query q = s.createQuery(hql);
        List wagesList = q.list();
        recordNumber = wagesList.size();
        int mod = recordNumber % this.PAGE_SIZE;
        totalPage = recordNumber / this.PAGE_SIZE;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取对象*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public Wages GetWagesByWagesid(String Wagesid) {
        Session s = factory.getCurrentSession();
        Wages wages = (Wages)s.get(Wages.class, Wagesid);
        return wages;
    }

    /*更新Wages信息*/
    public void UpdateWages(Wages wages) throws Exception {
        Session s = factory.getCurrentSession();
        s.update(wages);
    }
    
    public void UpdateWageszf(Wages wages) throws Exception {
        Session s = factory.getCurrentSession();
        s.update(wages);
        String hql = "update Wages wages set wages.zf = :zf where wages.wagesid= :wagesid";
		Query query = s.createQuery(hql);
		query.setString("zf", "已支付");
		query.setString("wagesid", wages.getWagesid());
		query.executeUpdate();
    }

    /*删除Wages信息*/
    public void DeleteWages (String barcode) throws Exception {
        Session s = factory.getCurrentSession(); 
        Object wages = s.load(Wages.class, barcode);
        s.delete(wages);
    }

}
