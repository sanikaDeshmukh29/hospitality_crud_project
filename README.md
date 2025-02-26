# Hospitality CRUD Project

## 📌 Project Overview
This is a **Hospitality CRUD** web application built using **Java, JSP, Servlets, and MySQL**. It allows users to manage hotel listings, including adding, editing, and deleting listings. The project follows **MVC architecture** and uses **Maven** for dependency management.

## ✨ Features
- 🏨 View all hotel listings
- ➕ Add new listings
- ✏️ Edit existing listings
- ❌ Delete listings
- 💬 Submit and view reviews

## 🛠 Technologies Used
- **Backend:** Java, Servlets, JSP
- **Frontend:** HTML, CSS, JSP
- **Database:** MySQL
- **Build Tool:** Maven
- **Server:** Apache Tomcat

## 📂 Project Structure
```plaintext
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com.sprk.controller
│   │   │   │   ├── DeleteListingController.java
│   │   │   │   ├── EditListingController.java
│   │   │   │   ├── ListingController.java
│   │   │   │   ├── ListingDetailsController.java
│   │   │   │   ├── SubmitReviewController.java
│   │   │   ├── com.sprk.dao
│   │   │   │   ├── HotelDao.java
│   │   │   ├── com.sprk.model
│   │   │   │   ├── Listings.java
│   │   │   │   ├── Review.java
│   │   ├── resources
│   │   ├── webapp
│   │   │   ├── css
│   │   │   ├── img
│   │   │   ├── META-INF
│   │   │   ├── WEB-INF
│   │   │   │   ├── lib
│   │   │   │   ├── web.xml
│   │   │   ├── edit_listing.jsp
│   │   │   ├── index.jsp
│   │   │   ├── listings_details.jsp
│   │   │   ├── listings_form.jsp
│   │   │   ├── listings_home.jsp
│   │   │   ├── navbar.jsp
├── test
```

## 🗄️ Database Setup
1. Install MySQL and create a database:
   ```sql
   CREATE DATABASE hospitality_db;
   ```
2. Create the `listings` table:
   ```sql
   CREATE TABLE listings (
       id INT AUTO_INCREMENT PRIMARY KEY,
       title VARCHAR(255) NOT NULL,
       descriptions TEXT NOT NULL,
       imageUrl VARCHAR(255),
       price DOUBLE NOT NULL
   );
   ```
3. Update Database Configuration in `HotelDao.java`:
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/hospitality_db";
   private static final String USER = "root";
   private static final String PASSWORD = "your_password";
   ```

## 🚀 Running the Project
1. Clone the repository:
   ```sh
   git clone https://github.com/your-username/hospitality-crud.git
   ```
2. Open the project in **Eclipse** or **IntelliJ**.
3. Configure **Tomcat Server**.
4. Run the project and access it in the browser:
   ```sh
   http://localhost:8080/hospitality_crud_project/listings
   ```

## 🏠 Default Home Page Issue Fix
To make `/listings` the default page when running the project:
1. Open `web.xml` and add this:
   ```xml
   <welcome-file-list>
       <welcome-file>listings_home.jsp</welcome-file>
   </welcome-file-list>
   ```
2. Restart the server and ensure it navigates to `/listings`.

## 🤝 Contributions
Feel free to **contribute** by submitting pull requests or reporting issues!

## 📜 License
This project is **open-source** and available under the **MIT License**.

---
💡 *Happy Coding!* 🎉

