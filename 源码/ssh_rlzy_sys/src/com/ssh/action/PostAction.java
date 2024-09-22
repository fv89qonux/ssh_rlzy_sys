package com.ssh.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.utils.ExportExcelUtil;
import com.ssh.dao.PostDAO;
import com.ssh.domain.Post;
import com.ssh.dao.DepartmentDAO;
import com.ssh.domain.Department;

@Controller @Scope("prototype")
public class PostAction extends ActionSupport {

	public String Postinfo ="-岗位-";
	
	private Integer postid;
	private String  postname;
	private Department department;

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
    public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}



	/*当前第几页*/
    private int currentPage;
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getCurrentPage() {
        return currentPage;
    }

    /*一共多少页*/
    private int totalPage;
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return totalPage;
    }

    /*当前查询的总记录数目*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*业务层对象*/
    @Resource PostDAO postDAO;

    @Resource DepartmentDAO departmentDAO;
    /*待操作的Book对象*/
    private Post post;
    public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	
	/*跳转到添加Book视图*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        /*查询所有的BookType信息*/
        List<Department> departmentList = departmentDAO.QueryAllDepartmentInfo();
        ctx.put("departmentList", departmentList);
        return "add_view";
    }

    /*添加岗位信息*/
    @SuppressWarnings("deprecation")
    public String AddPost() {
        ActionContext ctx = ActionContext.getContext();
        try {
            postDAO.AddPost(post);
            ctx.put("message",  java.net.URLEncoder.encode(Postinfo+"添加成功!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode(Postinfo+"添加失败!"));
            return "error";
        }
    }

    /*查询岗位信息*/
    public String QueryPost() {
        if(currentPage == 0) currentPage = 1;
        if(postid == null) postid = 0;
        if(postname == null) postname = "";
        List<Post> postList = postDAO.QueryPostInfo(postid, postname, department);
        /*计算总的页数和总的记录数*/
        postDAO.CalculateTotalPageAndRecordNumber(postid, postname, department);
        /*获取到总的页码数目*/
        totalPage = postDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = postDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("postList",  postList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("postid", postid);
        ctx.put("postname", postname);
        ctx.put("department", department);
        List<Department> departmentList = departmentDAO.QueryAllDepartmentInfo();
        ctx.put("departmentList", departmentList);
        
        return "query_view";
    }

 
    /*前台查询Book信息*/
    public String FrontQueryPost() {
        if(currentPage == 0) currentPage = 1;
        if(postid == null) postid = 0;
        if(postname == null) postname = "";
        List<Post> postList = postDAO.QueryPostInfo(postid, postname, department, currentPage);
        /*计算总的页数和总的记录数*/
        postDAO.CalculateTotalPageAndRecordNumber(postid, postname, department);
        /*获取到总的页码数目*/
        totalPage = postDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = postDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("postList",  postList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("postid", postid);
        ctx.put("postname", postname);
        ctx.put("department", department);
        List<Department> departmentList = departmentDAO.QueryAllDepartmentInfo();
        ctx.put("departmentList", departmentList);
        return "front_query_view";
    }

    /*查询要修改的Book信息*/
    public String ModifyPostQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键barcode获取Book对象*/
        Post post = postDAO.GetPostByPostId(postid);

        List<Department> departmentList = departmentDAO.QueryAllDepartmentInfo();
        ctx.put("departmentList", departmentList);
        ctx.put("post",  post);
        return "modify_view";
    }

    /*查询要修改的Book信息*/
    public String FrontShowPostQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键barcode获取Book对象*/
        Post post = postDAO.GetPostByPostId(postid);

        List<Department> departmentList = departmentDAO.QueryAllDepartmentInfo();
        ctx.put("departmentList", departmentList);
        ctx.put("post",  post);
        return "front_show_view";
    }

    /*更新修改Book信息*/
    public String ModifyPost() {
        ActionContext ctx = ActionContext.getContext();
        try {
       	 	postDAO.UpdatePost(post);
            ctx.put("message",  java.net.URLEncoder.encode(Postinfo+"信息更新成功!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode(Postinfo+"信息更新失败!"));
            return "error";
       }
   }

    /*删除Book信息*/
    public String DeletePost() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            postDAO.DeletePost(postid);
            ctx.put("message",  java.net.URLEncoder.encode(Postinfo+"删除成功!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode(Postinfo+"删除失败!"));
            return "error";
        }
    }

}
