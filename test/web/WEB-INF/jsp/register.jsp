<%--
  Created by IntelliJ IDEA.
  User: Nikitos
  Date: 08.11.2009
  Time: 2:15:06
  Registration page
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Registration</title></head>
<body>
<jsp:include page="page_errors.jsp"/>
<form action="register.action" method="POST">          
    <table>
        <tr>
            <td>Login:</td>
            <td><input type="text" name="login"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="text" name="password"/></td>
        </tr>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>Surname:</td>
            <td><input type="text" name="surname"/></td>
        </tr>
    </table>
    <input type="submit" value="Create"/>
</form>
</body>
</html>