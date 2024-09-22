<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>登陆页面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="Page-Enter" content="revealtrans(Duration=0.5,Transition=12)" />
    <meta http-equiv="Page-Exit" content="revealtrans(Duration=0.5,Transition=12)" />
    <link href="<%=basePath %>lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" />
    <link href="<%=basePath %>css/public.css" rel="stylesheet" />

    <script src="<%=basePath %>lib/jquery/jquery-1.5.2.min.js"></script>

    <script src="<%=basePath %>lib/ligerUI/js/ligerui.all.js"></script>

    <link href="<%=basePath %>css/demo.css" rel="stylesheet" />
    <link href="<%=basePath %>css/iconfont.css" rel="stylesheet" />

    <script>
        //绑定回车事件
        $(function() {
            $("#txtName").keydown(function(event) {
                if (event.keyCode == 13) {
                    $("#btnLogin").click();
                }
            }).focus(); //获得焦点

            $("#txtPwd").keydown(function(event) {
                if (event.keyCode == 13) {
                    $("#btnLogin").click();
                }
            });
            //跳出框架
            try {
                if (parent != window) {
                    parent.location.href = location.href;
                }
            } catch (e) {
                parent.location.reload();
            }

            //登录
            $("#btnLogin").click(function() {
                var txtName = $('#txtName').val();
                var txtPwd = $('#txtPwd').val();
                if (txtName == '' || txtPwd == '') {
                    tipMessage("请输入用户名和密码!", "warn");
                    return;
                }
                tipMessage("正在登录......", "successed");

                if (txtName == "admin" && txtPwd == "123456") {
                    tipMessage("正在跳转......", "successed");
                    window.location.replace("index.htm");
                } else {
                    /* tipMessage("用户名或密码错误!", "error"); */
                }

            });
            //重置按钮
            $("#reset").click(function() {
                $('#txtName,#txtPwd').val("");
            });
        });

        //提示信息
        function tipMessage(msg, type) {
            var msgDiv = $(".tip");
            msgDiv.html("<div class=" + type + ">" + msg + "</div>");
        }
    </script>

</head>
<body>
    <div class="kj_bg">
        <div class="kj_nav">
            <div class="autoHeight">
            </div>
        </div>
    </div>
    <div class="kj_login">
     <form action="<%=basePath %>login_CheckLogin.action" method="post" name="form1">
        <div class="mar_b15 tip">
        </div>
        <div class="kj_lbox mar_b15">
            <i><span class="icon iconfont">&#xe609;</span></i>
            <input type="text" id="txtName" name="admin.username" value="" placeholder="请输入帐号"
                title="请输入帐号" />
        </div>
        <div class="kj_lbox mar_b15">
            <i><span class="icon iconfont">&#xe60a;</span></i>
            <input type="password" value="" id="txtPwd" name="admin.password" placeholder="请输入你的密码" title="请输入你的密码" />
        </div>
        <div class="kj_lbnt">
            <a href="javascript:void(0);" onclick="document.forms[0].submit();" id="btnLogin" class="f_L blue_bg">确 定</a> <a href="javascript:void(0);"
                id="reset" class="f_R gray_bg">重 置</a>
        </div>
        </form>
    </div>
    <div class="kj_footer">
        人力资源管理系统 </div>
</body>
</html>
