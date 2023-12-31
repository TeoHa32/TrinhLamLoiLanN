package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.product;
import model.productDAO;

/**
 * Servlet implementation class searchServlet
 */
//@WebServlet("/searchServlet")
@WebServlet(urlPatterns = {
		"/searchServlet",
		"/timkiemnangcao"		
})
public class searchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* response.setContentType("text/html;charset=UTF-8"); */
		String uri = request.getRequestURI();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if(uri.contains("searchServlet")) {
			
			productDAO dao = new productDAO();
	    	String txtSearch = request.getParameter("search");
	    	List<product> products = dao.searchByName(txtSearch);
	    	request.setAttribute("products", products);
	    	if(products.size() == 0 ) {
	    		request.setAttribute("error", "Không tìm thấy sản phẩm!");
	    	}
	    	else request.setAttribute("txtsearch", txtSearch);
		}
		else if(uri.contains("timkiemnangcao")) {
			String gia = request.getParameter("gia");
			List<product> products = new ArrayList<product>();
			productDAO dao = new productDAO();
			String txtsearch =null;
			List<product> Products = new ArrayList<product>();
			if(request.getParameter("txtsearch")!=null) {
				txtsearch = request.getParameter("txtsearch");
				Products = dao.searchByName(txtsearch);
			}

			if(gia.equals("tren")) {
				if(request.getParameter("txtsearch")!=null) {
					for(product c:Products) {
						if(c.getPrice() >100000)
							products.add(c);
					}
					
				}
			}
			else {
				if(request.getParameter("txtsearch")!=null) {
					for(product c:Products) {
						if(c.getPrice() <100000)
							products.add(c);
					}
				}
			}
			if(products.size() == 0 ) {
	    		request.setAttribute("error", "Không tìm thấy sản phẩm!");
	    	}
	    	else request.setAttribute("txtsearch", txtsearch);
			request.setAttribute("products", products);
		}
		
    	
		request.getRequestDispatcher("/view/product.jsp").forward(request, response);
	}

}
