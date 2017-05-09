package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.MailSender;
import Util.Utils;
import DAO.User;

@WebServlet("/Regestion")
public class Regestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Regestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		User user = new User();
		HttpSession session = request.getSession();
		
		String username = request.getParameter("userName");
		String password = request.getParameter("passWord");
		String ConPassword = request.getParameter("ConfirmPassWord");
		String email = request.getParameter("email");
		String fname = request.getParameter("firstName"); // get the family name
		String lname = request.getParameter("lastName"); // get the last name
		String dob = request.getParameter("birthYear");
		String add = request.getParameter("streetAddress");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		String postcode = request.getParameter("postCode");
		char type = 'c';
		
		String StrCredit = request.getParameter("creditCardNo");
		String StrMob = request.getParameter("mob");
		
		if (dob.equals("")) {
			dob = "None";
		}
		
		if (add.equals("")) {
			add = "None";
		}
		
		if (city.equals("")) {
			city = "None";
		}
		
		if (country.equals("")) {
			country = "None";
		}
		
		if (postcode.equals("")) {
			postcode = "None";
		}
		
		if (username.equals("")) {
			String e = "UserName can not be empty!";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}

		if (username.contains(" ")) {
			String e = "Username can not contain space characters!";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}

		if (user.isUsername(username)) {
			String e = "This username is already existed!";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}

		if (password.equals("")) {
			String e = "Password can not be empty!";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}
		
		if (password.contains(" ")) {
			String e = "Password can not contain space characters!";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}
		if (ConPassword.equals("")) {
			String e = "Please confirm your password!";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}

		if (!password.equals(ConPassword)) {
			String e = "The confirmed password should be the same as the password";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}
		
		if (email.equals("")) {
			String e = "Email can not be empty!";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}
		
		if (!Utils.isEmail(email)) {
			String e = "Wrong format of Email!";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}
		

		if (!fname.equals("") && !Utils.isAlphabetic(request.getParameter("firstName"))) {
			String e = "FirstName can only contain letters";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}
		
		if (!lname.equals("") && !Utils.isAlphabetic(request.getParameter("lastName"))) {
			String e = "LastName can only contain letters";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}
		
		if (StrCredit.equals("")) {
			String e = "Credit Card No. can not be empty!";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}
		
		if (!Utils.isCreditCardNum(StrCredit)) {
			String e = "Credit Card Number must be 16 digits.";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}
		
		if (dob.equals("")) {
			String e = "Birth year can not be empty!";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}
		
		if (!Utils.isBirthYear(dob)) {
			String e = "There is the wrong format of Birth year.";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}
		
		if (StrMob.equals("")) {
			String e = "Mobile phone can not be empty!";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}
				
		if (!Utils.isMobile(StrMob)) {
			String e = "Mobile phone Number must be 10 digits.";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}

		if (request.getParameter("identity").equals("CustomerRegist")) {
			type = 'c';
		}
		
		else if (request.getParameter("identity").equals("SellerRegist")) {
			type = 's';
		}
		
		BigDecimal credit = new BigDecimal(request.getParameter("creditCardNo"));
		BigDecimal mob = new BigDecimal(request.getParameter("mob"));
		
		session.setAttribute("errMsg", null);
		if (session.getAttribute("errMsg")==null) {
			user.newUser(fname, lname, username, type, email, dob, add, city, country, postcode, credit, password, mob);
			request.getRequestDispatcher("RegistConfirm.jsp").forward(request, response);
			if (type == 'c') {
				CustomerEmailSender customeremailsender = new CustomerEmailSender(email ,username);
				customeremailsender.start();
			}
			else {
				SellerEmailSender selleremailsender = new SellerEmailSender(email ,username);
				selleremailsender.start();
			}
		}
	}
}

class SellerEmailSender extends Thread {
	private String email;
	private String username;
	public SellerEmailSender(String email ,String username) {
		this.email = email;
		this.username=username;
	}
	public void run() {
		try {
			Random ran = new Random();
			 int randomInt = ran.nextInt(100);
			 String patch ="";
			 for(int i=0; i<150;i++)
			 {
				 randomInt = ran.nextInt(100);
				 patch=patch+ randomInt;
			 }
			 new User().setUrl( username ,patch);
			String subject = "Registration Successful";
			String url = "http://localhost:8080/comp9321_ass2/RegistComfirmation.jsp?value="+patch+"&u="+username;
			String message = "Congratulations! You have registered in our bookstore successfully!\n"
					+ "Please click the following url to get access our bookstore.\n"
					+ url + "\n";

			System.out.println(email);
			MailSender.sendTextMail("comp9321111@gmail.com", "comp9321111", "comp9321", email, subject, message);
			
		} catch (Exception e) {}
	}
}

class CustomerEmailSender extends Thread {
	private String email;
	private String username;
	public CustomerEmailSender(String email ,String username) {
		this.email = email;
		this.username=username;
	}
	public void run() {
		try {
			Random ran = new Random();
			 int randomInt = ran.nextInt(100);
			 String patch ="";
			 for(int i=0; i<150;i++)
			 {
				 randomInt = ran.nextInt(100);
				 patch=patch+ randomInt;
			 }
			 new User().setUrl( username ,patch);
			String subject = "Registration Successful";
			String url = "http://localhost:8080/comp9321_ass2/RegistComfirmation.jsp?value="+patch+"&u="+username;
			String message = "Congratulations! You have registered in our bookstore successfully!\n"
					+ "Please click the following url to get access our bookstore.\n"
					+ url + "\n";

			System.out.println(email);
			MailSender.sendTextMail("comp9321111@gmail.com", "comp9321111", "comp9321", email, subject, message);
			
		} catch (Exception e) {}
	}
}