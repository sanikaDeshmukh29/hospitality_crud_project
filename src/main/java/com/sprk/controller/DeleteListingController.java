package com.sprk.controller;

import java.io.IOException;

import javax.sql.DataSource;

import com.sprk.dao.HotelDao;
import com.sprk.model.Listings;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteListing")
public class DeleteListingController extends HttpServlet {

	 @Resource(name = "sprk_hotels")
	    private DataSource dataSource;

	    private HotelDao hotelDao;

	    @Override
	    public void init() throws ServletException {
	        hotelDao = new HotelDao(dataSource);
	    }
	    
	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        try {
	            int id = Integer.parseInt(req.getParameter("id"));
	           
	            
	          

	            
	            boolean success = hotelDao.deleteListing(id);

	            if (success) {
	                resp.sendRedirect(req.getContextPath()+"/listings");
	            } else {
	                req.setAttribute("errMsg", "Failed to delete listing.");
	                req.getRequestDispatcher("/listing_details.jsp").forward(req, resp);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            req.setAttribute("errMsg", "Error updating listing.");
	            req.getRequestDispatcher("/listing_details.jsp").forward(req, resp);
	        }
	    }
}
