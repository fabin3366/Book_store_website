<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Password Change</title>
</head>
<body>
	<center><h1>Password Change</h1><hr></center>
	<form action="passwordChange" method="post">
		<center>
			<table>
				<tr>
					<td width="10%">Old Password:</td>
					<td width="35%"><input type="password" name="oldpassword"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">New Password:</td>
					<td width="35%"><input type="password" name="newpassword"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">Confirm New Password:</td>
					<td width="35%"><input type="password" name="confirmnewpassword"
						style="width: 100%"></td>
				</tr>
				<tr align="center">
					<td width="30%" colspan="1.5">
					<button type="submit" name="action" value="passwordsubmit">Submit</button>
					</td>
					<td width="45%" colspan="1.5">
					<button type="reset" name="action" value="passwordreset">Reset</button>
					</td>
					<td width="45%" colspan="1.5">
					<input type="button" name="action" value="Back" onclick="javascript:history.go(-1);"/>
					</td>
				</tr>
			</table>
		</center>
	</form>
</body>
</html>