package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.User;


@WebServlet("/LogIn")
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LogIn() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String loginType = request.getParameter("loginType");
		
		HttpSession session = request.getSession();
		User user = new User();
		
		String ownType = user.get(username, 3);
		String state = user.get(username, 12);
		
		if (state.equals("1")) {
			String e = "Sorry, you did not activate your account or you have been banned by the Administrator.";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
		} else {
			if (user.login(username, password) && loginType.equals(ownType)) {
				session.setAttribute("username", username);
				session.setAttribute("usertype", ownType);
				if (loginType.equals("c")) {
					request.getRequestDispatcher("customerMainPage.jsp").forward(request, response);
				}
				
				else if (loginType.equals("s")) {
					request.getRequestDispatcher("sellerMainPage.jsp").forward(request, response);
				}
				
				else if (loginType.equals("a")){
					request.getRequestDispatcher("adminMainPage.jsp").forward(request, response);
				}
			}
			
			else {
				String e = "Username, password and type are not mattched!";
				session.setAttribute("errMsg", e);
				request.getRequestDispatcher("Error.jsp").forward(request, response);
//				System.out.println(session.getAttribute("errMsg"));
			}
		}

	}

}
