<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String username=(String)session.getAttribute("username");
    if(username==null){
        response.getWriter().println("<script>top.location.href='" + basePath + "login/login_view.action';</script>");
    }
%>

<link href="<%=basePath %>lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath %>lib/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
<script src="<%=basePath %>lib/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="<%=basePath %>lib/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
<script src="<%=basePath %>lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script src="<%=basePath %>lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
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
                title: '添加部门信息',
                width: 700,
                height : 500,
                url : ''
            });
        }); 
    </script>
<HTML><HEAD><TITLE>添加部门</TITLE> 
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
    var departmentName = document.getElementById("department.departmentName").value;
    if(departmentName=="") {
        alert('请输入部门名称!');
        return false;
    }
    return true; 
}
 </script>
     <script type="text/javascript"> 
        function f_tip() {
        	var message=${message};
        	if(message==''){
        		
        	}else{
			
        		$.ligerDialog.tip({  title: '提示信息',content:'${message}' });
        	}
        }
        var tip;
        var num = 0;
        function f_tip2() {
            if (!tip) {
                tip = $.ligerDialog.tip({ title: '提示信息', content: '记录已经删除！' });
            }
            else {
                var visabled = tip.get('visible');
                if (!visabled) tip.show();
                tip.set('content', '内容改变' + num++);
            }
        } 
    </script>
</HEAD>

 <BODY onload="f_tip();"  > 
 <div id="panel2" style=" margin:10px; clear:both;">
<s:fielderror cssStyle="color:red" />
<TABLE align="center" height="20%" cellSpacing=0 cellPadding=0 width="25%" border=0>
  <TBODY>
  <TR>
    <TD align="left" vAlign=middle >
    <s:form action="Department_AddDepartment.action" method="post" id="departmentAddForm" onsubmit="return checkForm();"  enctype="multipart/form-data" name="form1">
<table width='' cellspacing='1' cellpadding='3' class="tablewidth">

  <tr>
    <tr>
    <td width=50%>&nbsp;&nbsp; </td>
    <td width=50%> </td>
  </tr>
  <tr>
    <td width=50%>&nbsp;&nbsp; </td>
    <td width=50%> </td>
  </tr>
  <td width=50%>部门名称:</td>
    <td width=50%><input id="department.departmentname" name="department.departmentname" type="text" size="18" /></td>
  </tr>
  <tr>
    <td width=50%>&nbsp;&nbsp; </td>
    <td width=50%> </td>
  </tr>
  <tr bgcolor='#FFFFFF'>
      <td width=50%></td>
      <td width=50%><input type='submit' name='button' value='保存' >&nbsp;&nbsp; 
        <input type="reset" value='重置' /></td>
        
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