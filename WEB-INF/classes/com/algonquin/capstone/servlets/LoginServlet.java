package com.algonquin.capstone.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.algonquin.capstone.dao.DBConnection;

/**
 * Servlet implementation class LogsServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HashMap<String, String> loginEntities = new HashMap<String, String>();   
    	String email = request.getParameter("email");
        String password = request.getParameter("password");
        Connection connection = DBConnection.getConnectionToDatabase();
        loginEntities.put("Email", email);
        loginEntities.put("Password", password);
        
        ResultSet status = DBConnection.selectFromDatabase(connection, "calendarpin", "users", loginEntities);
        String htmlResponse = null;
        if (status !=null) {
        	htmlResponse = String.format("YOU HAVE SUCCESSFULLY LOGGED IN:"+DBConnection.getUserId());
        } else {
        	htmlResponse = String.format("INVALID LOGIN, PLEASE TRY AGAIN");
        }
        PrintWriter writer = response.getWriter();
       
        writer.write(htmlResponse);   
    }
}
