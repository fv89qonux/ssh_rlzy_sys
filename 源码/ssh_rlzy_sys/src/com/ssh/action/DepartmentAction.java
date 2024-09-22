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
import com.ssh.dao.DepartmentDAO;
import com.ssh.domain.Department;
@Controller @Scope("prototype")
public class DepartmentAction extends ActionSupport {
	
	public String Departmeninfo ="-部门-";

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

    private int DepartmentId;

	public int getDepartmentId() {
		return DepartmentId;
	}
	public void setDepartmentId(int departmentId) {
		DepartmentId = departmentId;
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
    @Resource DepartmentDAO departmentDAO;

    /*待操作的BookType对象    部门*/
    private Department department;
    public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	/*跳转到添加BookType视图  部门*/
    public String AddView() {
        //ActionContext ctx = ActionContext.getContext();
        return "add_view";
    }

    /*添加BookType信息*/
    @SuppressWarnings("deprecation")
    public String AddDepartment() {
        ActionContext ctx = ActionContext.getContext();
        try {
        	departmentDAO.AddDepartment(department);
            ctx.put("message",  java.net.URLEncoder.encode(Departmeninfo+"添加成功!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode(Departmeninfo+"添加失败!"));
            return "error";
        }
    }

    /*查询BookType信息 部门*/
    public String QueryDepartment() {
        if(currentPage == 0) currentPage = 1;
        List<Department> departmentlist = departmentDAO.QueryDepartmentInfo(currentPage);
        /*计算总的页数和总的记录数*/
        departmentDAO.CalculateTotalPageAndRecordNumber();
        /*获取到总的页码数目*/
        totalPage = departmentDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = departmentDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("departmentlist",  departmentlist);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        return "query_view";
    }

    /*后台导出到excel*/
    public String QueryDepartmentOutputToExcel() { 
        List<Department> departmentlist = departmentDAO.QueryDepartmentInfo();
        ExportExcelUtil ex = new ExportExcelUtil();
        String title = "BookType信息记录"; 
        String[] headers = { "部门编码","部门名称"};
        List<String[]> dataset = new ArrayList<String[]>(); 
        for(int i=0;i<departmentlist.size();i++) {
        	Department department = departmentlist.get(i); 
        	dataset.add(new String[]{department.getDepartmentid() + "",department.getDepartmentname() + ""});
        }
        /*
        OutputStream out = null;
		try {
			out = new FileOutputStream("C://output.xls");
			ex.exportExcel(title,headers, dataset, out);
		    out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		HttpServletResponse response = null;//创建一个HttpServletResponse对象 
		OutputStream out = null;//创建一个输出流对象 
		try { 
			response = ServletActionContext.getResponse();//初始化HttpServletResponse对象 
			out = response.getOutputStream();//
			response.setHeader("Content-disposition","attachment; filename="+"BookType.xls");//filename是下载的xls的名，建议最好用英文 
			response.setContentType("application/msexcel;charset=UTF-8");//设置类型 
			response.setHeader("Pragma","No-cache");//设置头 
			response.setHeader("Cache-Control","no-cache");//设置头 
			response.setDateHeader("Expires", 0);//设置日期头  
			String rootPath = ServletActionContext.getServletContext().getRealPath("/");
			ex.exportExcel(rootPath,title,headers, dataset, out);
			out.flush();
		} catch (IOException e) { 
			e.printStackTrace(); 
		}finally{
			try{
				if(out!=null){ 
					out.close(); 
				}
			}catch(IOException e){ 
				e.printStackTrace(); 
			} 
		}
		return null;
    }
    /*前台查询BookType信息*/
    public String FrontQueryDepartment() {
        if(currentPage == 0) currentPage = 1;
        List<Department> departmentList = departmentDAO.QueryDepartmentInfo(currentPage);
        /*计算总的页数和总的记录数*/
        departmentDAO.CalculateTotalPageAndRecordNumber();
        /*获取到总的页码数目*/
        totalPage = departmentDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = departmentDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("departmentList",  departmentList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        return "front_query_view";
    }

    /*查询要修改的BookType信息*/
    public String ModifyDepartmentQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键bookTypeId获取BookType对象*/
        Department department = departmentDAO.GetDepartmentByDepartmentId(DepartmentId);

        ctx.put("department",  department);
        return "modify_view";
    }

    /*查询要修改的BookType信息*/
    public String FrontShowDepartmentQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键bookTypeId获取BookType对象*/
        Department departmentId = departmentDAO.GetDepartmentByDepartmentId(DepartmentId);

        ctx.put("DepartmentId",  DepartmentId);
        return "front_show_view";
    }

    /*更新修改BookType信息*/
    public String ModifyDepartment() {
        ActionContext ctx = ActionContext.getContext();
        try {
        	departmentDAO.UpdateDepartment(department);
            ctx.put("message",  java.net.URLEncoder.encode(Departmeninfo+"信息更新成功!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode(Departmeninfo+"信息更新失败!"));
            return "error";
       }
   }

    /*删除BookType信息*/
    public String DeleteDepartment() {
        ActionContext ctx = ActionContext.getContext();
        try { 
        	departmentDAO.DeleteDepartment(DepartmentId);
            ctx.put("message",  java.net.URLEncoder.encode(Departmeninfo+"删除成功!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode(Departmeninfo+"删除失败!"));
            return "error";
        }
    }

}
