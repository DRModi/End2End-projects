<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Location</title>
</head>
<body>

	<form action="updateLocation" method="post">
	<pre>
		Id: <input type="text" name="id" value="${retrievedLocation.id}" readonly="readonly"/>
		Code: <input type="text" name="code" value="${retrievedLocation.code}"/>
		Name: <input type="text" name="name" value="${retrievedLocation.name}"/>
		Type: Urban <input type="radio" name="type" value="URBAN" ${retrievedLocation.type=='URBAN'?'checked':''}/> 
			  Rural <input type="radio" name="type" value="RURAL" ${retrievedLocation.type=='RURAL'?'checked':''}/>
			  
		<input type="submit" value="save"/>
	</pre>
	</form>
</body>
</html>