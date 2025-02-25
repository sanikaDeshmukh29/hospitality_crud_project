<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200&icon_names=bookmark" />
    
    <style>
    .bg-wrapper {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100vh;
        background: url('<%= request.getContextPath() %>/img/hotelbg.jpg') no-repeat center center fixed;
        background-size: cover;
        z-index: -1;
      
    }
    
    
     </style>
</head>
<body>
          <div class="bg-wrapper"></div> 

        <jsp:include page="navbar.jsp"></jsp:include>
      
        <div class = "pt-5">
        <p>Listing ID: ${listing.id}</p>
        
        <div class = "container mt-5">
        
        <h2 class = "text-center liting-details-title text-white">${listing.title}</h2>
        
        <div class = "text-center listing-details-img">
        
          <img src="${listing.imageUrl}" alt="Listing Image" class="img-fluid" style="max-width: 600px; border-radius: 10px;">
        
        
        </div>
        
         <p class="mt-3 listing-details-descriptions text-center text-white"><strong>Description:</strong> ${listing.descriptions}</p>
        
         <h4 class="text-center text-white listing-details-price">Price: ${listing.price} / per night</h4>
        
         <!-- Edit & Delete Buttons -->
        
        <div class="d-flex gap-5 mt-4 justify-content-center">
            <a href="editListing?id=${listing.id}" class="btn btn-warning listing-details-edit">Edit</a>
            <a href="deleteListing?id=${listing.id}" class="btn btn-danger listing-details-delete" onclick="return confirm('Are you sure?')">Delete</a>
        </div>
        
        <hr>
        
        <!-- Leave a Review -->
        <h4 class="mt-4 listing-detail-review-heading text-center text-white">Leave a Review</h4>
        
        <div class= "d-flex justify-content-center align-items-center m-2 ">
        <form action="submitReview" method="post" class = " text-white d-flex flex-column align-items-center bg-white w-50 p-5 border border-primary rounded regular-shadow">
            <input type="hidden" name="listingId" value="${listing.id}">
           
           
            <label listing-detail-lable-rating text-white>Rating (1-5):</label>
			    
			    <select name="rating" class="form-select mb-3 rating-selection p-3" required>
			        <option value="">--Select Rating--</option> <!-- Prevents empty submission -->
			        <option value="5">5 - Excellent</option>
			        <option value="4">4 - Good</option>
			        <option value="3">3 - Average</option>
			        <option value="2">2 - Poor</option>
			        <option value="1">1 - Terrible</option>
			    </select>
       
          <textarea name="comment" class="form-control mb-2 comment-section mb-3" placeholder="Write your review..." required></textarea>
          <button type="submit" class="btn btn-primary submit-button mb-3">Submit Review</button>
        </form>
        </div>

        <hr>
        
         <!-- Display Reviews -->
       <div>
       
        <h4 class="mt-4 review-display-heading text-white">Reviews</h4>
        <c:if test="${empty reviews}">
            <p class = "text-white">No reviews yet.</p>
        </c:if>
        <c:forEach var="review" items="${reviews}">
            <div class="border rounded regular-shadow p-3 mb-2 display-riview-div bg-white">
             
                <p><strong>Rating:</strong> ${review.rating}/5</p>
                <p>${review.comment}</p>
            </div>
        </c:forEach>
       
       
       </div>
         
        
 </div>
</div>
</body>
</html>