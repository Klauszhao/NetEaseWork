<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>Student Information</h2>
<form:form method="POST" modelAttribute="ftName" action="/SSM_Test/addStudent_zn">
   <table>
    <tr>
        <td><form:label path="firstName">FirstName</form:label></td>
        <td><form:input path="firstName" /></td>
    </tr>
    <tr>
        <td><form:label path="lastName">LastName</form:label></td>
        <td><form:input path="lastName" /></td>
    </tr>
    <tr>
        <td><form:label path="salary">Salary</form:label></td>
        <td><form:input path="salary" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</body>
</html>