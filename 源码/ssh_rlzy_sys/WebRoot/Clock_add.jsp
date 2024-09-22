<%@ page language="java" import="java.util.*"  contentType="text/html;charset=gb2312"%>
<%@ page import="com.ssh.domain.Department" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    //获取所有的部门信息
    
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
                title: '签到打卡',
                width: 700,
                height : 500,
                url : ''
            });
        }); 
    </script>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

 <style type="text/css">  
input.text{text-align:center;padding:10px 20px;width:300px;}  
</style>  

</head>
<body  >
<div id="panel2" style=" margin:30px; clear:both; " >

			<div style=" margin:30px;">
			<s:form action="Clock_ModifyClock" method="post">
			<input type="hidden" name="clock.employee.employeeid" value="<%=session.getAttribute("username") %>"> 
			
			
				<s:submit value="打卡上班"></s:submit> 
			</s:form>
			</div>
			<div style=" margin:30px;">
			<s:form action="Clock_ModifyClockxb" method="post">
			<input type="hidden" name="clock.employee.employeeid" value="<%=session.getAttribute("username") %>"> 
			
			
				<s:submit value="打卡下班"></s:submit>	
			</s:form>
		</div>
</div>
</body>
</html>