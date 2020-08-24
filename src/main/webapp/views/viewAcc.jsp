<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<style>
   td,tr {
	        text-align: center;
        }
    
</style>
<meta charset="ISO-8859-1">
<title>My Contact lists</title>
    <link rel="stylesheet" type="text/css" href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.0/css/jquery.dataTables.css">
    <link rel="stylesheet" type="text/css" href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.0/css/jquery.dataTables_themeroller.css">
    <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.0/jquery.dataTables.min.js"></script>
   <script type="text/javascript">
      $(document).ready( function () {
                 $('#hisTab').DataTable();
           } );
      jQuery(function() {
    	    jQuery('#role').change(function() {
    	        this.form.submit();
    	    });
    	});
      
 </script>
</head>
<body>
    <h2 style="color:orange;text-align: center">....HIS ADMIN....</h2>
    <div align="center">
   <form:form action="view" method="GET" modelAttribute="adminAcc">
    Select Role:: <form:select path="role">
                                   <option value="">--select--</option>
                                   <option value="Admin">Admin</option>
                                   <option value="Case Worker">Case Worker</option>
                             </form:select> 
</form:form>
</div>
  
    <hr size="5px" noshade="noshade">
	<c:choose>
		<c:when test="${!empty adminList }">
		 <table  border="1" id="hisTab" align="center" cellspacing="0">
	    <thead>
		             <tr>
			                                <th>S.NO</th>
			                                <th>FIRST NAME</th>
			                                <th>LAST NAME</th>
			                                <th>EMAIL ID</td>
			                                <th>GENDER</th>
			                                <th>ROLE</th>
			                                <th>LOCKED STATUS</th>
			                                <th >ACTIONS</th>
		               </tr>
	    </thead>	
	    <tbody>
            <c:forEach items="${adminList}" var="admin" varStatus="index">
           
                          <tr>
                                          <td>${index.count }</td>
                                          <td>${admin.firstName}</td>
                                          <td>${admin.lastName}</td>
                                          <td>${admin.emailId}</td>
                                          <td>${admin.gender}</td>
                                          <td>${admin.role}</td>
                                          <td>${admin.lockedStatus}</td>
                                          <td>
                                          <a href="${pageContext.request.contextPath}/editAcc?id=${admin.accountId }">EDIT</a> &nbsp;&nbsp;&nbsp;
                                          <c:if test="${ 'N' eq admin.getDeleteSwitch() }">
                                          <a href="${pageContext.request.contextPath}/deleteAcc?id=${admin.accountId }" onclick="return confirm('Are you sure, You want to delete this account ?')">DELETE</a>
                                          </c:if>
                                          <c:if test="${  'Y' eq admin.getDeleteSwitch() }">
                                          <a href="${pageContext.request.contextPath}/activateAcc?id=${admin.accountId }" onclick="return confirm('Are you sure, You want to activate this account ?')">ACTIVATE</a>
                                          </c:if>
                                          </td>
                          </tr>
           
            </c:forEach>
            </tbody>
         </table>
		</c:when>
		<c:otherwise>
			<h2 style="color: red; text-align: center">No Admin or Case Worker Selected...!</h2>
		</c:otherwise>
	</c:choose>
</body>
</html>