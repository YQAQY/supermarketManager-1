<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员主界面</title>
</head>
<body>
<span>欢迎管理员：<font>${admin} 登录系统</font></span><br />
<div width = "500" align = "center">
	<h1>管理员功能页面</h1>
	<a href = "../userManager/listUser">用户管理</a> <br/><br/><br/>
	<a href = "../billManager/listBill">订单管理</a> <br/><br/><br/>
	<a href = "../providerManager/listProvider">供应商管理</a><br/> <br/><br/>
	<a href = "../authority/exit" >退出系统</a><br/>
</div>
</body>
</html>