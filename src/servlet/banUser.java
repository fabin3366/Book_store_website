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
 * Servlet implementation class banUser
 */
@WebServlet("/banUser")
public class banUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public banUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = new User();
		HttpSession session = request.getSession();
		String ban = request.getParameter("ban");
		String searchname = (String) session.getAttribute("searchname");
		if (ban.equals("yes")) {
			user.set(searchname, "1", 12);
			System.out.println(searchname +" is banned by the Admin");
			request.getRequestDispatcher("adminMainPage.jsp").forward(request, response);
		}
	}

}
