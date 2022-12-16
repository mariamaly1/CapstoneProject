package com.algonquin.capstone.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.algonquin.capstone.dao.DBConnection;
import com.algonquin.capstone.dto.TimeframeDTO;
import com.google.gson.*;

/**
 * Servlet implementation class LogsServlet
 */
@WebServlet("/timeFrameServlet")
public class Timeframe extends HttpServlet {
    private static final long serialVersionUID = 1L;
   /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *
	 */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Object> userEntities = new HashMap<String, Object>();   
        String increment = request.getParameter("increment");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String limitedChoice = request.getParameter("limitedChoice");
        String privateChoice = request.getParameter("privateChoice");
        String accessPin = request.getParameter("accessPin");
        String ownerId = request.getParameter("ownerId");
        
        
        System.out.println("increment == "+increment);
        System.out.println("name == "+name);
        System.out.println("description == "+description);
        System.out.println("limitedChoice == "+limitedChoice);
        System.out.println("privateChoice == "+privateChoice);
        System.out.println("accessPin == "+accessPin);
        System.out.println("ownerId == "+ownerId);
        
        
        Connection connection = DBConnection.getConnectionToDatabase();
        userEntities.put("Increment", increment);
        userEntities.put("Name", name);
        userEntities.put("Description", description);
        
        userEntities.put("AccessPin", accessPin);
        userEntities.put("IsPrivate", privateChoice);
        userEntities.put("oneSlot", limitedChoice);
        userEntities.put("OwnerId", ownerId);
        

        boolean status = DBConnection.insertIntoDatabase(connection, "calendarpin", "timeframes", userEntities);
        String htmlResponse = "";
        if (status) {
        	htmlResponse = String.format("Entry has been successfully written into the database");
            
        } else {
        	 htmlResponse = String.format("Entry was not successfully written to the database");        
        }
        PrintWriter writer = response.getWriter();
        writer.append(htmlResponse);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HashMap<String, String> loginEntities = new HashMap<String, String>();   
    	String ownerId = request.getParameter("OwnerId");
        Connection connection = DBConnection.getConnectionToDatabase();
        loginEntities.put("OwnerId", ownerId);
        
        ResultSet result = DBConnection.selectFromDatabase(connection, "calendarpin", "timeframes", loginEntities);
               
        try {
        	PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(createDTOTimeFrame(result));
            out.flush();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private ArrayList<String> createDTOTimeFrame(ResultSet result) throws SQLException {
    	TimeframeDTO timeFrameDTO = new TimeframeDTO();
    	Gson gson = new Gson();
    	String timeFrameDTOString = null;
    	ArrayList<String> timeFrameDTOArray = new ArrayList<String>();
    	do {
    		timeFrameDTO.setTimeFrameId(result.getString("TimeFrameId"));
        	timeFrameDTO.setTFUUID(result.getString("TFUUID"));
        	timeFrameDTO.setOwnerId(result.getString("OwnerId"));
        	timeFrameDTO.setLocationId(result.getString("LocationId"));
        	timeFrameDTO.setStartDate(result.getString("StartDate"));
        	timeFrameDTO.setEndDate(result.getString("EndDate"));
        	timeFrameDTO.setStartDate(result.getString("StartDate"));
        	timeFrameDTO.setIncrement(result.getString("Increment"));
        	timeFrameDTO.setName(result.getString("Name"));
        	timeFrameDTO.setDescription(result.getString("Description"));
        	timeFrameDTO.setOneSlot(result.getString("oneSlot"));
        	timeFrameDTO.setIsPrivate(result.getString("IsPrivate"));
        	timeFrameDTO.setAccessPin(result.getString("AccessPin"));
        	timeFrameDTO.setCreatedBy(result.getString("CreatedBy"));
        	timeFrameDTO.setCreatedOn(result.getString("CreatedOn"));
        	timeFrameDTO.setUpdatedBy(result.getString("UpdatedBy"));
        	timeFrameDTO.setUpdatedOn(result.getString("UpdatedOn"));
        	timeFrameDTO.setDeletedBy(result.getString("DeletedBy"));
        	timeFrameDTO.setDeletedOn(result.getString("DeletedOn"));
        	timeFrameDTOString = gson.toJson(timeFrameDTO);
        	timeFrameDTOArray.add(timeFrameDTOString);
    	} while (result.next());
    	
    	return timeFrameDTOArray;
    }
}
