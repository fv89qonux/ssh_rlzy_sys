<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%>
<%@ page import="com.ssh.domain.Post" %>
<%@ page import="com.ssh.domain.Department" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    //��ȡ���е�post��Ϣ
    List<Post> postList = (List<Post>)request.getAttribute("postList");
    //��ȡ���е�department��Ϣ
    List<Department> departmentList = (List<Department>)request.getAttribute("departmentList");
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
                title: '���ְԱ��Ϣ',
                width: 700,
                height : 500,
                url : ''
            });
        }); 
    </script>   
<HTML><HEAD><TITLE>���ְԱ</TITLE> 
<STYLE type=text/css>
BODY {
    	MARGIN-LEFT: 0px; BACKGROUND-COLOR: #ffffff
}
.STYLE1 {color: #ECE9D8}
.label {font-style.:italic; }
.errorLabel {font-style.:italic;  color:red; }
.errorMessage {font-weight:bold; color:red; }
</STYLE>

    
    <script type="text/javascript">
        $(function ()
        {
            $("#txtcsrq").ligerDateEditor({ label: '', labelWidth: 100, labelAlign: 'right', initValue: '1990-01-01' });
            $("#txtrzrq").ligerDateEditor({ label: '', labelWidth: 100, labelAlign: 'right', initValue: '2017-01-01' });
           
        });
    </script>
    

       <script type="text/javascript">
        var manager;
        $(function ()
        {

            $("#panel2").ligerPanel({
                title: '����ְԱ��Ϣ',
                width: 700,
                height : 500,
                url : ''
            });
        }); 
    </script>
<script language="javascript">
/*��֤��*/
function checkForm() {
    return true; 
}
 </script>
</HEAD>

<BODY style="padding:10px">
<div id="panel2" style=" margin:10px; clear:both;">

<s:fielderror cssStyle="color:red" />
<TABLE align="center" height="60%" cellSpacing=0 cellPadding=0 width="40%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign="middle" >
    <s:form action="Employee_AddEmployee.action" method="post" id="loanInfoAddForm" onsubmit="return checkForm();"  enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth" >

  <tr>
    <td width=30%>ְԱ����:</td>
    <td width=70%>
		<input id="employee.name" name="employee.name" type="text" size="18" placeholder="������ְԱ����" title="������ְԱ����"/>
    </td>
  </tr>

  <tr>
    <td width=30%>ְԱ����:</td>
    <td width=70%>
		<input id="employee.password" name="employee.password" type="text" size="18" placeholder="����������" title="����������"/>
    </td>
  </tr>
  
    <tr>
    <td width=30%>��������:</td>
    <td width=70%> <input type="text" id="txtcsrq" name="employee.birth" onclick="setDay(this);"/></td>
  </tr>

  <tr>
    <td width=30%>��ְ����:</td>
    <td width=70%><input type="text" id="txtrzrq" name="employee.rzTime" onclick="setDay(this);" /></td>
  </tr>
  
   <tr>
    <td width=40%>��������:</td>
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
    <td width=30%>��λ����:</td>
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
    <td width=30%>ְԱ�˺�:</td>
    <td width=70%>
		<input id="employee.zh" name="employee.zh" type="text" size="18" placeholder="" title=""/>
    </td>
  </tr>
    <tr>
    <td width=30%>��ע:</td>
    <td width=70%>
		<input id="employee.employeebz" name="employee.employeebz" type="text" size="18" />
    </td>
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
       </div>     
  </TBODY>
</TABLE>
</BODY>
</HTML>