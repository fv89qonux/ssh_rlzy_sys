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
import com.ssh.domain.Clock;

@Service @Transactional
public class ClockDAO {

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
    public void AddClock(Clock clock) throws Exception {
    	Session s = factory.getCurrentSession();
    	s.save(clock);
    }

    /*查询Book信息*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Clock> QueryClockInfo(int clockid,String clockname,Employee employee,int currentPage) { 
    	Session s = factory.getCurrentSession();
    	String hql = "From Clock clock where 1=1";
    	if(clockid!=0) hql = hql + " and clock.barcode like '%" + clockid + "%'";
    	if(!clockname.equals("")) hql = hql + " and clock.clockname like '%" + clockname + "%'";
    	if(null != employee && employee.getEmployeeid()!=0) hql += " and clock.employee.employeeid=" + employee.getEmployeeid();

    	 Query q = s.createQuery(hql);
    	/*计算当前显示页码的开始记录*/
    	int startIndex = (currentPage-1) * this.PAGE_SIZE;
    	q.setFirstResult(startIndex);
    	q.setMaxResults(this.PAGE_SIZE);
    	List clockList = q.list();
    	return (ArrayList<Clock>) clockList;
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Clock> QueryClockInfo(int clockid,String clockname,Employee employee) { 
    	Session s = factory.getCurrentSession();
    	String hql = "From Clock clock where 1=1";
    	if(clockid!=0) hql = hql + " and clock.clockid like '%" + clockid + "%'";
    	if(!clockname.equals("")) hql = hql + " and clock.clockname like '%" + clockname + "%'";
    	if(null != employee && employee.getEmployeeid()!=0) hql += " and clock.employee.employeeid=" + employee.getEmployeeid();
    	
    	Query q = s.createQuery(hql);
    	List clockList = q.list();
    	return (ArrayList<Clock>) clockList;
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Clock> QueryAllClockInfo() {
        Session s = factory.getCurrentSession();
        String hql = "From Clock";
        Query q = s.createQuery(hql);
        List clockList = q.list();
        return (ArrayList<Clock>) clockList;
    }

    /*计算总的页数和记录数*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public void CalculateTotalPageAndRecordNumber(int clockid,String clockname,Employee employee) {
        Session s = factory.getCurrentSession();
        String hql = "From Clock clock where 1=1";
        if(clockid!=0) hql = hql + " and clock.clockid like '%" + clockid + "%'";
        if(!clockname.equals("")) hql = hql + " and clock.clockname like '%" + clockname + "%'";
        if(null != employee && employee.getEmployeeid()!=0) hql += " and clock.employee.employeeid=" + employee.getEmployeeid();
        
        Query q = s.createQuery(hql);
        List clockList = q.list();
        recordNumber = clockList.size();
        int mod = recordNumber % this.PAGE_SIZE;
        totalPage = recordNumber / this.PAGE_SIZE;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取对象*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public Clock GetClockByClockId(int clockid) {
        Session s = factory.getCurrentSession();
        Clock clock = (Clock)s.get(Clock.class, clockid);
        return clock;
    }

    /*更新Book信息*/
    public void UpdateClock(Clock clock) throws Exception {
        Session s = factory.getCurrentSession();
        //s.update(clock);
        
        String hql = "update Clock clock set clock.sbdate = :sbdate where clock.clockid= :clockid";
		Query query = s.createQuery(hql);
		query.setString("sbdate", clock.getSbdate());
		query.setString("clockid", clock.getClockid());
		query.executeUpdate();

    }
    public void UpdateClockq(Clock clock) throws Exception {
        Session s = factory.getCurrentSession();
        //s.update(clock);
        
        String hql = "update Clock clock set clock.clockbz = :sbdate where clock.clockid= :clockid";
		Query query = s.createQuery(hql);
		query.setString("sbdate", clock.getClockbz());
		query.setString("clockid", clock.getClockid());
		query.executeUpdate();

    }
    
    /*更新Book信息*/
    public void UpdateClockxb(Clock clock) throws Exception {
        Session s = factory.getCurrentSession();
        //s.update(clock);
        
        String hql = "update Clock clock set clock.xbdate = :xbdate where clock.clockid= :clockid";
		Query query = s.createQuery(hql);
		query.setString("xbdate", clock.getXbdate());
		query.setString("clockid", clock.getClockid());
		query.executeUpdate();

    }

    /*删除Book信息*/
    public void DeleteClock (int clockid) throws Exception {
        Session s = factory.getCurrentSession(); 
        Object clock = s.load(Clock.class, clockid);
        s.delete(clock);
    }
    
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Clock> QueryAllClockInfojs(int employeeid,String clockid) {
        Session s = factory.getCurrentSession();
        String hql = "From Clock clock where clock.sbdate is not null and clock.xbdate is not null and clock.riqi=:clockid and clock.employee.employeeid=:employeeid";
        
        Query q = s.createQuery(hql);
        q.setString("clockid", clockid);
        q.setInteger("employeeid", employeeid);
        List clockList = q.list();
        return (ArrayList<Clock>) clockList;
    }

}
