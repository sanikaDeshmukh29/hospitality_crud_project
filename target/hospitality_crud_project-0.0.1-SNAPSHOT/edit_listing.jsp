<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Listing</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<jsp:include page="navbar.jsp"></jsp:include>

<div class="container mt-5">
    <h2 class="text-center">Edit Listing</h2>
    
    <form action="editListing" method="post">
        <input type="hidden" name="id" value="${listing.id}">

        <div class="mb-3">
            <label class="form-label">Title</label>
            <input type="text" name="title" class="form-control" value="${listing.title}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Description</label>
            <textarea name="descriptions" class="form-control" required>${listing.descriptions}</textarea>
        </div>
        
         <div class="mb-3">
            <label class="form-label">Image URL</label>
            <input type="text" name="imageUrl" class="form-control" value="${listing.imageUrl}" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Price</label>
            <input type="number" name="price" class="form-control" value="${listing.price}" required>
        </div>

       

        <button type="submit" class="btn btn-primary">Update Listing</button>
        <a href="listings/home" class="btn btn-secondary">Cancel</a>
    </form>
</div>

</body>
</html>
