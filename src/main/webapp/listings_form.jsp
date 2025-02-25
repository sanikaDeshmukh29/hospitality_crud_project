<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SPRK Hotels</title>
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
        

				<div class="form-container">
				
				<c:set var = "errMsg" value = "<%= request.getAttribute(\"errorMsg\") %>"/>
       
       <c:if test="${not empty errMsg}">
          <div class="alert alert-danger w-50 text-center mx-auto" role="alert">
  			${errMsg}
		</div>
       </c:if>
       
				    <form  method="post" action="${pageContext.request.contextPath}/listings">
				      
				
				        <div class="mb-2">
				            <label for="title" class="form-label">Title</label>
				            <input type="text" class="form-control" id="title" placeholder="Enter title" name = "title">
				        </div>
				
				        <div class="mb-2">
				            <label for="description" class="form-label">Description</label>
				            <textarea class="form-control" id="description" rows="2" placeholder="Enter description" name = "descriptions"></textarea>
				        </div>
				
				        <div class="row mb-2">
				            <div class="col-6">
				                <label for="price" class="form-label">Price</label>
				                <input type="number" class="form-control" id="price" placeholder="Enter price" name = "price">
				            </div>
				            <div class="col-6">
				                <label for="imageUrl" class="form-label">Image URL</label>
				                <input type="text" class="form-control" id="imageUrl" placeholder="Enter image URL" name="imageUrl">
				            </div>
				        </div>
				
				        <button type="submit" class="btn btn-primary save-button">
				       
                         <span class="material-symbols-outlined">
                            bookmark
                         </span>
                       Save</button>
				    </form>
				</div>



</body>
</html>