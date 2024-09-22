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

import com.ssh.dao.CardDAO;
import com.ssh.domain.Card;
import com.ssh.dao.EmployeeDAO;
import com.ssh.domain.Employee;

@Controller @Scope("prototype")
public class CardAction extends ActionSupport {

	public String Cardinfo ="-卡信息-";
	
	private Integer cardid;
	private String  cardname;
	private Employee employee;

	public Integer getCardid() {
		return cardid;
	}
	public void setCardid(Integer cardid) {
		this.cardid = cardid;
	}
	public String getCardname() {
		return cardname;
	}
	public void setCardname(String cardname) {
		this.cardname = cardname;
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
    @Resource CardDAO cardDAO;

    @Resource EmployeeDAO employeeDAO;
    /*待操作的Book对象*/
    private Card card;
    public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
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
    public String AddCard() {
        ActionContext ctx = ActionContext.getContext();
        try {
            if(true) {
            Employee employee = employeeDAO.GetEmployeeByLoadId(card.getEmployee().getEmployeeid());
            card.setEmployee(employee);;
            }
            cardDAO.AddCard(card);
            ctx.put("message",  java.net.URLEncoder.encode(Cardinfo+"添加成功!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode(Cardinfo+"添加失败!"));
            return "error";
        }
    }

    /*查询岗位信息*/
    public String QueryCard() {
        if(currentPage == 0) currentPage = 1;
        if(cardid == null) cardid = 0;
        if(cardname == null) cardname = "";
        List<Card> cardList = cardDAO.QueryCardInfo(cardid, cardname, employee);
        /*计算总的页数和总的记录数*/
        cardDAO.CalculateTotalPageAndRecordNumber(cardid, cardname, employee);
        /*获取到总的页码数目*/
        totalPage = cardDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = cardDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("cardList",  cardList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("cardid", cardid);
        ctx.put("cardname", cardname);
        ctx.put("employee", employee);
        List<Employee> employeeList = employeeDAO.QueryAllEmployeeInfo();
        ctx.put("employeeList", employeeList);
        
        return "query_view";
    }

 
    /*前台查询Book信息*/
    public String FrontQueryCard() {
        if(currentPage == 0) currentPage = 1;
        if(cardid == null) cardid = 0;
        if(cardname == null) cardname = "";
        List<Card> cardList = cardDAO.QueryCardInfo(cardid, cardname, employee, currentPage);
        /*计算总的页数和总的记录数*/
        cardDAO.CalculateTotalPageAndRecordNumber(cardid, cardname, employee);
        /*获取到总的页码数目*/
        totalPage = cardDAO.getTotalPage();
        /*当前查询条件下总记录数*/
        recordNumber = cardDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("cardList",  cardList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("cardid", cardid);
        ctx.put("cardname", cardname);
        ctx.put("employee", employee);
        List<Employee> employeeList = employeeDAO.QueryAllEmployeeInfo();
        ctx.put("employeeList", employeeList);
        return "front_query_view";
    }

    /*查询要修改的Book信息*/
    public String ModifyCardQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键barcode获取Book对象*/
        Card card = cardDAO.GetCardByCardId(cardid);

        List<Employee> employeeList = employeeDAO.QueryAllEmployeeInfo();
        ctx.put("employeeList", employeeList);
        ctx.put("card",  card);
        return "modify_view";
    }

    /*查询要修改的Book信息*/
    public String FrontShowCardQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*根据主键barcode获取Book对象*/
        Card card = cardDAO.GetCardByCardId(cardid);

        List<Employee> employeeList = employeeDAO.QueryAllEmployeeInfo();
        ctx.put("employeeList", employeeList);
        ctx.put("card",  card);
        return "front_show_view";
    }

    /*更新修改Book信息*/
    public String ModifyCard() {
        ActionContext ctx = ActionContext.getContext();
        try {
            if(true) {
            Employee employee = employeeDAO.GetEmployeeByLoadId(card.getEmployee().getEmployeeid());
            card.setEmployee(employee);
            }
       	 	cardDAO.UpdateCard(card);
            ctx.put("message",  java.net.URLEncoder.encode(Cardinfo+"信息更新成功!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode(Cardinfo+"信息更新失败!"));
            return "error";
       }
   }

    /*删除Book信息*/
    public String DeleteCard() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            cardDAO.DeleteCard(cardid);
            ctx.put("message",  java.net.URLEncoder.encode(Cardinfo+"删除成功!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode(Cardinfo+"删除失败!"));
            return "error";
        }
    }

}
