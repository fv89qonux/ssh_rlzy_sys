<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%> 
<%@ page import="com.ssh.domain.Department" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    Department department = (Department)request.getAttribute("department");

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
                title: '修改部门信息',
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
/*验证表单*/
function checkForm() {
    var departmentName = document.getElementById("department.departmentname").value;
    if(departmentName=="") {
        alert('请输入类别名称!');
        return false;
    }
    return true; 
}
 </script>
</HEAD>
<BODY >
 <div id="panel2" style=" margin:10px; clear:both;">
<s:fielderror cssStyle="color:red" />
<TABLE align="center" height="25%" cellSpacing=0 cellPadding=0 width="25%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=middle ><s:form action="Department_ModifyDepartment.action" method="post" onsubmit="return checkForm();" enctype="multipart/form-data" name="form1">
<table width='100%' cellspacing='1' cellpadding='3' class="tablewidth">

  <tr><input hidden="hidden" id="department.departmentid" name="department.departmentid" type="text" size="18" value='<%=department.getDepartmentid() %>'/></td>
  </tr>
  <tr>
    <td width=30%>部门名称:</td>
    <td width=70%><input id="department.departmentname" name="department.departmentname" type="text" size="18" value='<%=department.getDepartmentname() %>'/></td>

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
