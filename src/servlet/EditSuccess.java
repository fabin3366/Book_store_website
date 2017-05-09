package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EditSuccess
 */
@WebServlet("/EditSuccess")
public class EditSuccess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditSuccess() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String ownType = (String) session.getAttribute("usertype");
		if (ownType.equals("c")) {
			request.getRequestDispatcher("customerMainPage.jsp").forward(request, response);
		}
		else if (ownType.equals("s")) {
			request.getRequestDispatcher("sellerMainPage.jsp").forward(request, response);
		}
	}

}
