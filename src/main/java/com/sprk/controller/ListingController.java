package com.sprk.controller;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import com.sprk.dao.HotelDao;
import com.sprk.model.Listings;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listings")
public class ListingController extends HttpServlet {

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
            List<Listings> listings = hotelDao.getAllListings(); // ✅ Always fetch fresh data
            req.setAttribute("listings", listings);
        } catch (Exception e) {
            req.setAttribute("errMsg", "Error fetching listings: " + e.getMessage());
        }

        req.getRequestDispatcher("/listings_home.jsp").forward(req, resp);   //change
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String title = req.getParameter("title");
        String descriptions = req.getParameter("descriptions");
        String imageUrl = req.getParameter("imageUrl");
        String priceStr = req.getParameter("price");

        double priceDouble = 0.0; // Default value

        // Validation
        if (title == null || title.isBlank()) {
            req.setAttribute("errMsg", "Title cannot be empty");
            req.getRequestDispatcher("listing_form.jsp").forward(req, resp);
            return; // Stop further execution
        } else if (descriptions == null || descriptions.isBlank()) {
            req.setAttribute("errMsg", "Descriptions cannot be empty");
            req.getRequestDispatcher("listing_form.jsp").forward(req, resp);
            return;
        } else if (imageUrl == null || imageUrl.isBlank()) {
            req.setAttribute("errMsg", "Image URL cannot be empty");
            req.getRequestDispatcher("listing_form.jsp").forward(req, resp);
            return;
        } else if (priceStr == null || priceStr.isBlank()) {
            req.setAttribute("errMsg", "Price cannot be empty");
            req.getRequestDispatcher("listing_form.jsp").forward(req, resp);
            return;
        }

        // Convert price to double safely
        try {
            priceDouble = Double.parseDouble(priceStr);
            if (priceDouble <= 0) {
                req.setAttribute("errMsg", "Price must be greater than 0");
                req.getRequestDispatcher("listing_form.jsp").forward(req, resp);
                return;
            }
        } catch (NumberFormatException e) {
            req.setAttribute("errMsg", "Invalid price format");
            req.getRequestDispatcher("listing_form.jsp").forward(req, resp);
            return;
        }

        // If all validations pass, create the Listings object
        Listings listings = new Listings(0, title, descriptions, imageUrl, priceDouble);

        try {
            int result = hotelDao.saveListings(listings);

            if (result > 0) {
                resp.sendRedirect(req.getContextPath() + "/listings");
            } else {
                req.setAttribute("errMsg", "Database error occurred");
                req.getRequestDispatcher("listing_form.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            System.out.println("Error inserting data: " + e.getMessage());
            req.setAttribute("errMsg", "Database error: " + e.getMessage());
            req.getRequestDispatcher("listing_form.jsp").forward(req, resp);
        }
    }
}
