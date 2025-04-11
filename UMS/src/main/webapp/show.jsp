<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List"  %>
<%@ page import = "org.cms.MODEL.Student" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All students</title>
</head>
<body>
<%  
try {
HttpSession oldsession=request.getSession(false);
List<Student> fetchedStudents=(List<Student>)oldsession.getAttribute("xyz");
// project in a html table 

out.println(fetchedStudents);
}catch(Exception e){}
%>
</body>
</html>