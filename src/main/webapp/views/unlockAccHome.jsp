<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="./js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="./js/unlock.js"></script>
</head>
<body>
	<h2 style="color: orange; text-align: center">Unlock Account</h2><br>
	<h2 style="color: red; text-align: center"> ${errorMsg} </h2>
	<form:form action="unlockAcc" method="POST" modelAttribute="unlockAccount">
		<table align="center" bgcolor="LightGray">
			<tr>
				<td>Email ID</td>
				<td><form:input path="emailId" readonly="true"/></td>
			</tr>
			<tr>
				<td>Temporary password</td>
				<td><form:password path="tempPazzword" /></td>
			</tr>
			<tr>
				<td>Choose new password</td>
				<td><form:password path="newPazzword" /></td>
			</tr>
			<tr>
				<td>Confirm password</td>
				<td><form:password path="confirmPazzword" /></td>
			</tr>
			<tr>
			<td></td>
			<td><span id="confirmPwdErr"></span></td>
			</tr>
			<tr>
			    <td></td>
				<td>
				<input type="reset" value="CLEAR"> &nbsp;
				<input type="submit" value="UNLOCK" id="unlockBtn">
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>