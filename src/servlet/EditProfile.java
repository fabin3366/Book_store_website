package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.User;

/**
 * Servlet implementation class EditProfile
 */
@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfile() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		User user = new User();
		
		if (!request.getParameter("email").equals("")) {
			String info = request.getParameter("email");
			user.set(username, info, 4);
		}
		
		if (!request.getParameter("fname").equals("")) {
			String info = request.getParameter("fname");
			user.set(username, info, 2);
		}
		
		if (!request.getParameter("lname").equals("")) {
			String info = request.getParameter("lname");
			user.set(username, info, 14);
		}
		
		if (!request.getParameter("credit").equals("")) {
			String info = request.getParameter("credit");
			user.set(username, info, 13);
		}

		if (!request.getParameter("dob").equals("")) {
			String info = request.getParameter("dob");
			user.set(username, info, 5);
		}
		
		if (!request.getParameter("mob").equals("")) {
			String info = request.getParameter("mob");
			user.set(username, info, 10);
		}
		
		if (!request.getParameter("streetaddress").equals("")) {
			String info = request.getParameter("streetaddress");
			user.set(username, info, 6);
		}
		
		if (!request.getParameter("city").equals("")) {
			String info = request.getParameter("city");
			user.set(username, info, 7);
		}
		
		if (!request.getParameter("country").equals("")) {
			String info = request.getParameter("country");
			user.set(username, info, 8);
		}
		
		if (!request.getParameter("postcode").equals("")) {
			String info = request.getParameter("postcode");
			user.set(username, info, 9);
		}
		
		request.getRequestDispatcher("EditSuccess.jsp").forward(request, response);
	}

}
