package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.User;


@WebServlet("/passwordChange")
public class passwordChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public passwordChange() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = new User();
		String username = (String) session.getAttribute("username");
		String passfromdb = user.get(username, 11);
		String oldpass = request.getParameter("oldpassword");
		String newpass = request.getParameter("newpassword");
		String confirmNew = request.getParameter("confirmnewpassword");
		if (!oldpass.equals(passfromdb)) {
			String e = "The password is incorrect!";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}
		
		if (newpass.equals("")) {
			String e = "New Password can not be empty!";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}
		
		if (newpass.contains(" ")) {
			String e = "New Password can not contain space characters!";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}
		if (confirmNew.equals("")) {
			String e = "Please confirm your password!";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}

		if (!newpass.equals(confirmNew)) {
			String e = "The confirmed password should be the same as the new password";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}
		
		user.set(username, newpass, 11);
		request.getRequestDispatcher("EditSuccess.jsp").forward(request, response);
	}
}
