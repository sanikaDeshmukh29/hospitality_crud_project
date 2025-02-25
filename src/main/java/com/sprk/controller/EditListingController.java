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

@WebServlet("/editListing")
public class EditListingController extends HttpServlet {
    
	 @Resource(name = "sprk_hotels")
	    private DataSource dataSource;

	    private HotelDao hotelDao;

	    @Override
	    public void init() throws ServletException {
	        hotelDao = new HotelDao(dataSource);
	    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        
        if (idParam == null || idParam.isEmpty()) {
            resp.sendRedirect("listings/home");
            return;
        }

        try {
            int listingId = Integer.parseInt(idParam);
            Listings listing = hotelDao.getListingById(listingId);
            
            if (listing == null) {
                resp.sendRedirect("listings/home");
                return;
            }

            req.setAttribute("listing", listing);
            req.getRequestDispatcher("/edit_listing.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("listings/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String title = req.getParameter("title");
            String descriptions = req.getParameter("descriptions");
           
            String imageUrl = req.getParameter("imageUrl");
            double price = Double.parseDouble(req.getParameter("price"));

            Listings updatedListing = new Listings(id, title, descriptions, imageUrl, price);
            boolean success = hotelDao.updateListing(updatedListing);

            if (success) {
                resp.sendRedirect(req.getContextPath()+"/listings/home");
            } else {
                req.setAttribute("errMsg", "Failed to update listing.");
                req.getRequestDispatcher("/edit_listing.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("errMsg", "Error updating listing.");
            req.getRequestDispatcher("/edit_listing.jsp").forward(req, resp);
        }
    }
}
