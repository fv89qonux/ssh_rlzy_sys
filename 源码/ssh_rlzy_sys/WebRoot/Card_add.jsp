<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%>
<%@ page import="com.ssh.domain.Employee" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    //��ȡ���еĲ�����Ϣ
    
    List<Employee> employeeList = (List<Employee>)request.getAttribute("employeeList");
    String username=(String)session.getAttribute("username");
    if(username==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "login/login_view.action';</script>");
    }
%>
<link href="<%=basePath %>lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="<%=basePath %>lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="<%=basePath %>lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
    <link href="<%=basePath %>lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" /> 
    <script src="<%=basePath %>lib/ligerUI/js/plugins/ligerPanel.js" type="text/javascript"></script>
 <script type="text/javascript">
        var manager;
        $(function ()
        {

            $("#panel2").ligerPanel({
                title: '��ӷ�����Ϣ',
                width: 700,
                height : 500,
                url : ''
            });
        }); 
    </script>
<HTML><HEAD><TITLE></TITLE> 
<STYLE type=text/css>
BODY {
    	MARGIN-LEFT: 0px; BACKGROUND-COLOR: #ffffff
}
.STYLE1 {color: #ECE9D8}
.label {font-style.:italic; }
.errorLabel {font-style.:italic;  color:red; }
.errorMessage {font-weight:bold; color:red; }
</STYLE>
 <script src="<%=basePath %>calendar.js"></script>
<script language="javascript">
/*��֤��*/
function checkForm() {
    var barcode = document.getElementById("book.barcode").value;
    if(barcode=="") {
        alert('�������λ����!');
        return false;
    }
    var bookName = document.getElementById("book.bookName").value;
    if(bookName=="") {
        alert('�������λ����!');
        return false;
    }
    return true; 
}
 </script>
</HEAD>

<BODY >
<div id="panel2" style=" margin:10px; clear:both;">
<s:fielderror cssStyle="color:red" />
<TABLE align="center" height="30%" cellSpacing=0 cellPadding=0 width="25%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=middle >
    <s:form action="Card_AddCard.action" method="card" id="cardAddForm" onsubmit="return checkForm();"  enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth">

  <tr>
  </tr>
  <tr>
    <td width=30%>ְԱ����:</td>
    <td width=70%>
      <select name="card.employee.employeeid">
      <%
        for(Employee employee:employeeList) {
      %>
          <option value='<%=employee.getEmployeeid() %>'><%=employee.getName() %></option>
      <%
        }
      %>
    </td>
  </tr>
  <tr>
    <td width=30%>ԭ����:</td>
    <td width=70%><input id="card.ykh" name="card.ykh" type="text" size="20" /></td>
  </tr>
  
    <tr>
    <td width=30%>����:</td>
    <td width=70%><input id="card.xkh" name="card.xkh" type="text" size="20" /></td>
  </tr>
  
  <tr bgcolor='#FFFFFF'>
      <td colspan="4" align="center">
        <input type='submit' name='button' value='����' >
        &nbsp;&nbsp;
        <input type="reset" value='��д' />
      </td>
    </tr>

</table>
</s:form>
   </TD></TR>
  </TBODY>
</TABLE>
</div>
</BODY>
</HTML>
