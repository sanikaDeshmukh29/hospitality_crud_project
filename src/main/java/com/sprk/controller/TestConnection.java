package com.sprk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.sql.DataSource;

import com.sprk.dao.HotelDao;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/test-conn")
public class TestConnection extends HttpServlet{
	
	@Resource(name = "sprk_hotels")
	private DataSource dataSource;
	
	private HotelDao hotelDao;

	@Override
	public void init() throws ServletException {
		
		 hotelDao = new HotelDao(dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		
		Connection conn = null;
		
		out.print("<html>");
		out.print("<head>");
		out.print("<title>Testing DB Connection Servlet</title>");
		out.print("</head>");
		out.print("<body>");
		
		    try {
		    	
		    	conn = hotelDao.testConnection();
		    	out.print("<h1> Connection Established Successfully ! </h1>");
		    	out.print("<p> Connection : "+conn+"</p>");
				
			} catch (Exception e) {
				// TODO: handle exception
				out.print(e.getMessage()); 
			}
		out.print("</body>");
		out.print("</html>");
		
	}
	

}
