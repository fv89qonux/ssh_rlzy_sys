<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
    <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
                title: '请假',
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

  
    <script type="text/javascript">
        $(function ()
        {
            $("#txt1").ligerDateEditor({ showTime: true, label: '带时间', labelWidth: 100, labelAlign: 'left' });

            $("#txtcsrq").ligerDateEditor({ label: '', labelWidth: 100, labelAlign: 'right', initValue: '2017-01-01' });
            $("#txtrzrq").ligerDateEditor({ label: '', labelWidth: 100, labelAlign: 'right', initValue: '1990-01-01' });
            
        });
    </script>
</head>
<body>
<div id="panel2" style=" margin:30px; clear:both; " >

<div style=" margin:30px; clear:both; " >
	<s:form action="Clock_ModifyClockq" method="post">
	<input type="hidden" name="clock.employee.employeeid" value="<%=session.getAttribute("username") %>"> 
	

    <td width=30%>请假日期:</td>
    <td width=70%> <input type="text" id="txtcsrq" name="clock.clockbz"/></td>

	<s:submit value="请假"></s:submit>
	</s:form>
</div>
</div>
</body>
</html>