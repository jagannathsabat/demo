<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<html>
<head>
    <title>Login Page</title>
</head>

<h2>Hello, please log in:</h2>
<br><br>
<body>
	<f:form method="post" action="${pageContext.request.contextPath}/auth/login" modelAttribute="LOGIN_MODEL">
	    <p><strong>Please Enter Your User Name: </strong>
	    <f:input path="j_username" size="25"/>
	    <p><p><strong>Please Enter Your Password: </strong>
	    <f:password size="15" path="j_password"/>
	    <p><p>
	    <input type="submit" value="Submit">
	    <input type="reset" value="Reset">
	</f:form>
</body>
</html>