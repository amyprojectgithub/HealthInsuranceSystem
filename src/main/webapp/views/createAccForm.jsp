<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
       <h1 style="color:green;text-align: center">:::::SignUp To HIS:::::</h1>
       <h2 style="color: red;text-align: center"> ${msg} </h2>
	<form:form action="saveAcc" method="POST" modelAttribute="adminAcc">
      <table align="center" bgcolor="cyan">
      <tr> <td><form:hidden path="accountId"/></td></tr>
  <tr>
    <td>First Name</td>
    <td><form:input path="firstName" /></td>
  </tr>
  <tr>
    <td>Last Name</td>
    <td><form:input path="lastName" /></td>
  </tr>
  <tr>
    <td>Email</td>
    <td><form:input path="emailId" /></td>
  </tr>
  <tr>
    <td>Gender</td>
    <td>
    <form:radiobutton path="gender" value="male" />Male
    <form:radiobutton path="gender" value="female" />Female
    </td>
  </tr>
  <tr>
    <td>Role</td>
    <td>
    <form:select path="role" >
    <form:option value="">--select--</form:option>
    <form:option value="Admin">Admin</form:option>
    <form:option value="Case Worker">Case Worker</form:option>
    </form:select>
    </td>
  </tr>
  <tr>
    <td><input type="submit" value="CREATE"></td>
    <td><input type="reset" value="CLEAR"></td>
  </tr>
</table>

	</form:form>
</body>
</html>