<%--
  Created by IntelliJ IDEA.
  User: Nikitos
  Date: 07.11.2009
  Time: 2:53:22
  Login page
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Login page</title></head>
<body>
<jsp:include page="page_errors.jsp"/>
<form action="login.action" method="POST">
    <table>
        <tr>
            <td>Login:</td>
            <td><input type="text" name="login"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password"/></td>
        </tr>
    </table>
    <input type="submit" value="Login"/>
</form>
<br/>
<a href="show_register.action">Register</a>
</body>
</html>