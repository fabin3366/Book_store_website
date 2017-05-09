package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.*;


/**
 * Servlet implementation class itemBought
 */
@WebServlet("/itemBought")
public class itemBought extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public itemBought() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = new User();
		Itemes item = new Itemes();
		String username = "fff";
//		String username = (String) session.getAttribute("username");
		String buyer_id = (String) user.get(username, 1);
	}

}
