<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%	
	String pictureURL = (String)session.getAttribute("pictureURL");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add an item</title>
<%@ include file="sellerHeader.html"%>
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
ul#navlist {
	margin: 0 0 0 .1px;
	padding: 0;
	width: 300px;
}

#navlist li {
	list-style-type: none;
	background-color: #000;
	color: #ffffff;
	border: .01em solid #ffffff;
	font-weight: 600;
	text-align: center;
	padding: .5em;
	margin-bottom: .01em;
}

#navlist li a {
	color: #ffffff;
	text-decoration: none;
	display: block;
}

#navlist li a:hover {
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
	<center><table>
		<td>
		
	<form action='addItems' method='POST'>
		<center>
			<table align="center" width="600px">

				<tr align="center">
					<td colspan="4"><h1>Your item for sale</h1></td>
				</tr>
				<tr align="center">
					<td colspan="4">please add information of your item below</td>
				</tr>
				<tr align="center">
					<td	colspan="2"><font size=3; color=red>* means this blank must be filled.</font></td>
				</tr>
				<tr align="left">
					<td width="10%"><font color=red size=2>*</font>Title:</td>
					<td width="35%"><input type="text" name="title"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%"><font color=red size=2>*</font>Author:</td>
					<td width="35%"><input type="text" name="author"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">Publisher:</td>
					<td width="35%"><input type="text" name="publisher"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%"><font color=red size=2>*</font>Year:</td>
					<td width="35%"><input type="text" name="year"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">ISBN:</td>
					<td width="35%"><input type="text" name="isbn"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">EE:</td>
					<td width="35%"><input type="text" name="ee"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">Refernece:</td>
					<td width="35%"><input type="text" name="reference"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%">Pages:</td>
					<td width="35%"><input type="text" name="page"
						style="width: 100%"></td>
				</tr>
				<tr>
					<td width="10%"><font color=red size=2>*</font>Price:</td>
					<td width="35%"><input type="text" name="price"
						style="width: 100%"></td>
				</tr>
			</table>
		</center>
		<center>
			<table>

				<tr align="center">
					<td width="30%" colspan="1.5">
					<button type="submit" name="action" value="itemsubmit">Submit</button>
					</td>
					<td width="45%" colspan="1.5">
					<button type="reset" name="action" value="itemreset">Reset</button>
					</td>
					<td width="45%" colspan="1.5">
					<input type="button" name="action" value="Back" onclick="window.location.href='sellerMainPage.jsp';"/>
					</td>
				</tr>
				</table></center>
		</form>		
			<td valign="top" align="right" width="25%">
			<img style="width:210px;height:300px" 
			<%if (pictureURL == null){%>src=""<%}else{%>src="<%=pictureURL%>"<%} %>/>
			</td>
		</tr>
	</table>
		
</body>
</html>