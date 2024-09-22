<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%> 
<%@ page import="com.ssh.domain.Employee" %>
<%@ page import="com.ssh.domain.Post" %>
<%@ page import="com.ssh.domain.Department" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    //获取所有的post信息
    List<Post> postList = (List<Post>)request.getAttribute("postList");
    //获取所有的department信息
    List<Department> departmentList = (List<Department>)request.getAttribute("departmentList");
    Employee employee = (Employee)request.getAttribute("employee");

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
                title: '修改帐号信息',
                width: 700,
                height : 500,
                url : ''
            });
        }); 
    </script>
<HTML><HEAD><TITLE>修改借阅信息</TITLE>
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
/*验证表单*/
function checkForm() {
    return true; 
}
 </script>
</HEAD>
<BODY >
<div id="panel2" style=" margin:10px; clear:both;">
<s:fielderror cssStyle="color:red" />
<TABLE align="center" height="40%" cellSpacing=0 cellPadding=0 width="30%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=middle ><s:form action="Employee_ModifyEmployee.action" method="post" onsubmit="return checkForm();" enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth">
  <tr>
    <td width=30%>职员编号:</td>
    <td width=70%><input id="employee.employeeid" name="employee.employeeid" type="text" value="<%=employee.getEmployeeid() %>" readOnly /></td>
  </tr>
  <tr>
    <td width=30%>职员姓名:</td>
    <td width=70%><input id="employee.name" name="employee.name" type="text" value="<%=employee.getName() %>"  /></td>
  </tr>
  <tr hidden="hidden">
    <td width=30%>岗位名称:</td>
    <td width=70%>
      <select name="employee.post.postid">
      <%
        for(Post post:postList) {
          String selected = "";
          if(post.getPostid().equals(employee.getPost().getPostid()))
            selected = "selected";
      %>
          <option value='<%=post.getPostid() %>' <%=selected %>><%=post.getPostname() %></option>
      <%
        }
      %>
    </td>
  </tr>

  <tr hidden="hidden">
    <td width=30%>部门名称:</td>
    <td width=70%>
      <select name="employee.department.departmentid">
      <%
        for(Department department:departmentList) {
          String selected = "";
          if(department.getDepartmentid().equals(employee.getDepartment().getDepartmentid()))
            selected = "selected";
      %>
          <option value='<%=department.getDepartmentid() %>' <%=selected %>><%=department.getDepartmentname() %></option>
      <%
        }
      %>
    </td>
  </tr>
  <tr hidden="hidden">
    <td width=30%>出生日期:</td>
    <td width=70%><input id="employee.birth" name="employee.birth" type="text" value="<%=employee.getBirth() %>"  /></td>
  </tr>
    <tr hidden="hidden">
    <td width=30%>入职日期:</td>
    <td width=70%><input id="employee.rzTime" name="employee.rzTime" type="text" value="<%=employee.getRzTime() %>"  /></td>
  </tr>
    <tr hidden="hidden">
    <td width=30%>备注:</td>
    <td width=70%><input id="employee.employeebz" name="employee.employeebz" type="text" value="<%=employee.getEmployeebz() %>"  /></td>
  </tr>
    <tr>
    <td width=30%>账号:</td>
    <td width=70%><input id="employee.zh" name="employee.zh" type="text" value="<%=employee.getZh() %>"  /></td>
  </tr>
    <tr hidden="hidden">
    <td width=30%>帐号:</td>
    <td width=70%><input id="employee.password" name="employee.password" type="text" value="<%=employee.getPassword() %>"  /></td>
  </tr>
  <tr bgcolor='#FFFFFF'>
      <td colspan="4" align="center">
        <input type='submit' name='button' value='保存' >
        &nbsp;&nbsp;
        <input type="reset" value='重写' />
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
