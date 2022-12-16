<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Page</title>
 <script type="text/javascript">
	function test(){
		var email = document.getElementById("email").value;
		var password = document.getElementById("password").value;
	 	post(email, password);
	}
	
	 function post(email, password){
	
		var http = new XMLHttpRequest();
		var url = "http://localhost:8080/CapstoneProject/loginServlet";
		var params = "?email="+email+"&password="+password;
		http.open('GET', url+params, true);

		//Send the proper header information along with the request
		http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

		http.onreadystatechange = function() {
		    if(http.readyState == 4 && http.status == 200) {
		    	const myArray = http.responseText.split(":");
		        alert(myArray[0]);
		        if ("INVALID LOGIN, PLEASE TRY AGAIN." !== http.responseText){
		        	localStorage.setItem("UserId", myArray[1]);
		        	window.location.href = "http://localhost:8080/CapstoneProject/jsp/timeFrame.jsp"
		        }
		    }
		}
		http.send(params);
	}
	
</script>
</head>
<body>

<h1>Login Page</h1>

Email: <input type="email" name="email" id="email">
  <br> <br> 
  
  Password: <input type="password" name="password" id="password"><br>

  <br> 
  
 <button onclick="test()">Login</button>
</body>
</html>
 