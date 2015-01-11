<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
</head>
<body>
<div>
	<h1>User Information</h1>
	<f:form method="post" modelAttribute="REGISTER_MODEL">
		<f:label path="userName">Username</f:label>
		<f:input path="userName"/><br/>
		<f:label path="password">Password</f:label>
		<f:password path="password"/><br/>
		
		<input type="submit"/>
	</f:form>
</div>
</body>
</html>