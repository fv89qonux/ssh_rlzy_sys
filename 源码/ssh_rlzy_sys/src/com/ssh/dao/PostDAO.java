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

import com.ssh.domain.Department;
import com.ssh.domain.Post;

@Service @Transactional
public class PostDAO {

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
    public void AddPost(Post post) throws Exception {
    	Session s = factory.getCurrentSession();
    	s.save(post);
    }

    /*查询Book信息*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Post> QueryPostInfo(int postid,String postname,Department department,int currentPage) { 
    	Session s = factory.getCurrentSession();
    	String hql = "From Post post where 1=1";
    	if(postid!=0) hql = hql + " and post.barcode like '%" + postid + "%'";
    	if(!postname.equals("")) hql = hql + " and post.postname like '%" + postname + "%'";
    	if(null != department && department.getDepartmentid()!=0) hql += " and post.department.departmentid=" + department.getDepartmentid();

    	 Query q = s.createQuery(hql);
    	/*计算当前显示页码的开始记录*/
    	int startIndex = (currentPage-1) * this.PAGE_SIZE;
    	q.setFirstResult(startIndex);
    	q.setMaxResults(this.PAGE_SIZE);
    	List postList = q.list();
    	return (ArrayList<Post>) postList;
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Post> QueryPostInfo(int postid,String postname,Department department) { 
    	Session s = factory.getCurrentSession();
    	String hql = "From Post post where 1=1";
    	if(postid!=0) hql = hql + " and post.postid like '%" + postid + "%'";
    	if(!postname.equals("")) hql = hql + " and post.postname like '%" + postname + "%'";
    	if(null != department && department.getDepartmentid()!=0) hql += " and post.department.departmentid=" + department.getDepartmentid();
    	
    	Query q = s.createQuery(hql);
    	List postList = q.list();
    	return (ArrayList<Post>) postList;
    }

    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public ArrayList<Post> QueryAllPostInfo() {
        Session s = factory.getCurrentSession();
        String hql = "From Post";
        Query q = s.createQuery(hql);
        List postList = q.list();
        return (ArrayList<Post>) postList;
    }

    /*计算总的页数和记录数*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public void CalculateTotalPageAndRecordNumber(int postid,String postname,Department department) {
        Session s = factory.getCurrentSession();
        String hql = "From Post post where 1=1";
        if(postid!=0) hql = hql + " and post.postid like '%" + postid + "%'";
        if(!postname.equals("")) hql = hql + " and post.postname like '%" + postname + "%'";
        if(null != department && department.getDepartmentid()!=0) hql += " and post.department.departmentid=" + department.getDepartmentid();
        
        Query q = s.createQuery(hql);
        List postList = q.list();
        recordNumber = postList.size();
        int mod = recordNumber % this.PAGE_SIZE;
        totalPage = recordNumber / this.PAGE_SIZE;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取对象*/
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public Post GetPostByPostId(int postid) {
        Session s = factory.getCurrentSession();
        Post post = (Post)s.get(Post.class, postid);
        return post;
    }

    /*更新Book信息*/
    public void UpdatePost(Post post) throws Exception {
        Session s = factory.getCurrentSession();
        s.update(post);
    }

    /*删除Book信息*/
    public void DeletePost (int postid) throws Exception {
        Session s = factory.getCurrentSession(); 
        Object post = s.load(Post.class, postid);
        s.delete(post);
    }

}
