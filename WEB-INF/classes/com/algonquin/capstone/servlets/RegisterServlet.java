package com.algonquin.capstone.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.algonquin.capstone.dao.DBConnection;
import com.google.gson.*;


/**
 * Servlet implementation class LogsServlet
 */
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *
	 */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, String> userEntities = new HashMap<String, String>();   
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        
        Connection connection = DBConnection.getConnectionToDatabase();
        userEntities.put("Email", email);
        userEntities.put("Fname", firstName);
        userEntities.put("Lname", lastName);
        userEntities.put("Password", password);

        boolean status = DBConnection.insertIntoDatabase(connection, "calendarpin", "users", userEntities);
        String htmlResponse = "";
        
        if (status) {
        	htmlResponse = String.format("Entry has been successfully written into the database, please login to continue");
        } else {
        	 htmlResponse = String.format("Entry was not successfully written to the database");
        }
        PrintWriter writer = response.getWriter();
   	 	writer.write(htmlResponse);
    }
}
