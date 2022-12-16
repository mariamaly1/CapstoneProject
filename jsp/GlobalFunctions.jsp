<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
<script type="text/javascript">

function post(var email, var password){

	console.log("email == "+email);
	console.log("password == "+password);

	var http = new XMLHttpRequest();
	var url = 'http://localhost:8080/CapstoneProject/loginServlet';
	var params = '?email=omar@works@works&password=mahmoo@mahmoo@mahmoo';
	http.open('GET', url+params, true);

	//Send the proper header information along with the request
	http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

	http.onreadystatechange = function() {
	    if(http.readyState == 4 && http.status == 200) {
	        console.log(http.responseText);
	    }
	}
	http.send(params);
}
</script>
</body>
</html>