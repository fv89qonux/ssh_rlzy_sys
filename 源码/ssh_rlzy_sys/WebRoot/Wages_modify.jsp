<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%> 
<%@ page import="com.ssh.domain.Wages" %>
<%@ page import="com.ssh.domain.Employee" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    //��ȡ���е�employee��Ϣ
    List<Employee> employeeList = (List<Employee>)request.getAttribute("employeeList");
    Wages wages = (Wages)request.getAttribute("wages");

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
                title: '�޸Ĺ�����Ϣ',
                width: 700,
                height : 500,
                url : ''
            });
        }); 
    </script>
<HTML><HEAD><TITLE>�޸�ͼ��</TITLE>
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

</HEAD>
<BODY>
<div id="panel2" style=" margin:10px; clear:both;">
<s:fielderror cssStyle="color:red" />
<TABLE align="center" height="40%" cellSpacing=0 cellPadding=0 width="30%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=middle ><s:form action="Wages_ModifyWages.action" method="post" onsubmit="return checkForm();" enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth">

  <tr  hidden="hidden">
    <td width=30%>���ʱ��:</td>
    <td width=70%><input id="wages.wagesid" name="wages.wagesid" type="text" size="8" value='<%=wages.getWagesid() %>'/></td>
  </tr>

  <tr>
    <td width=30%>ְԱ����:</td>
    <td width=70%>
      <select name="wages.employee.employeeid">
      <%
        for(Employee employee:employeeList) {
          String selected = "";
          if(employee.getEmployeeid() == wages.getEmployee().getEmployeeid())
            selected = "selected";
      %>
          <option value='<%=employee.getEmployeeid() %>' <%=selected %>><%=employee.getName() %></option>
      <%
        }
      %>
      </select>
    </td>
  </tr>

  <tr>
    <td width=30%>��������:</td>
    <td width=70%><input id="wages.jbgz" name="wages.jbgz" type="text" size="8" value='<%=wages.getJbgz() %>'/></td>
  </tr>

  <tr>
    <td width=30%>ȫ�ڽ���:</td>
    <td width=70%><input id="wages.qqjl" name="wages.qqjl" type="text" size="8" value='<%=wages.getQqjl() %>'/></td>
  </tr>


  <tr>
    <td width=30%>��λ����:</td>
    <td width=70%><input id="wages.gwjt" name="wages.gwjt" type="text" size="20" value='<%=wages.getGwjt()%>'/></td>
  </tr>

  <tr>
    <td width=30%>���乤��:</td>
    <td width=70%><input id="wages.glgz" name="wages.glgz" value='<%=wages.getGlgz()%>'></input></td>
  </tr>
  
  <tr hidden="hidden">
    <td width=30%>�������:</td>
    <td width=70%><input id="wages.ffnd" name="wages.ffnd" value='<%=wages.getFfnd() %>'></input></td>
  </tr>
  <tr hidden="hidden">
    <td width=30%>֧��:</td>
    <td width=70%><input id="wages.zf" name="wages.zf" value='<%=wages.getZf() %>'></input></td>
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
