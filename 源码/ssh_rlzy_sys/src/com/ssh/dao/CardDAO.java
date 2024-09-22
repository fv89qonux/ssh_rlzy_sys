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
import com.ssh.domain.Card;

@Service @Transactional
public class CardDAO {

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
    public void AddCard(Card card) throws Exception {
    	Session s = factory.getCurrentSession();
    	s.save(card);
    }

    /*查询Book信息*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Card> QueryCardInfo(int cardid,String cardname,Employee employee,int currentPage) { 
    	Session s = factory.getCurrentSession();
    	String hql = "From Card card where 1=1";
    	if(cardid!=0) hql = hql + " and card.barcode like '%" + cardid + "%'";
    	if(!cardname.equals("")) hql = hql + " and card.cardname like '%" + cardname + "%'";
    	if(null != employee && employee.getEmployeeid()!=0) hql += " and card.employee.employeeid=" + employee.getEmployeeid();

    	 Query q = s.createQuery(hql);
    	/*计算当前显示页码的开始记录*/
    	int startIndex = (currentPage-1) * this.PAGE_SIZE;
    	q.setFirstResult(startIndex);
    	q.setMaxResults(this.PAGE_SIZE);
    	List cardList = q.list();
    	return (ArrayList<Card>) cardList;
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Card> QueryCardInfo(int cardid,String cardname,Employee employee) { 
    	Session s = factory.getCurrentSession();
    	String hql = "From Card card where 1=1";
    	if(cardid!=0) hql = hql + " and card.cardid like '%" + cardid + "%'";
    	if(!cardname.equals("")) hql = hql + " and card.cardname like '%" + cardname + "%'";
    	if(null != employee && employee.getEmployeeid()!=0) hql += " and card.employee.employeeid=" + employee.getEmployeeid();
    	
    	Query q = s.createQuery(hql);
    	List cardList = q.list();
    	return (ArrayList<Card>) cardList;
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Card> QueryAllCardInfo() {
        Session s = factory.getCurrentSession();
        String hql = "From Card";
        Query q = s.createQuery(hql);
        List cardList = q.list();
        return (ArrayList<Card>) cardList;
    }

    /*计算总的页数和记录数*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public void CalculateTotalPageAndRecordNumber(int cardid,String cardname,Employee employee) {
        Session s = factory.getCurrentSession();
        String hql = "From Card card where 1=1";
        if(cardid!=0) hql = hql + " and card.cardid like '%" + cardid + "%'";
        if(!cardname.equals("")) hql = hql + " and card.cardname like '%" + cardname + "%'";
        if(null != employee && employee.getEmployeeid()!=0) hql += " and card.employee.employeeid=" + employee.getEmployeeid();
        
        Query q = s.createQuery(hql);
        List cardList = q.list();
        recordNumber = cardList.size();
        int mod = recordNumber % this.PAGE_SIZE;
        totalPage = recordNumber / this.PAGE_SIZE;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取对象*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public Card GetCardByCardId(int cardid) {
        Session s = factory.getCurrentSession();
        Card card = (Card)s.get(Card.class, cardid);
        return card;
    }

    /*更新Book信息*/
    public void UpdateCard(Card card) throws Exception {
        Session s = factory.getCurrentSession();
        s.update(card);
    }

    /*删除Book信息*/
    public void DeleteCard (int cardid) throws Exception {
        Session s = factory.getCurrentSession(); 
        Object card = s.load(Card.class, cardid);
        s.delete(card);
    }

}
