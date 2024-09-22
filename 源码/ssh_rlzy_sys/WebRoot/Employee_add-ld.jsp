<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%>
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
    String username=(String)session.getAttribute("username");
    if(username==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "login/login_view.action';</script>");
    }
%>
<HTML><HEAD><TITLE>添加职员</TITLE> 
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
 
 
    <link href="<%=basePath %>lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <script src="<%=basePath %>lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>lib/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="<%=basePath %>lib/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
    <link href="<%=basePath %>lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <link href="<%=basePath %>lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" /> 
  
    <script type="text/javascript">
        $(function ()
        {
            $("#txt1").ligerDateEditor({ showTime: true, label: '带时间', labelWidth: 100, labelAlign: 'left' });

            $("#txtcsrq").ligerDateEditor({ label: '', labelWidth: 100, labelAlign: 'right', initValue: '1990-01-01' });
            $("#txtrzrq").ligerDateEditor({ label: '', labelWidth: 100, labelAlign: 'right', initValue: '2017-01-01' });
            $("#txt3").ligerDateEditor(
                {

                    format: "MM/dd/yyyy",
                    label: '格式化日期',
                    labelWidth: 100,
                    labelAlign: 'center',
                    cancelable : false
            });


            $("#txt4").ligerDateEditor();
             

            $(".btn").click(function ()
            {
                alert("txt1:" + $("#txt1").val());
                alert("txt2:" + $("#txt2").val());
            });

            $("#txt1").change(function ()
            {
                $(".message").html("txt1 value change:" + $("#txt1").val())
            });

            $(".btn2").click(function ()
            {
                $("#txt2").ligerGetDateEditorManager().setDisabled();
            });
            $(".btn3").click(function ()
            {
                $("#txt2").ligerGetDateEditorManager().setEnabled();
            });
            $(".btn4").click(function ()
            {
                alert($("#txt3").ligerGetDateEditorManager().getValue());
            });
            $(".btn5").click(function ()
            {
                $("#txt3").ligerGetDateEditorManager().setValue('2011-03-07');
            });
        });
    </script>
 
<script language="javascript">
/*验证表单*/
function checkForm() {
    return true; 
}
 </script>
</HEAD>

<BODY >
<s:fielderror cssStyle="color:red" />
<TABLE align="center" height="60%" cellSpacing=0 cellPadding=0 width="30%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign="middle" >
    <s:form action="Employee_AddEmployee.action" method="post" id="loanInfoAddForm" onsubmit="return checkForm();"  enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth" border=1>

  <tr>
    <td width=30%>职员名称:</td>
    <td width=70%>
		<input id="employee.name" name="employee.name" type="text" size="18" placeholder="请输入职员名称" title="请输入职员名称"/>
    </td>
  </tr>

  <tr>
    <td width=30%>职员密码:</td>
    <td width=70%>
		<input id="employee.password" name="employee.password" type="text" size="18" placeholder="请输入密码" title="请输入密码"/>
    </td>
  </tr>
  
    <tr>
    <td width=30%>出生日期:</td>
    <td width=70%> <input type="text" id="txtcsrq" name="employee.birth"/></td>
  </tr>

  <tr>
    <td width=30%>入职日期:</td>
    <td width=70%><input type="text" id="txtrzrq" name="employee.rzTime"/></td>
  </tr>
  
   <tr>
    <td width=30%>部门名称：</td>
    <td width=70%>
      <select name="employee.department.departmentid">
      <%
        for(Department department:departmentList) {
      %>
          <option value='<%=department.getDepartmentid() %>'><%=department.getDepartmentname() %></option>
      <%
        }
      %>
    </td>
  </tr>
  
  <tr>
    <td width=30%>岗位名称:</td>
    <td width=70%>
      <select name="employee.post.postid">
      <%
        for(Post post:postList) {
      %>
          <option value='<%=post.getPostid() %>'><%=post.getPostname() %></option>
      <%
        }
      %>
    </td>
  </tr>

  <tr>
    <td width=30%>职员账号:</td>
    <td width=70%>
		<input id="employee.zh" name="employee.zh" type="text" size="18" placeholder="" title=""/>
    </td>
  </tr>
    <tr>
    <td width=30%>备注:</td>
    <td width=70%>
		<input id="employee.employeebz" name="employee.employeebz" type="text" size="18" />
    </td>
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
</BODY>
</HTML>