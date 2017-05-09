<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Congratulations</title>
</head>
<body>
			<% String url=request.getParameter("value");
			String msg;
			String msg2;
			 String username=request.getParameter("u");
			boolean c= new DAO.User().checkUrl( url);
			msg2= new DAO.User().getByL(url);
			if(c){ msg="Congratulations! You have registered in our bookstore successfully! Please return to the main page to log in.";}
			else{ msg="Sorry, there is someting wrong.";}
			%>
			</br> </br> </br> </br> </br> </br>
		<tr><td>
		
		<center><h1><%=msg %></h1><hr></center>

		<center><table>
		</td></tr>
		</br> </br> </br> </br>
		
				<tr><td>
		<input type="button" style="width: 150px; height: 30px" name="back"
								value="Main Page" onclick="window.location.href='welcome.jsp';"/>
			</td></tr>
			</table></center>

</body>
</html>