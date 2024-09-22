package com.ssh.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.ssh.dao.ClockDAO;
import com.ssh.dao.EmployeeDAO;
import com.ssh.domain.Clock;
import com.ssh.domain.Employee;
import com.ssh.domain.Wages;
import com.ssh.dao.PostDAO;
import com.ssh.domain.Post;
import com.ssh.dao.DepartmentDAO;
import com.ssh.domain.Department;
@Controller @Scope("prototype")
public class EmployeeAction extends ActionSupport {
	@Resource ClockDAO clockDAO;
	private Clock clock;
	

	public Clock getClock() {
		return clock;
	}
	public void setClock(Clock clock) {
		this.clock = clock;
	}

	private int employeeid;
	
	public int getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}

	private String Employeeinfo="职员";
    /*界面层需要查询的属性: 图书对象*/
    private Post post;
    public void setPost(Post post) {
        this.post = post;
    }
    public Post getPost() {
        return this.post;
    }
   
	/*界面层需要查询的属性: 读者对象*/
    private Department department;
    public void setDepartment(Department department) {
        this.department = department;
    }
    public Department getDepartment() {
        return this.department;
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

    private Wages wages;
    

    public Wages getWages() {
		return wages;
	}
	public void setWages(Wages wages) {
		this.wages = wages;
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
    
    @Resource EmployeeDAO employeeDAO;

    @Resource PostDAO postDAO;
    @Resource DepartmentDAO departmentDAO;
    /*待操作的Employee对象*/
    private Employee employee;
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public Employee getEmployee() {
        return this.employee;
    }
    
    private Employee employeemax;
    

    public Employee getEmployeemax() {
		return employeemax;
	}
	public void setEmployeemax(Employee employeemax) {
		this.employeemax = employeemax;
	}
	/*跳转到添加Employee视图*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        /*查询所有的Post信息*/
        List<Post> postList = postDAO.QueryAllPostInfo();
        ctx.put("postList", postList);
        /*查询所有的Department信息*/
        List<Department> departmentList = departmentDAO.QueryAllDepartmentInfo();
        ctx.put("departmentList", departmentList);
        return "add_view";
    }


    /*添加岗位信息*/
    
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");  
    java.util.Date date=new java.util.Date();  
    String str=sdf.format(date); 
    
    @SuppressWarnings("deprecation")
    public String AddClock() {
        ActionContext ctx = ActionContext.getContext();
        try {
            /*if(true) {
            Employee employee = employeeDAO.GetEmployeeByLoadId(clock.getEmployee().getEmployeeid());
            clock.setEmployee(employee);
            }*/
            clockDAO.AddClock(clock);
            
            ctx.put("message",  java.net.URLEncoder.encode("添加成功!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("添加失败!"));
            return "error";
        }
    }
    
    
    /*添加Employee信息*/
    @SuppressWarnings("deprecation")
    public String AddEmployee() {
        ActionContext ctx = ActionContext.getContext();
        try {
            if(true) {
            Post post = postDAO.GetPostByPostId(employee.getPost().getPostid());
            employee.setPost(post);
            }
            if(true) {
            Department department = departmentDAO.GetDepartmentByDepartmentId(employee.getDepartment().getDepartmentid());
            employee.setDepartment(department);
            }
            employeeDAO.AddEmployee(employee);
            ctx.put("message",  java.net.URLEncoder.encode(Employeeinfo+"添加成功!"));

            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode(Employeeinfo+"添加失败!"));
            return "error";
        }
    }

    /*查询Employee信息*/
    public String QueryEmployee() {
        if(currentPage == 0) currentPage = 1;
        List<Employee> employeeList = employeeDAO.QueryEmployeeInfo(post, department, currentPage);
        /*计算总的页数和总的记录数*/
        employeeDAO.CalculateTotalPageAndRecordNumber(post, department);
        /*获取到总的页码数目*/
        totalPage = employeeDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = employeeDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("employeeList",  employeeList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("post", post);
        List<Post> postList = postDAO.QueryAllPostInfo();
        ctx.put("postList", postList);
        ctx.put("department", department);
        List<Department> departmentList = departmentDAO.QueryAllDepartmentInfo();
        ctx.put("departmentList", departmentList);
        return "query_view";
    }

    /*查询Employee信息*/
    public String zh() {
        if(currentPage == 0) currentPage = 1;
        List<Employee> employeeList = employeeDAO.QueryEmployeeInfo(post, department, currentPage);
        /*计算总的页数和总的记录数*/
        employeeDAO.CalculateTotalPageAndRecordNumber(post, department);
        /*获取到总的页码数目*/
        totalPage = employeeDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = employeeDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("employeeList",  employeeList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("post", post);
        List<Post> postList = postDAO.QueryAllPostInfo();
        ctx.put("postList", postList);
        ctx.put("department", department);
        List<Department> departmentList = departmentDAO.QueryAllDepartmentInfo();
        ctx.put("departmentList", departmentList);
        return "query_zh";
    }
    
    
    
    

    /*前台查询Employee信息*/
    public String FrontQueryEmployee() {
        if(currentPage == 0) currentPage = 1;
        List<Employee> employeeList = employeeDAO.QueryEmployeeInfo(post, department, currentPage);
        /*计算总的页数和总的记录数*/
        employeeDAO.CalculateTotalPageAndRecordNumber(post, department);
        /*获取到总的页码数目*/
        totalPage = employeeDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = employeeDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("employeeList",  employeeList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("post", post);
        List<Post> postList = postDAO.QueryAllPostInfo();
        ctx.put("postList", postList);
        ctx.put("department", department);
        List<Department> departmentList = departmentDAO.QueryAllDepartmentInfo();
        ctx.put("departmentList", departmentList);
        return "front_query_view";
    }

    /*查询要修改的Employee信息*/
    public String ModifyEmployeeQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键loadId获取Employee对象*/
        Employee employee = employeeDAO.GetEmployeeByLoadId(employeeid);

        List<Post> postList = postDAO.QueryAllPostInfo();
        ctx.put("postList", postList);
        List<Department> departmentList = departmentDAO.QueryAllDepartmentInfo();
        ctx.put("departmentList", departmentList);
        ctx.put("employee",  employee);
        return "modify_view";
    }

    /*查询要修改的Employee信息*/
    public String ModifyEmployeeQueryzh() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键loadId获取Employee对象*/
        Employee employee = employeeDAO.GetEmployeeByLoadId(employeeid);

        List<Post> postList = postDAO.QueryAllPostInfo();
        ctx.put("postList", postList);
        List<Department> departmentList = departmentDAO.QueryAllDepartmentInfo();
        ctx.put("departmentList", departmentList);
        ctx.put("employee",  employee);
        return "modify_viewzh";
    }
    
    /*查询要修改的Employee信息*/
    public String FrontShowEmployeeQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键loadId获取Employee对象*/
        Employee employee = employeeDAO.GetEmployeeByLoadId(employeeid);

        List<Post> postList = postDAO.QueryAllPostInfo();
        ctx.put("postList", postList);
        List<Department> departmentList = departmentDAO.QueryAllDepartmentInfo();
        ctx.put("departmentList", departmentList);
        ctx.put("employee",  employee);
        return "front_show_view";
    }

    /*更新修改Employee信息*/
    public String ModifyEmployee() {
        ActionContext ctx = ActionContext.getContext();
        try {
            if(true) {
            Post post = postDAO.GetPostByPostId(employee.getPost().getPostid());
            employee.setPost(post);
            }
            if(true) {
            Department department = departmentDAO.GetDepartmentByDepartmentId(employee.getDepartment().getDepartmentid());
            employee.setDepartment(department);
            }
            employeeDAO.UpdateEmployee(employee);
            ctx.put("message",  java.net.URLEncoder.encode(Employeeinfo+"信息更新成功!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode(Employeeinfo+"信息更新失败!"));
            return "error";
       }
   }
    
    public String ModifyEmployeezh() {
        ActionContext ctx = ActionContext.getContext();
        try {
            employeeDAO.UpdateEmployeezh(employee);
            ctx.put("message",  java.net.URLEncoder.encode(Employeeinfo+"信息更新成功!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode(Employeeinfo+"信息更新失败!"));
            return "error";
       }
   }

    /*删除Employee信息*/
    public String DeleteEmployee() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            employeeDAO.DeleteEmployee(employeeid);
            ctx.put("message",  java.net.URLEncoder.encode(Employeeinfo+"删除成功!"));  
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode(Employeeinfo+"删除失败!"));
            return "error";
        }
    }

}
