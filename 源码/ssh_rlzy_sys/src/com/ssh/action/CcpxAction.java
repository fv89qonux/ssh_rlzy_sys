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

import com.ssh.dao.CcpxDAO;

import com.ssh.domain.Ccpx;
import com.ssh.dao.EmployeeDAO;
import com.ssh.domain.Employee;

@Controller @Scope("prototype")
public class CcpxAction extends ActionSupport {

	public String Ccpxinfo ="-岗位-";
	
	private Integer ccpxid;
	private String  ccpxname;
	private Employee employee;

	public Integer getCcpxid() {
		return ccpxid;
	}
	public void setCcpxid(Integer ccpxid) {
		this.ccpxid = ccpxid;
	}
	public String getCcpxname() {
		return ccpxname;
	}
	public void setCcpxname(String ccpxname) {
		this.ccpxname = ccpxname;
	}
    public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
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
    @Resource CcpxDAO ccpxDAO;

    @Resource EmployeeDAO employeeDAO;
    /*待操作的Book对象*/
    private Ccpx ccpx;
    public Ccpx getCcpx() {
		return ccpx;
	}
	public void setCcpx(Ccpx ccpx) {
		this.ccpx = ccpx;
	}
	
	/*跳转到添加Book视图*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        /*查询所有的BookType信息*/
        List<Employee> employeeList = employeeDAO.QueryAllEmployeeInfo();
        ctx.put("employeeList", employeeList);
        return "add_view";
    }

    /*添加岗位信息*/
    @SuppressWarnings("deprecation")
    public String AddCcpx() {
        ActionContext ctx = ActionContext.getContext();
        try {
            if(true) {
            Employee employee = employeeDAO.GetEmployeeByLoadId(ccpx.getEmployee().getEmployeeid());
            ccpx.setEmployee(employee);;
            }
            ccpxDAO.AddCcpx(ccpx);
            ctx.put("message",  java.net.URLEncoder.encode(Ccpxinfo+"添加成功!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode(Ccpxinfo+"添加失败!"));
            return "error";
        }
    }

    /*查询岗位信息*/
    public String QueryCcpx() {
        if(currentPage == 0) currentPage = 1;
        if(ccpxid == null) ccpxid = 0;
        if(ccpxname == null) ccpxname = "";
        List<Ccpx> ccpxList = ccpxDAO.QueryCcpxInfo(ccpxid, ccpxname, employee);
        /*计算总的页数和总的记录数*/
        ccpxDAO.CalculateTotalPageAndRecordNumber(ccpxid, ccpxname, employee);
        /*获取到总的页码数目*/
        totalPage = ccpxDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = ccpxDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("ccpxList",  ccpxList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("ccpxid", ccpxid);
        ctx.put("ccpxname", ccpxname);
        ctx.put("employee", employee);
        List<Employee> employeeList = employeeDAO.QueryAllEmployeeInfo();
        ctx.put("employeeList", employeeList);
        
        return "query_view";
    }
    
    /*查询岗位信息*/
    public String QueryCcpxpx() {
        if(currentPage == 0) currentPage = 1;
        if(ccpxid == null) ccpxid = 0;
        if(ccpxname == null) ccpxname = "";
        List<Ccpx> ccpxList = ccpxDAO.QueryCcpxInfo(ccpxid, ccpxname, employee);
        /*计算总的页数和总的记录数*/
        ccpxDAO.CalculateTotalPageAndRecordNumber(ccpxid, ccpxname, employee);
        /*获取到总的页码数目*/
        totalPage = ccpxDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = ccpxDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("ccpxList",  ccpxList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("ccpxid", ccpxid);
        ctx.put("ccpxname", ccpxname);
        ctx.put("employee", employee);
        List<Employee> employeeList = employeeDAO.QueryAllEmployeeInfo();
        ctx.put("employeeList", employeeList);
        
        return "query_viewpx";
    }

 
    /*前台查询Book信息*/
    public String FrontQueryCcpx() {
        if(currentPage == 0) currentPage = 1;
        if(ccpxid == null) ccpxid = 0;
        if(ccpxname == null) ccpxname = "";
        List<Ccpx> ccpxList = ccpxDAO.QueryCcpxInfo(ccpxid, ccpxname, employee, currentPage);
        /*计算总的页数和总的记录数*/
        ccpxDAO.CalculateTotalPageAndRecordNumber(ccpxid, ccpxname, employee);
        /*获取到总的页码数目*/
        totalPage = ccpxDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = ccpxDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("ccpxList",  ccpxList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("ccpxid", ccpxid);
        ctx.put("ccpxname", ccpxname);
        ctx.put("employee", employee);
        List<Employee> employeeList = employeeDAO.QueryAllEmployeeInfo();
        ctx.put("employeeList", employeeList);
        return "front_query_view";
    }

    /*查询要修改的Book信息*/
    public String ModifyCcpxQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键barcode获取Book对象*/
        Ccpx ccpx = ccpxDAO.GetCcpxByCcpxId(ccpxid);

        List<Employee> employeeList = employeeDAO.QueryAllEmployeeInfo();
        ctx.put("employeeList", employeeList);
        ctx.put("ccpx",  ccpx);
        return "modify_view";
    }

    /*查询要修改的Book信息*/
    public String FrontShowCcpxQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键barcode获取Book对象*/
        Ccpx ccpx = ccpxDAO.GetCcpxByCcpxId(ccpxid);

        List<Employee> employeeList = employeeDAO.QueryAllEmployeeInfo();
        ctx.put("employeeList", employeeList);
        ctx.put("ccpx",  ccpx);
        return "front_show_view";
    }

    /*更新修改Book信息*/
    public String ModifyCcpx() {
        ActionContext ctx = ActionContext.getContext();
        try {
            if(true) {
            Employee employee = employeeDAO.GetEmployeeByLoadId(ccpx.getEmployee().getEmployeeid());
            ccpx.setEmployee(employee);
            }
       	 	ccpxDAO.UpdateCcpx(ccpx);
            ctx.put("message",  java.net.URLEncoder.encode(Ccpxinfo+"信息更新成功!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode(Ccpxinfo+"信息更新失败!"));
            return "error";
       }
   }

    /*删除Book信息*/
    public String DeleteCcpx() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            ccpxDAO.DeleteCcpx(ccpxid);
            ctx.put("message",  java.net.URLEncoder.encode(Ccpxinfo+"删除成功!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode(Ccpxinfo+"删除失败!"));
            return "error";
        }
    }

}
