package com.sprk.controller;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import com.sprk.dao.HotelDao;
import com.sprk.model.Listings;
import com.sprk.model.Review;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listings_details")
public class ListingDetailsController extends HttpServlet {

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
            System.out.println("Fetching listing with ID: " + listingId); // Debugging
            
            Listings listing = hotelDao.getListingById(listingId);
            if (listing == null) {
                System.out.println("No listing found for ID: " + listingId); // Debugging
            }
            
            List<Review> reviews = hotelDao.getReviewsByListingId(listingId);
            if (reviews.isEmpty()) {
                System.out.println("No reviews found for ID: " + listingId); // Debugging
            }

            req.setAttribute("listing", listing);
            req.setAttribute("reviews", reviews);
            req.getRequestDispatcher("/listings_details.jsp").forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("errMsg", "Error fetching listing details.");
            req.getRequestDispatcher("/listings_home.jsp").forward(req, resp);
        }
    }
}