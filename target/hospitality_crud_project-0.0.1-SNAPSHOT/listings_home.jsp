<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SPRK Listings</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
    .bg-wrapper{
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
    
   

   <div class="container mt-5">
    <c:if test="${empty listings}">
        <div class="alert alert-warning text-center">No listings available.</div>
    </c:if>

    <div class="row g-5">
        <c:forEach var="listing" items="${listings}">
            <div class="col-md-4 mb-4">
                <div class="card h-100 shadow text-center">
                    
                    <!-- Clickable Image -->
                    <a href="${pageContext.request.contextPath}/listings_details?id=${listing.id}" class="image-link">
                        <img src="${listing.imageUrl}" class="card-img-top img-fluid" alt="Hotel Image">
                    </a>
                    
                    <div class="card-body">
                        <!-- Title -->
                        <h5 class="card-title listing-title">${listing.title}</h5>
                        
                        <!-- Price -->
                        <h6 class="text-success fw-bold">${listing.price} / per night</h6>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
   

</body>
</html>
