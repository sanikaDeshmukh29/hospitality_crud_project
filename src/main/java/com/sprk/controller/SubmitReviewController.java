package com.sprk.controller;

import java.io.IOException;

import javax.sql.DataSource;

import com.sprk.dao.HotelDao;
import com.sprk.model.Review;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/submitReview")
public class SubmitReviewController extends HttpServlet {

    @Resource(name = "sprk_hotels")
    private DataSource dataSource;

    private HotelDao hotelDao;

    @Override
    public void init() throws ServletException {
        hotelDao = new HotelDao(dataSource);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Fetch and validate parameters
            String listingIdStr = req.getParameter("listingId");
            String ratingStr = req.getParameter("rating");
            String comment = req.getParameter("comment");
           
            
           

            // Check if any field is empty or null
            if (listingIdStr == null || listingIdStr.trim().isEmpty() ||
                ratingStr == null || ratingStr.trim().isEmpty() ||
                comment == null || comment.trim().isEmpty()) {
                
                req.setAttribute("errMsg", "All fields are required!");
                req.getRequestDispatcher("listings_details.jsp").forward(req, resp);
                return;
            }

            // Parse values
            int listingId = Integer.parseInt(listingIdStr.trim());
            int rating = Integer.parseInt(ratingStr.trim());

            // Ensure rating is between 1 and 5
            if (rating < 1 || rating > 5) {
                req.setAttribute("errMsg", "Invalid rating! Please select a value between 1 and 5.");
                req.getRequestDispatcher("listings_details.jsp?id=" + listingId).forward(req, resp);
                return;
            }

            // Create Review object
            Review review = new Review();
            review.setListingId(listingId);
            review.setRating(rating);
            review.setComment(comment.trim());
            

            // Save review in database
            try {
                hotelDao.saveReview(review);
                resp.sendRedirect("listings_details?id=" + listingId);
            } catch (Exception e) {
                req.setAttribute("errMsg", "Error saving review. Please try again.");
                req.getRequestDispatcher("listings_details.jsp?id=" + listingId).forward(req, resp);
            }
        } catch (NumberFormatException e) {
            req.setAttribute("errMsg", "Invalid input! Please enter valid numbers.");
            req.getRequestDispatcher("listings_details.jsp").forward(req, resp);
        }
    }

}
