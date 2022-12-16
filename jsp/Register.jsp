<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Page</title>
<script>

	function Register() {
	
		var elem1 = document.createElement('label');
		elem1.innerHTML = "First Name: ";    
		document.getElementsByTagName('body')[0].appendChild(elem1);
		
		var input1 = document.createElement("input");
		input1.setAttribute("type", "text");
		input1.setAttribute("id", "firstName");
		document.body.appendChild(input1); 
		
		var br1 = document.createElement("br");
		document.body.append(br1);
		
		var br2 = document.createElement("br");
		document.body.append(br2);
		
		var elem1_lastName = document.createElement('label');
		elem1_lastName.innerHTML = "Last Name: ";    
		document.getElementsByTagName('body')[0].appendChild(elem1_lastName);
		
		var input1_lastName = document.createElement("input");
		input1_lastName.setAttribute("type", "text");
		input1_lastName.setAttribute("id", "lastName");
		document.body.appendChild(input1_lastName); 
		
		var br1 = document.createElement("br");
		document.body.append(br1);
		
		var br2 = document.createElement("br");
		document.body.append(br2);
		
		var elem2 = document.createElement('label');
		elem2.innerHTML = "Email: ";    
		document.getElementsByTagName('body')[0].appendChild(elem2);
		
		var input2 = document.createElement("input");
		input2.setAttribute("type", "email");
		input2.setAttribute("id", "email");
		document.body.appendChild(input2); 
		
		var br3 = document.createElement("br");
		document.body.append(br3);
		
		var br4 = document.createElement("br");
		document.body.append(br4);
		
		var elem3 = document.createElement('label');
		elem3.innerHTML = "Password: ";    
		document.getElementsByTagName('body')[0].appendChild(elem3);
		
		var input3 = document.createElement("input");
		input3.setAttribute("type", "password");
		input3.setAttribute("id", "password");
		document.body.appendChild(input3); 
		
		var br3 = document.createElement("br");
		document.body.append(br3);
		
		var br4 = document.createElement("br");
		document.body.append(br4);
		
		let btn = document.createElement("button");
		btn.innerHTML = "Register";
		
		btn.onclick = function () {
		
		var firstName = document.getElementById("firstName").value;
		var lastName = document.getElementById("lastName").value;
		var email = document.getElementById("email").value;
		var password = document.getElementById("password").value;
		var http = new XMLHttpRequest();
		var url = "http://localhost:8080/CapstoneProject/registerServlet";
		var params = "?firstName="+firstName;
		params +="&lastName="+lastName;
		params +="&email="+email;
		params +="&password="+password;
				
		http.open('POST', url+params, true);

		//Send the proper header information along with the request
		http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

		http.onreadystatechange = function() {
		    if(http.readyState == 4 && http.status == 200) {
		    	alert(http.responseText);
		    	if (http.responseText === "Entry has been successfully written into the database, please login to continue"){
		    		window.location.href = "http://localhost:8080/CapstoneProject/jsp/Login.jsp";
		    	}
		    }
		}
		http.send(params);	 
		};
		document.body.appendChild(btn);
	
	}
	
</script>
</head>
<body onload="Register()">


<h1>Registration Page</h1>
</body>
</html>