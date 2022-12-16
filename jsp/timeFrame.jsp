<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Page</title>
</head>
<script type="text/javascript">

	function checkUser(){
		
		if (localStorage.getItem("UserId") !== null && localStorage.getItem("UserId") !== "undefined"){
			getExistingTimeFrames();
			viewTimeStampHtml();
		} else {
			alert("You must to login first!")
			window.location.href = "http://localhost:8080/CapstoneProject/jsp/Login.jsp"
		}
	}
	
	function viewTimeStampHtml(){
		 
		var elem1 = document.createElement('label');
		elem1.innerHTML = "Increment / Appointment Duration in Minutes: ";    
		document.getElementsByTagName('body')[0].appendChild(elem1);
		
		
		var select = document.createElement("select");
		select.setAttribute("id","increment");
		document.body.appendChild(select);
		for (var i = 10; i<=100; i+=10){
		    var opt = document.createElement('option');
		    opt.value = i+"";
		    opt.innerHTML = i+"";
		    select.appendChild(opt);
		}
		
		var br = document.createElement("br");
	    document.body.append(br);
		
		var elem2 = document.createElement('label');
		elem2.innerHTML = "Name: ";    
		document.getElementsByTagName('body')[0].appendChild(elem2);
		
		var x = document.createElement("input");
		x.setAttribute("type", "text");
		x.setAttribute("id", "name");
		document.body.appendChild(x); 
		
		var br = document.createElement("br");
	    document.body.append(br);
	     
		var elem3 = document.createElement('label');
		elem3.innerHTML = "Description: ";    
		document.getElementsByTagName('body')[0].appendChild(elem3);
		
		var x = document.createElement("textarea");
		x.setAttribute("type", "text");
		x.setAttribute("id", "description");
		x.setAttribute("rows", "5");
		x.setAttribute("cols", "50");
		document.body.appendChild(x); 
		
		var br = document.createElement("br");
	    document.body.append(br);
	    
		var tag = document.createElement("p");
	    var text = document.createTextNode("Do you want visitors to be limited to 1 appointment per TimeFrame?: ");
	    document.body.appendChild(text);
	    
	    var select = document.createElement("select");
	    select.setAttribute("id","limitedChoice");
		document.body.appendChild(select);
		
	    var opt = document.createElement('option');
	    opt.value = "1";
	    opt.innerHTML = "Yes";
	    select.appendChild(opt);
	    
	    var opt = document.createElement('option');
	    opt.value = "0";
	    opt.innerHTML = "No";
	    select.appendChild(opt);
	    

	    var br = document.createElement("br");
	    document.body.append(br);
	    
	    var tag = document.createElement("p");
	    var text = document.createTextNode("Do you want this TimeFrame to be Private?: ");
	    document.body.appendChild(text);
	    
	    var select = document.createElement("select");
	    select.setAttribute("id","privateChoice");
		document.body.appendChild(select);
		
	    var opt = document.createElement('option');
	    opt.value = "1";
	    opt.innerHTML = "Yes";
	    select.appendChild(opt);
	    
	    var opt = document.createElement('option');
	    opt.value = "0";
	    opt.innerHTML = "No";
	    select.appendChild(opt);
	    
	    var br = document.createElement("br");
	    document.body.append(br);
	    
	    var tag = document.createElement("p");
	    var text = document.createTextNode("Access PIN (4 character string using letters or numbers");
	    document.body.appendChild(text);
	    
	    var x = document.createElement("input");
		x.setAttribute("type", "text");
		x.setAttribute("id", "accessPin");
		x.setAttribute("maxlength", "4");
		document.body.appendChild(x);
		
		var br = document.createElement("br");
		document.body.append(br);
		
		 var br = document.createElement("br");
		 document.body.append(br);
		    
		let btn = document.createElement("button");
		btn.innerHTML = "Save";
		
		btn.onclick = function () {
		
		var name = document.getElementById("name").value;
		var description = document.getElementById("description").value;
		var increment = document.getElementById("increment").value;
		var privateChoice = document.getElementById("privateChoice").value;
		var limitedChoice = document.getElementById("limitedChoice").value;
		var accessPin = document.getElementById("accessPin").value;
		var ownerId = localStorage.getItem("UserId");
		var http = new XMLHttpRequest();
		var url = "http://localhost:8080/CapstoneProject/timeFrameServlet";
		var params = "?increment="+increment;
		params +="&name="+name;
		params +="&description="+description;
		params +="&privateChoice="+privateChoice;
		params +="&limitedChoice="+limitedChoice;
		params +="&accessPin="+accessPin;
		params +="&ownerId="+ownerId;
		
		http.open('POST', url+params, true);

		//Send the proper header information along with the request
		http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

		http.onreadystatechange = function() {
		    if(http.readyState == 4 && http.status == 200) {
		    	window.location.href = "http://localhost:8080/CapstoneProject/jsp/timeFrame.jsp";
		    	alert(http.responseText);
				getExistingTimeFrames();
		    }
		}
		http.send(params);	 
		};
		document.body.appendChild(btn);
	}
	
	function getExistingTimeFrames() {
		
		var http = new XMLHttpRequest();
		var url = "http://localhost:8080/CapstoneProject/timeFrameServlet";
		var params = "?OwnerId="+localStorage.getItem("UserId");
		
		http.open('GET', url+params, true);

		//Send the proper header information along with the request
		http.setRequestHeader('Content-type', 'application/json');

		http.onreadystatechange = function() {
		    if(http.readyState == 4 && http.status == 200) {
		    	const response = (JSON.parse(http.responseText));
		        
		        var br = document.createElement("br");
			    document.body.append(br);
			    var br = document.createElement("br");
			    document.body.append(br);
			    var br = document.createElement("br");
			    document.body.append(br);
				var tag = document.createElement("p");
			    var text = document.createTextNode("Here are your existing timeframes");
			    document.body.appendChild(text);
		        
		        const body = document.body,
		        tbl = document.createElement('table');
				  tbl.style.width = '500px';
				  tbl.style.border = '1px solid black';
				  const initialRow = tbl.insertRow();
				  
				    const td1_initial = initialRow.insertCell();
			        td1_initial.appendChild(document.createTextNode("Name"));
			        td1_initial.style.border = '1px solid black';
			        
			        const td2_initial = initialRow.insertCell();
			        td2_initial.appendChild(document.createTextNode("Description"));
			        td2_initial.style.border = '1px solid black';
			        
			        const td3_initial = initialRow.insertCell();
			        td3_initial.appendChild(document.createTextNode("Appointment Duration"));
			        td3_initial.style.border = '1px solid black';
			        
			        const td4_initial = initialRow.insertCell();
			        td4_initial.appendChild(document.createTextNode("Limited"));
			        td4_initial.style.border = '1px solid black';
			        
			        const td5_initial = initialRow.insertCell();
			        td5_initial.appendChild(document.createTextNode("Private TimeFrame"));
			        td5_initial.style.border = '1px solid black';
			        
			        const td6_initial = initialRow.insertCell();
			        td6_initial.appendChild(document.createTextNode("AccessPin"));
			        td6_initial.style.border = '1px solid black';
				  
		  for (let i = 0; i < response.length; i++) {
		    const tr = tbl.insertRow();
		        const td1 = tr.insertCell();
		        td1.appendChild(document.createTextNode(response[i].Name));
		        td1.style.border = '1px solid black';
		        
		        const td2 = tr.insertCell();
		        td2.appendChild(document.createTextNode(response[i].Description));
		        td2.style.border = '1px solid black';
		        
		        const td3 = tr.insertCell();
		        td3.appendChild(document.createTextNode(response[i].Increment));
		        td3.style.border = '1px solid black';
		        
		        const td4 = tr.insertCell();
		        td4.appendChild(document.createTextNode(response[i].oneSlot === "1" ? "Yes":"No"));
		        td4.style.border = '1px solid black';
		        
		        const td5 = tr.insertCell();
		        td5.appendChild(document.createTextNode(response[i].IsPrivate === "1" ? "Yes":"No"));
		        td5.style.border = '1px solid black';
		        
		        const td6 = tr.insertCell();
		        td6.appendChild(document.createTextNode(response[i].AccessPin));
		        td6.style.border = '1px solid black';

		  }
		  body.appendChild(tbl);
		    }
		}
		http.send(params);
	}
</script>
<body onload="checkUser()">
	<h1 id="welcomeMessage"></h1>

</body>
</html>