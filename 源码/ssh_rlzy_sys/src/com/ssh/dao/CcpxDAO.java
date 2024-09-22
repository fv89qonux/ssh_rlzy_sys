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

import com.ssh.domain.Employee;
import com.ssh.domain.Ccpx;

@Service @Transactional
public class CcpxDAO {

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

    /*添加Book信息*/
    public void AddCcpx(Ccpx ccpx) throws Exception {
    	Session s = factory.getCurrentSession();
    	s.save(ccpx);
    }

    /*查询Book信息*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Ccpx> QueryCcpxInfo(int ccpxid,String ccpxname,Employee employee,int currentPage) { 
    	Session s = factory.getCurrentSession();
    	String hql = "From Ccpx ccpx where 1=1";
    	if(ccpxid!=0) hql = hql + " and ccpx.barcode like '%" + ccpxid + "%'";
    	if(!ccpxname.equals("")) hql = hql + " and ccpx.ccpxname like '%" + ccpxname + "%'";
    	if(null != employee && employee.getEmployeeid()!=0) hql += " and ccpx.employee.employeeid=" + employee.getEmployeeid();

    	 Query q = s.createQuery(hql);
    	/*计算当前显示页码的开始记录*/
    	int startIndex = (currentPage-1) * this.PAGE_SIZE;
    	q.setFirstResult(startIndex);
    	q.setMaxResults(this.PAGE_SIZE);
    	List ccpxList = q.list();
    	return (ArrayList<Ccpx>) ccpxList;
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Ccpx> QueryCcpxInfo(int ccpxid,String ccpxname,Employee employee) { 
    	Session s = factory.getCurrentSession();
    	String hql = "From Ccpx ccpx where 1=1";
    	if(ccpxid!=0) hql = hql + " and ccpx.ccpxid like '%" + ccpxid + "%'";
    	if(!ccpxname.equals("")) hql = hql + " and ccpx.ccpxname like '%" + ccpxname + "%'";
    	if(null != employee && employee.getEmployeeid()!=0) hql += " and ccpx.employee.employeeid=" + employee.getEmployeeid();
    	
    	Query q = s.createQuery(hql);
    	List ccpxList = q.list();
    	return (ArrayList<Ccpx>) ccpxList;
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Ccpx> QueryAllCcpxInfo() {
        Session s = factory.getCurrentSession();
        String hql = "From Ccpx";
        Query q = s.createQuery(hql);
        List ccpxList = q.list();
        return (ArrayList<Ccpx>) ccpxList;
    }

    /*计算总的页数和记录数*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public void CalculateTotalPageAndRecordNumber(int ccpxid,String ccpxname,Employee employee) {
        Session s = factory.getCurrentSession();
        String hql = "From Ccpx ccpx where 1=1";
        if(ccpxid!=0) hql = hql + " and ccpx.ccpxid like '%" + ccpxid + "%'";
        if(!ccpxname.equals("")) hql = hql + " and ccpx.ccpxname like '%" + ccpxname + "%'";
        if(null != employee && employee.getEmployeeid()!=0) hql += " and ccpx.employee.employeeid=" + employee.getEmployeeid();
        
        Query q = s.createQuery(hql);
        List ccpxList = q.list();
        recordNumber = ccpxList.size();
        int mod = recordNumber % this.PAGE_SIZE;
        totalPage = recordNumber / this.PAGE_SIZE;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取对象*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public Ccpx GetCcpxByCcpxId(int ccpxid) {
        Session s = factory.getCurrentSession();
        Ccpx ccpx = (Ccpx)s.get(Ccpx.class, ccpxid);
        return ccpx;
    }

    /*更新Book信息*/
    public void UpdateCcpx(Ccpx ccpx) throws Exception {
        Session s = factory.getCurrentSession();
        s.update(ccpx);
    }

    /*删除Book信息*/
    public void DeleteCcpx (int ccpxid) throws Exception {
        Session s = factory.getCurrentSession(); 
        Object ccpx = s.load(Ccpx.class, ccpxid);
        s.delete(ccpx);
    }

}
