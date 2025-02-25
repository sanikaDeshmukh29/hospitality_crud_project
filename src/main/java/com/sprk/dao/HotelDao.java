package com.sprk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import com.sprk.model.Listings;
import com.sprk.model.Review;

public class HotelDao {

	private DataSource dataSource;
	
	public HotelDao(DataSource dataSource) {
		
		this.dataSource = dataSource;
	}
	
	public Connection testConnection() throws SQLException {
		
		Connection conn = dataSource.getConnection();
		
		return conn;
		
	}
	
	public int saveListings(Listings listings) throws Exception {
		
		Connection conn = dataSource.getConnection();
		
		String sql = "insert into listings(title, descriptions, imageUrl, price) values (?,?,?,?)";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, listings.getTitle());
		ps.setString(2, listings.getDescriptions());
		ps.setString(3, listings.getImageUrl());
		ps.setDouble(4, listings.getPrice());
		
		int result = ps.executeUpdate();
		
		closeAll(ps, conn, null);
		
		return result;
		
	}
	
	public List<Listings> getAllListings() throws SQLException {
	    List<Listings> listings = new LinkedList<>();
	    
	    try (Connection conn = dataSource.getConnection();
	         PreparedStatement ps = conn.prepareStatement("SELECT * FROM listings");
	         ResultSet rs = ps.executeQuery()) { // Auto-closes resources

	        while (rs.next()) {
	            Listings listing = new Listings();
	            listing.setId(rs.getInt("id"));
	            listing.setTitle(rs.getString("title"));
	            listing.setDescriptions(rs.getString("descriptions"));
	            listing.setImageUrl(rs.getString("imageUrl"));
	            listing.setPrice(rs.getDouble("price"));
	            listings.add(listing);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listings;
	}
	
	
	
	public Listings getListingById(int listingId) throws Exception {
	    Connection conn = dataSource.getConnection();
	    String sql = "SELECT * FROM listings WHERE id = ?";
	    PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setInt(1, listingId);

	    System.out.println("Executing SQL: " + ps.toString()); // Debugging line

	    ResultSet rs = ps.executeQuery();
	    Listings listing = null;

	    if (rs.next()) {
	        listing = new Listings();
	        listing.setId(rs.getInt("id"));
	        listing.setTitle(rs.getString("title"));
	        listing.setDescriptions(rs.getString("descriptions"));
	        listing.setImageUrl(rs.getString("imageUrl"));
	        listing.setPrice(rs.getDouble("price"));
	    } else {
	        System.out.println("No listing found for ID: " + listingId); // Debugging
	    }

	    closeAll(ps, conn, rs);
	    return listing;
	}


	public List<Review> getReviewsByListingId(int listingId) throws Exception {
	    Connection conn = dataSource.getConnection();
	    String sql = "SELECT * FROM reviews WHERE listing_id = ?";
	    PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setInt(1, listingId);
	    
	    ResultSet rs = ps.executeQuery();
	    List<Review> reviews = new LinkedList<>();

	    while (rs.next()) {
	        Review review = new Review();
	        review.setId(rs.getInt("id"));
	        review.setListingId(rs.getInt("listing_id"));
	        review.setRating(rs.getInt("rating"));
	        review.setComment(rs.getString("comment"));
	        reviews.add(review);
	    }

	    closeAll(ps, conn, rs);
	    return reviews;
	}


	public int saveReview(Review review) throws Exception {
	    Connection conn = dataSource.getConnection();
	    
	    String sql = "INSERT INTO reviews (listing_id, rating, comment) VALUES (?, ?, ?)";
	    
	    PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setInt(1, review.getListingId());
	    ps.setInt(2, review.getRating());
	    ps.setString(3, review.getComment());
	    
	    int result = ps.executeUpdate();
	    
	    closeAll(ps, conn, null);
	    
	    return result;
	}
	
	public boolean updateListing(Listings listing) {
	    String sql = "UPDATE listings SET title=?, descriptions=?, imageUrl=?, price=? WHERE id=?";
	    
	    try (Connection conn = dataSource.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setString(1, listing.getTitle());
	        stmt.setString(2, listing.getDescriptions());
	        stmt.setString(3, listing.getImageUrl());
	        stmt.setDouble(4, listing.getPrice());
	        stmt.setInt(5, listing.getId());

	        // Debugging: Print the values
	        System.out.println("Executing Update: " + sql);
	        System.out.println("Values: " + listing.getTitle() + ", " + listing.getDescriptions() + 
	                           ", " + listing.getImageUrl() + ", " + listing.getPrice() + ", " + listing.getId());

	        int rowsAffected = stmt.executeUpdate();
	        System.out.println("Rows Affected: " + rowsAffected);

	        return rowsAffected > 0;
	    } catch (Exception e) {
	        e.printStackTrace();  // Print error in console
	        return false;
	    }
	}
	
	public boolean deleteListing(int id) {
		
		String sql = ("delete from listings where id = ?");
		
		try 
		(Connection conn = dataSource.getConnection();
	    PreparedStatement ps = conn.prepareStatement(sql))
		{
			
			ps.setInt(1, id);
			
			int rowsAffected = ps.executeUpdate();
			
			return rowsAffected > 0;
			
			
			    
		} catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();  // Print error in console
		        return false;
		}
	}



	
	
	
	
	
	
	
	
	private void closeAll(PreparedStatement ps, Connection conn, ResultSet rs) throws Exception {
		if (ps != null) {
			ps.close();
		}
		if (rs != null) {
			rs.close();
		}
		if (conn != null) {
			conn.close();
		}
	}
}
