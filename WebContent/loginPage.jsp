<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.net.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script language="javascript">
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body bgcolor="gray">
<center>
<br/><br/><br/>
<font size=5>Login</font>
<br/><br/>
	<form action="LogIn" method="post">
		<table bgcolor="white" style="text-align:center;" >
			<tr>
				<td><select name="loginType">
						<option value="c">Customer</option>
						<option value="s">Seller</option>
						<option value="a">Admin</option>
				</select></td>
				<td>Username:</td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td></td>
				<td>Password:</td>
				<td><input type="password" name="password"></td>
			</tr>
				<td></td>
				<td>
					<button type="submit" name="action" value="login">Submit</button>
				</td>
				<td width="45%" colspan="1.5">
					<input type="button" name="action" value="Back" onclick="window.location.href='welcome.jsp';"/>
				</td>
		</table>
	</form>
	</center>

</body>
</html>