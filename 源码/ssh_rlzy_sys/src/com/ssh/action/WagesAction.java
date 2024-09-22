package com.ssh.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.ssh.dao.WagesDAO;
import com.ssh.domain.Clock;
import com.ssh.domain.Wages;
import com.ssh.dao.EmployeeDAO;
import com.ssh.domain.Employee;

@Controller @Scope("prototype")
public class WagesAction extends ActionSupport {

/*图片字段wagesPhoto参数接收*/
	 private File wagesPhotoFile;
	 private String wagesPhotoFileFileName;
	 private String wagesPhotoFileContentType;
	 public File getWagesPhotoFile() {
		return wagesPhotoFile;
	}
	public void setWagesPhotoFile(File wagesPhotoFile) {
		this.wagesPhotoFile = wagesPhotoFile;
	}
	public String getWagesPhotoFileFileName() {
		return wagesPhotoFileFileName;
	}
	public void setWagesPhotoFileFileName(String wagesPhotoFileFileName) {
		this.wagesPhotoFileFileName = wagesPhotoFileFileName;
	}
	public String getWagesPhotoFileContentType() {
		return wagesPhotoFileContentType;
	}
	public void setWagesPhotoFileContentType(String wagesPhotoFileContentType) {
		this.wagesPhotoFileContentType = wagesPhotoFileContentType;
	}
    /*界面层需要查询的属性: 图书条形码*/
	
	
	private String wagesid;
	private int employeeid;
	
    public int getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}



	public String getWagesid() {
		return wagesid;
	}
	public void setWagesid(String wagesid) {
		this.wagesid = wagesid;
	}

	private String barcode;
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    public String getBarcode() {
        return this.barcode;
    }

    /*界面层需要查询的属性: 图书名称*/
    private String wagesname;
    public void setWagesName(String wagesName) {
        this.wagesname = wagesName;
    }
    public String getWagesName() {
        return this.wagesname;
    }

    /*界面层需要查询的属性: 图书所在类别*/
    private Employee employee;
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public Employee getEmployee() {
        return this.employee;
    }

    /*界面层需要查询的属性: 出版日期*/
    private String publishDate;
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
    public String getPublishDate() {
        return this.publishDate;
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
    @Resource WagesDAO wagesDAO;
    @Resource ClockDAO clockDAO;
    @Resource EmployeeDAO employeeDAO;
    /*待操作的Wages对象*/
    private Wages wages;
    public void setWages(Wages wages) {
        this.wages = wages;
    }
    public Wages getWages() {
        return this.wages;
    }

    /*跳转到添加Wages视图*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        /*查询所有的Employee信息*/
        List<Employee> employeeList = employeeDAO.QueryAllEmployeeInfo();
        ctx.put("employeeList", employeeList);
        return "add_view";
    }
    public String Addcsh() {
        ActionContext ctx = ActionContext.getContext();
        /*查询所有的Employee信息*/
        List<Employee> employeeList = employeeDAO.QueryAllEmployeeInfo();
        ctx.put("employeeList", employeeList);
        return "add_csh";
    }
    
    //####初始化工资信息
    /*添加Wages信息*/
    @SuppressWarnings("deprecation")
    public String AddWagescsh() {
        ActionContext ctx = ActionContext.getContext();

        List yuefen=new ArrayList();
        yuefen.add("201701");
        yuefen.add("201702");
        yuefen.add("201703");
        yuefen.add("201704");
        yuefen.add("201705");
        yuefen.add("201706");
        yuefen.add("201707");
        yuefen.add("201708");
        yuefen.add("201709");
        yuefen.add("201710");
        yuefen.add("201711");
        yuefen.add("201712");

        try {

        	if(true) {
                Employee employee = employeeDAO.GetEmployeeByLoadId(wages.getEmployee().getEmployeeid());
                wages.setEmployee(employee);
                }
        	
            	//String id=employee.getEmployeeid().toString();
            	//List<Employee> employeeList = employeeDAO.QueryAllEmployeeInfo();
            	 
                for (int i = 0; i < yuefen.size(); i++) {                	
                	wages.setWagesid(wages.getEmployee().getEmployeeid().toString()+yuefen.get(i));
                	wages.setEmployee(employeeDAO.GetEmployeeByLoadId(wages.getEmployee().getEmployeeid()));
                	wages.setZf("未支付");
                	wagesDAO.AddWages(wages);
                	}            
            	
            ctx.put("message",  java.net.URLEncoder.encode("Wages添加成功!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Wages添加失败!"));
            return "error";
        }
    }
    
    
    //当前日期
    SimpleDateFormat sdfdb=new SimpleDateFormat("yyyy-MM");  
    java.util.Date datedb=new java.util.Date();  
    String strdb=sdfdb.format(datedb); 
    
    //当前日期
    SimpleDateFormat sdfjs=new SimpleDateFormat("yyyy-MM-dd");  
    java.util.Date datejs=new java.util.Date();  
    String strjs=sdfjs.format(datejs); 
    
    
    private String id;
    
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	//####计算本月工资全勤信息
    /*添加Wages信息*/
    @SuppressWarnings("deprecation")
    public String AddWagesjs() {
        ActionContext ctx = ActionContext.getContext();
        try {
        	//查询全部人员
        	List<Employee> employeeList = employeeDAO.QueryAllEmployeeInfo();
        	//查询全部签到
        	for(Employee employeetem:employeeList){
        		int sum=0;
        		List<Clock> clocllist=(List<Clock>)clockDAO.QueryAllClockInfojs(employeetem.getEmployeeid(),strdb);
        		for(Clock clocktem:clocllist){
        			
		            long strsb=Long.valueOf(clocktem.getSbdate().replaceAll("[-\\s:]",""));
		            long strxb=Long.valueOf(clocktem.getXbdate().replaceAll("[-\\s:]",""));
		            String sb="09:00:00";
		            String xb="18:00:00";
		            long strsbsj=Long.valueOf(sb.replaceAll("[-\\s:]",""));
		            long strxbsj=Long.valueOf(xb.replaceAll("[-\\s:]",""));
                	if(strsb<strsbsj&&strxb>strxbsj){
                		sum++;
                	}
                }

        		Wages wages=new Wages();
        		
        		String id=employeetem.getEmployeeid().toString();
        		wagesid=id+strdb;
        		//Employee employee=new Employee();
        		//employee.setEmployeeid(employeetem.getEmployeeid());
        		wages.setWagesid(wagesid);
        		wages.setFfnd(strdb);
        		wages.setEmployee(employeetem);
        		wages.setQqjl(sum*100);
        		wages.setZf("未支付");
        		wagesDAO.AddWages(wages);
        	}
            ctx.put("message",  java.net.URLEncoder.encode("计算完毕!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("计算失败!"));
            return "error";
        }
    }
    
    
    

    /*添加Wages信息*/
    @SuppressWarnings("deprecation")
    public String AddWages() {
        ActionContext ctx = ActionContext.getContext();
        /*验证图书条形码是否已经存在*/
        String Wagesid = wages.getWagesid();
        Wages db_Wages = wagesDAO.GetWagesByWagesid(Wagesid);
        if(null != db_Wages) {
            ctx.put("error",  java.net.URLEncoder.encode("该图书条形码已经存在!"));
            return "error";
        }
        try {
            if(true) {
            Employee employee = employeeDAO.GetEmployeeByLoadId(wages.getEmployee().getEmployeeid());
            wages.setEmployee(employee);
            }
            String path = ServletActionContext.getServletContext().getRealPath("/upload"); 
            /*处理图片上传*/
            String wagesPhotoFileName = ""; 
       	 	if(wagesPhotoFile != null) {
       	 		InputStream is = new FileInputStream(wagesPhotoFile);
       			String fileContentType = this.getWagesPhotoFileContentType();
       			if(fileContentType.equals("image/jpeg")  || fileContentType.equals("image/pjpeg"))
       				wagesPhotoFileName = UUID.randomUUID().toString() +  ".jpg";
       			else if(fileContentType.equals("image/gif"))
       				wagesPhotoFileName = UUID.randomUUID().toString() +  ".gif";
       			else {
       				ctx.put("error",  java.net.URLEncoder.encode("上传图片格式不正确!"));
       				return "error";
       			}
       			File file = new File(path, wagesPhotoFileName);
       			OutputStream os = new FileOutputStream(file);
       			byte[] b = new byte[1024];
       			int bs = 0;
       			while ((bs = is.read(b)) > 0) {
       				os.write(b, 0, bs);
       			}
       			is.close();
       			os.close();
       	 	}

            wagesDAO.AddWages(wages);
            ctx.put("message",  java.net.URLEncoder.encode("Wages添加成功!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Wages添加失败!"));
            return "error";
        }
    }

    /*查询Wages信息*/
    public String QueryWages() {
        if(currentPage == 0) currentPage = 1;
        if(wagesid == null) wagesid = "";
        if(wagesname == null) wagesname = "";
        if(publishDate == null) publishDate = "";
        List<Wages> wagesList = wagesDAO.QueryWagesInfo(wagesid, wagesname, employee, currentPage);
        /*计算总的页数和总的记录数*/
        wagesDAO.CalculateTotalPageAndRecordNumber(wagesid, wagesname, employee);
        /*获取到总的页码数目*/
        totalPage = wagesDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = wagesDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("wagesList",  wagesList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("barcode", barcode);
        ctx.put("wagesname", wagesname);
        ctx.put("employee", employee);
        List<Employee> employeeList = employeeDAO.QueryAllEmployeeInfo();
        ctx.put("employeeList", employeeList);
        ctx.put("publishDate", publishDate);
        return "query_view";
    }

    /*后台导出到excel*/
    public String QueryWagesOutputToExcel() { 
        if(barcode == null) barcode = "";
        if(wagesname == null) wagesname = "";
        if(publishDate == null) publishDate = "";
        List<Wages> wagesList = wagesDAO.QueryWagesInfo(barcode,wagesname,employee);
        ExportExcelUtil ex = new ExportExcelUtil();
        String title = "Wages信息记录"; 
        String[] headers = { "图书条形码","图书名称","图书所在类别","图书价格","库存","出版日期","出版社","图书图片"};
        List<String[]> dataset = new ArrayList<String[]>(); 
        for(int i=0;i<wagesList.size();i++) {
        	Wages wages = wagesList.get(i); 
        	dataset.add(new String[]{wages.getWagesid(),wages.getEmployee().getName(),
wages.getJbgz() + "",wages.getGlgz() + "",wages.getGwjt()+"",});
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
			response.setHeader("Content-disposition","attachment; filename="+"Wages.xls");//filename是下载的xls的名，建议最好用英文 
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
    /*前台查询Wages信息*/
    public String FrontQueryWages() {
        if(currentPage == 0) currentPage = 1;
        if(barcode == null) barcode = "";
        if(wagesname == null) wagesname = "";
        if(publishDate == null) publishDate = "";
        List<Wages> wagesList = wagesDAO.QueryWagesInfo(barcode, wagesname, employee,  currentPage);
        /*计算总的页数和总的记录数*/
        wagesDAO.CalculateTotalPageAndRecordNumber(barcode, wagesname, employee);
        /*获取到总的页码数目*/
        totalPage = wagesDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = wagesDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("wagesList",  wagesList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("barcode", barcode);
        ctx.put("wagesname", wagesname);
        ctx.put("employee", employee);
        List<Employee> employeeList = employeeDAO.QueryAllEmployeeInfo();
        ctx.put("employeeList", employeeList);
        ctx.put("publishDate", publishDate);
        return "front_query_view";
    }

    /*查询要修改的Wages信息*/
    public String ModifyWagesQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键barcode获取Wages对象*/
        Wages wages = wagesDAO.GetWagesByWagesid(wagesid);

        List<Employee> employeeList = employeeDAO.QueryAllEmployeeInfo();
        ctx.put("employeeList", employeeList);
        ctx.put("wages",  wages);
        return "modify_view";
    }

    /*查询要修改的Wages信息*/
    public String FrontShowWagesQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键barcode获取Wages对象*/
        Wages wages = wagesDAO.GetWagesByWagesid(wagesid);

        List<Employee> employeeList = employeeDAO.QueryAllEmployeeInfo();
        ctx.put("employeeList", employeeList);
        ctx.put("wages",  wages);
        return "front_show_view";
    }

    /*更新修改Wages信息*/
    public String ModifyWages() {
        ActionContext ctx = ActionContext.getContext();
        try {
            if(true) {
            Employee employee = employeeDAO.GetEmployeeByLoadId(wages.getEmployee().getEmployeeid());
            wages.setEmployee(employee);
            }
            String path = ServletActionContext.getServletContext().getRealPath("/upload"); 
            /*处理图片上传*/
            String wagesPhotoFileName = ""; 
       	 	if(wagesPhotoFile != null) {
       	 		InputStream is = new FileInputStream(wagesPhotoFile);
       			String fileContentType = this.getWagesPhotoFileContentType();
       			if(fileContentType.equals("image/jpeg") || fileContentType.equals("image/pjpeg"))
       				wagesPhotoFileName = UUID.randomUUID().toString() +  ".jpg";
       			else if(fileContentType.equals("image/gif"))
       				wagesPhotoFileName = UUID.randomUUID().toString() +  ".gif";
       			else {
       				ctx.put("error",  java.net.URLEncoder.encode("上传图片格式不正确!"));
       				return "error";
       			}
       			File file = new File(path, wagesPhotoFileName);
       			OutputStream os = new FileOutputStream(file);
       			byte[] b = new byte[1024];
       			int bs = 0;
       			while ((bs = is.read(b)) > 0) {
       				os.write(b, 0, bs);
       			}
       			is.close();
       			os.close();
       	 	}
            wagesDAO.UpdateWages(wages);
            ctx.put("message",  java.net.URLEncoder.encode("工资信息更新成功!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("工资信息更新失败!"));
            return "error";
       }
   }
    
    
    public String zfWages() {
        ActionContext ctx = ActionContext.getContext();
        try {
/*            if(true) {
            Employee employee = employeeDAO.GetEmployeeByLoadId(wages.getEmployee().getEmployeeid());
            wages.setEmployee(employee);
            }*/
            wagesDAO.UpdateWageszf(wagesDAO.GetWagesByWagesid(wagesid));
            ctx.put("message",  java.net.URLEncoder.encode("支付成功!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("支付失败!"));
            return "error";
       }
   }

    /*删除Wages信息*/
    public String DeleteWages() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            wagesDAO.DeleteWages(barcode);
            ctx.put("message",  java.net.URLEncoder.encode("工资信息删除成功!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("工资信息删除失败!"));
            return "error";
        }
    }

}
