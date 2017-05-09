<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="java.util.*"%>
<%@ page import="bean.*"%>
<%@ page import="DAO.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Profile</title>
<%@ include file="customerHeader.html"%>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function() {
		$(".bodys p").not(":first").hide();
		$(".searchbox ul li").mouseover(function() {
			var index = $(this).index();
			if (index == 0) {
				$(this).find("a").addClass("style1");
				$(".searchbox ul li").eq(1).find("a").removeClass("style1");
				$(".searchbox ul li").eq(2).find("a").removeClass("style1");
				$(".searchbox ul li").eq(3).find("a").removeClass("style1");
				$(".searchbox ul li").eq(4).find("a").removeClass("style1");
			}
			if (index == 1) {
				$(this).find("a").addClass("style1");
				$(".searchbox ul li").eq(0).find("a").removeClass("style1");
				$(".searchbox ul li").eq(2).find("a").removeClass("style1");
				$(".searchbox ul li").eq(3).find("a").removeClass("style1");
				$(".searchbox ul li").eq(4).find("a").removeClass("style1");
			}
			if (index == 2) {

				$(this).find("a").addClass("style1");
				$(".searchbox ul li").eq(0).find("a").removeClass("style1");
				$(".searchbox ul li").eq(1).find("a").removeClass("style1");
				$(".searchbox ul li").eq(3).find("a").removeClass("style1");
				$(".searchbox ul li").eq(4).find("a").removeClass("style1");
			}
			if (index == 3) {

				$(this).find("a").addClass("style1");
				$(".searchbox ul li").eq(0).find("a").removeClass("style1");
				$(".searchbox ul li").eq(1).find("a").removeClass("style1");
				$(".searchbox ul li").eq(2).find("a").removeClass("style1");
				$(".searchbox ul li").eq(4).find("a").removeClass("style1");
			}
			if (index == 4) {
				$(this).find("a").addClass("style1");
				$(".searchbox ul li").eq(1).find("a").removeClass("style1");
				$(".searchbox ul li").eq(2).find("a").removeClass("style1");
				$(".searchbox ul li").eq(3).find("a").removeClass("style1");
				$(".searchbox ul li").eq(0).find("a").removeClass("style1");
			}
			var index = $(this).index();
			$(".bodys p").eq(index).show().siblings().hide();
		});
	});
</script>

<style type="text/css">
* {
	margin: 0;
	padding: 0;
	list-style-type: none;
}

a, img {
	border: 0;
}

/* searchbox */
ul#navlist{
	margin: 0 0 0 .1px;
	padding: 0;
	width: 300px;
	}
#navlist li{
	list-style-type: none;
	background-color: #000;
	color: #ffffff;
	border: .01em solid #ffffff;
	font-weight: 600;
	text-align: center;
	padding: .5em;
	margin-bottom: .01em;
	}
#navlist li a{
	color: #ffffff;
	text-decoration: none;
	display: block;
	}
#navlist li a:hover{
	padding: .1em;
	background-color: #faebd7;
	color: #191970;
	}

.bodys input {
	height: 30px;
	line-height: 30px;
	width: 600px;
	padding: 0 10px;
	float: left;
}

.bodys .one {
	border: #000 3px solid
}

.bodys .one1 {
	background-color: #000;
}

.bodys button {
	float: left;
	border: 0;
	height: 36px;
	width: 100px;
	color: #FFF;
	line-height: 36px;
	text-align: center;
	overflow: hidden;
}
</style>
</head>
 <body>
	<table>
	<form action='myProfile' method='POST'>
<%
	User user = new User();
	String username = (String) session.getAttribute("username");
%>
	</form>
				<table align="center" style="text-align: center;" width="80%" border="0">
					<%
						if (username != null) {
					%>
					<br>
					<br>
					<br>
					<br>
					<tr>
						<td align="center"><label>UserName</label></td>
						<td><%=username%></td>
						
					</tr>
					<tr>
						<td align="center"><label>First Name</label></td>
						<td><%=user.get(username, 2)%></td>
					</tr>
					<tr>
						<td align="center"><label>Last Name</label></td>
						<td><%=user.get(username, 14)%></td>
					</tr>
					<tr>
						<td align="center"><label>Email</label></td>
						<td><%=user.get(username, 4)%></td>
					</tr>
					<tr>
						<td align="center"><label>Credit Card Number</label></td>
						<td><%=user.get(username, 13)%></td>
					</tr>
					<tr>
						<td align="center"><label>Mobile</label></td>
						<td><%=user.get(username, 10)%></td>
					</tr>
					<tr>
						<td align="center"><label>Year of Birth</label></td>
						<td><%=user.get(username, 5)%></td>
					</tr>
					<tr>
						<td align="center"><label>Street Address</label></td>
						<td><%=user.get(username, 6)%></td>
					</tr>
					<tr>
						<td align="center"><label>City</label></td>
						<td><%=user.get(username, 7)%></td>
					</tr>
					<tr>
						<td align="center"><label>Country</label></td>
						<td><%=user.get(username, 8)%></td>
					</tr>
					<tr>
						<td align="center"><label>Postcode</label></td>
						<td><%=user.get(username, 9)%></td>
					</tr>
					<%
						}
					%>
		</table>
					<br>
					<br>
			<center><table>
			<button style="width: 150px; height: 30px" onclick="window.location.href='EditProfile.jsp';"/>Edit My Profile</button>
	</table></center>
	</td>
	</tr>
	</table>
</body>
</html> 