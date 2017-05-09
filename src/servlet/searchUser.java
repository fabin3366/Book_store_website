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
 * Servlet implementation class searchUser
 */
@WebServlet("/searchUser")
public class searchUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String searchname = request.getParameter("searchContent");
		User user = new User();
		if (!user.isUsername(searchname)) {
			String e = "No such username, check it again!";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}
		if (user.get(searchname, 3).equals("c")) {
			session.setAttribute("identity", "Customer");
		} else if (user.get(searchname, 3).equals("s")) {
			session.setAttribute("identity", "Seller");
		}
		session.setAttribute("searchname", searchname);
		System.out.println(searchname);
		request.getRequestDispatcher("adminSearchProfile.jsp").forward(request, response);
	}

}
