package com.ssh.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParsePosition;
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
import com.ssh.domain.Clock;
import com.ssh.domain.Department;
import com.ssh.dao.EmployeeDAO;
import com.ssh.domain.Employee;

@Controller @Scope("prototype")
public class ClockAction extends ActionSupport {

	public String Clockinfo ="-签到-";
	
	private Integer clockid;
	private String  clockbz;
	private Employee employee;

	public Integer getClockid() {
		return clockid;
	}
	public void setClockid(Integer clockid) {
		this.clockid = clockid;
	}
	public String getclockbz() {
		return clockbz;
	}
	public void setclockbz(String clockbz) {
		this.clockbz = clockbz;
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
    @Resource ClockDAO clockDAO;

    @Resource EmployeeDAO employeeDAO;
    /*待操作的Book对象*/
    private Clock clock;
    public Clock getClock() {
		return clock;
	}
	public void setClock(Clock clock) {
		this.clock = clock;
	}
	
	/*跳转到添加Book视图*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        /*查询所有的BookType信息*/
        /*
        List<Employee> employeeList = employeeDAO.QueryAllEmployeeInfo();
        ctx.put("employeeList", employeeList);*/
        return "add_view";
    }
    
    public String AddViewq() {
        ActionContext ctx = ActionContext.getContext();
        /*查询所有的BookType信息*/
        /*
        List<Employee> employeeList = employeeDAO.QueryAllEmployeeInfo();
        ctx.put("employeeList", employeeList);*/
        return "add_viewq";
    }


    //当前日期
    SimpleDateFormat sdfdb=new SimpleDateFormat("yyyy-MM-dd");  
    java.util.Date datedb=new java.util.Date();  
    String strdb=sdfdb.format(datedb); 
    
    
    SimpleDateFormat sdfqi=new SimpleDateFormat("yyyy-MM");  
    java.util.Date dateqi=new java.util.Date();  
    String strrq=sdfqi.format(dateqi); 
    
    //日期时间
    SimpleDateFormat sdfsb=new SimpleDateFormat("HH:mm:ss");  
    java.util.Date datesb=new java.util.Date();  
    String strsb=sdfsb.format(datesb); 


    /*添加岗位信息*/
    @SuppressWarnings("deprecation")
    public String AddClock() {
        ActionContext ctx = ActionContext.getContext();
        try {
            if(true) {
            Employee employee = employeeDAO.GetEmployeeByLoadId(clock.getEmployee().getEmployeeid());
            clock.setEmployee(employee);
            }
            
            clockDAO.AddClock(clock);
            
            ctx.put("message",  java.net.URLEncoder.encode(Clockinfo+"添加成功!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode(Clockinfo+"添加失败!"));
            return "error";
        }
    }
    //初始化
    @SuppressWarnings("deprecation")
    public String AddClockcsh() {
        ActionContext ctx = ActionContext.getContext();
        try {
            clockDAO.AddClock(clock);
            
            ctx.put("message",  java.net.URLEncoder.encode("初始化添加成功!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("初始化添加失败!"));
            return "error";
        }
    }
    
    @SuppressWarnings("deprecation")
    public String AddClockxb() {
        ActionContext ctx = ActionContext.getContext();
        try {
            if(true) {
            Employee employee = employeeDAO.GetEmployeeByLoadId(clock.getEmployee().getEmployeeid());
            clock.setEmployee(employee);;
            }
            clockDAO.AddClock(clock);
            ctx.put("message",  java.net.URLEncoder.encode(Clockinfo+"添加成功!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode(Clockinfo+"添加失败!"));
            return "error";
        }
    }
    
    
    //获得指定日期段内所有日期
    public static List<Date> findDates(Date dBegin, Date dEnd)
	 {
	  List lDate = new ArrayList();
	  lDate.add(dBegin);
	  Calendar calBegin = Calendar.getInstance();
	  // 使用给定的 Date 设置此 Calendar 的时间
	  calBegin.setTime(dBegin);
	  Calendar calEnd = Calendar.getInstance();
	  // 使用给定的 Date 设置此 Calendar 的时间
	  calEnd.setTime(dEnd);
	  // 测试此日期是否在指定日期之后
	  while (dEnd.after(calBegin.getTime()))
	  {
	   // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
	   calBegin.add(Calendar.DAY_OF_MONTH, 1);
	   lDate.add(calBegin.getTime());
	  }
	  return lDate;
	 }
    //初始化设置   签到
    @SuppressWarnings("deprecation")
    public String AddClockchushihua() {
        ActionContext ctx = ActionContext.getContext();
        try {
            if(true) {
            Employee employee = employeeDAO.GetEmployeeByLoadId(clock.getEmployee().getEmployeeid());
            clock.setEmployee(employee);
            
            
            
            
            //添加人员同时添加工资和
	          Calendar cal = Calendar.getInstance();
	      	  String start = "2017-01-01";
	      	  String end = "2017-12-31";
	      	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	      	  Date dBegin = sdf.parse(start);
	      	  Date dEnd = sdf.parse(end);
	      	  List<Date> lDate = findDates(dBegin, dEnd);

	      	  
	      	  String zy=clock.getEmployee().getEmployeeid().toString();
	      	
	      	  for (Date date : lDate)
	      	  {
	      		clock.setClockid(zy+sdf.format(date));
	      		clock.setEmployee(employeeDAO.GetEmployeeByLoadId(clock.getEmployee().getEmployeeid()));
	      		clock.setRiqi(sdf.format(date));
	      		
	      		clockDAO.AddClock(clock);
	      		
	      	  }
            }
           
            ctx.put("message",  java.net.URLEncoder.encode("初始化成功!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("已经初始化过了!"));
            return "error";
        }
    }

    /*查询岗位信息*/
    public String QueryClock() {
        if(currentPage == 0) currentPage = 1;
        if(clockid == null) clockid = 0;
        if(clockbz == null) clockbz = "";
        List<Clock> clockList = clockDAO.QueryClockInfo(clockid, clockbz, employee);
        /*计算总的页数和总的记录数*/
        clockDAO.CalculateTotalPageAndRecordNumber(clockid, clockbz, employee);
        /*获取到总的页码数目*/
        totalPage = clockDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = clockDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("clockList",  clockList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("clockid", clockid);
        ctx.put("clockbz", clockbz);
        ctx.put("employee", employee);
        List<Employee> employeeList = employeeDAO.QueryAllEmployeeInfo();
        ctx.put("employeeList", employeeList);
        
        return "query_view";
    }

 
    /*前台查询Book信息*/
    public String FrontQueryClock() {
        if(currentPage == 0) currentPage = 1;
        if(clockid == null) clockid = 0;
        if(clockbz == null) clockbz = "";
        List<Clock> clockList = clockDAO.QueryClockInfo(clockid, clockbz, employee, currentPage);
        /*计算总的页数和总的记录数*/
        clockDAO.CalculateTotalPageAndRecordNumber(clockid, clockbz, employee);
        /*获取到总的页码数目*/
        totalPage = clockDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = clockDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("clockList",  clockList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("clockid", clockid);
        ctx.put("clockbz", clockbz);
        ctx.put("employee", employee);
        List<Employee> employeeList = employeeDAO.QueryAllEmployeeInfo();
        ctx.put("employeeList", employeeList);
        return "front_query_view";
    }

    /*查询要修改的Book信息*/
    public String ModifyClockQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键barcode获取Book对象*/
        Clock clock = clockDAO.GetClockByClockId(clockid);

        List<Employee> employeeList = employeeDAO.QueryAllEmployeeInfo();
        ctx.put("employeeList", employeeList);
        ctx.put("clock",  clock);
        return "modify_view";
    }

    /*查询要修改的Book信息*/
    public String FrontShowClockQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键barcode获取Book对象*/
        
        
            
            
        Clock clock = clockDAO.GetClockByClockId(clockid);

        List<Employee> employeeList = employeeDAO.QueryAllEmployeeInfo();
        ctx.put("employeeList", employeeList);
        ctx.put("clock",  clock);
        return "front_show_view";
    }

    public String ModifyClock() {
        ActionContext ctx = ActionContext.getContext();
        try {
        	if(true) {
                Employee employee = employeeDAO.GetEmployeeByLoadId(clock.getEmployee().getEmployeeid());
                clock.setEmployee(employee);
                }
        	clock.setClockid(clock.getEmployee().getEmployeeid()+strdb);
        	clock.setRiqi(strrq);
        	clock.setNyr(strdb);
        	clock.setSbdate(strsb);
        	clockDAO.AddClock(clock);
            ctx.put("message",  java.net.URLEncoder.encode("签到成功!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("已签到，无需重新签到!"));
            return "error";
       }
   }
    
    public String ModifyClockxb() {
        ActionContext ctx = ActionContext.getContext();
        try {
        	if(true) {
                Employee employee = employeeDAO.GetEmployeeByLoadId(clock.getEmployee().getEmployeeid());
                clock.setEmployee(employee);
                }
        	clock.setClockid(clock.getEmployee().getEmployeeid()+strdb);
        	clock.setXbdate(strsb);
        	clockDAO.UpdateClockxb(clock);
            ctx.put("message",  java.net.URLEncoder.encode("签到成功!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("已签到，无需重新签到!"));
            return "error";
       }
   }
    
    public String ModifyClockq() {
        ActionContext ctx = ActionContext.getContext();
        try {
        	if(true) {
                Employee employee = employeeDAO.GetEmployeeByLoadId(clock.getEmployee().getEmployeeid());
                clock.setEmployee(employee);
                }
        	clock.setClockid(clock.getEmployee().getEmployeeid()+clock.getClockbz().toString());
        	clock.setClockbz(clockbz);
        	clock.setClockbz("请假");
        	clockDAO.UpdateClockq(clock);
            ctx.put("message",  java.net.URLEncoder.encode("请假成功!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("请假失败!"));
            return "error";
       }
   }

}
