package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.*;
import Util.*;


@WebServlet("/addItems")
public class addItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addItems() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Itemes items = new Itemes();
		User user = new User();
		String username = (String) session.getAttribute("username");
		
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String StrYear = request.getParameter("year");
		String publisher = request.getParameter("publisher");
		String isbn = request.getParameter("isbn");
		String ee = request.getParameter("ee");
		String reference = request.getParameter("reference");
		String page = request.getParameter("page");
		String StrPrice = request.getParameter("price");
		
		GetDateTime date = new GetDateTime();
		String offered = date.getCurTime();
	
		if (title.equals("")) {
			String e = "Sorry title can not be empty!";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}

		if (author.equals("")) {
			String e = "Sorry author can not be empty!";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}
		
		if (StrPrice.equals("")){
			String e = "Please enter a price.";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		} 
		
		if (StrYear.equals("")){
			String e = "Please enter the year.";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		} 
		
		
		
		if (!Utils.isPrice(StrPrice)) {
			String e = "Sorry! The input of price is wrong.";
			session.setAttribute("errMsg", e);
			request.getRequestDispatcher("Error.jsp").forward(request, response);
			return;
		}
		
		if (publisher.equals("")) {
			publisher = "None";
		}
		
		if (isbn.equals("")) {
			isbn = "None";
		}
		
		if (ee.equals("")) {
			ee = "None";
		}
		
		if (reference.equals("")) {
			reference = "None";
		}
		
		if (page.equals("")) {
			page = "None";
		}
		
		System.out.println(offered);
		int year = Integer.parseInt(StrYear);
		int price = Integer.parseInt(StrPrice);
		int sellerId = user.getId(username);

		
		session.setAttribute("errMsg", null);
		if (session.getAttribute("errMsg")==null) {
			items.addItems(title, author, publisher, year, isbn, ee, reference, page, offered, sellerId, price);
			request.getRequestDispatcher("addSuccess.jsp").forward(request, response);
			return;
		}
	}

}
