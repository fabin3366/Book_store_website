<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Profile</title>
</head>
<body>
	<center><h1>Edit Your Profile</h1><hr></center>
	<form action="EditProfile" method="post">
		<center>
			<table>
				<tr>
					<td colspan="4"><h3>Personal Informations</h3></td>
				</tr>
				<tr>
					<td width="10%">Email:</td>
					<td width="35%"><input type="text" name="email"
					style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">FirstName:</td>
					<td width="35%"><input type="text" name="fname"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">LastName:</td>
					<td width="35%"><input type="text" name="lname"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">Credit Card Number:</td>
					<td width="35%"><input type="text" name="credit"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">Year of Birth:</td>
					<td width="35%"><input type="text" name="dob"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">Mobile Phone:</td>
					<td width="35%"><input type="text" name="mob"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td colspan="4"><h3>Post Informations</h3></td>
				</tr>
				<tr>
					<td width="10%">StreetAddress:</td>
					<td width="35%"><input type="text" name="streetaddress"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">City:</td>
					<td width="35%"><input type="text" name="city"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">Country:</td>
					<td width="35%"><input type="text" name="country"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">PostCode:</td>
					<td width="35%"><input type="text" name="postcode"
						style="width: 100%"></td>
				</tr>

				
				<tr align="center">
					<td width="30%" colspan="1.5">
					<button type="submit" name="action" value="editsubmit">Submit</button>
					</td>
					<td width="45%" colspan="1.5">
					<button type="reset" name="action" value="editreset">Reset</button>
					</td>
				</tr>
			</table>
		</center>
	</form>
	<center> <a href="changepassword.jsp" >Change Password</a></center>
</body>
</html>