package com.bhavit.pnrexpress.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bhavit.pnrexpress.util.Helper;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.gson.JsonObject;


@SuppressWarnings("serial")
public class UpdateCheckService extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		JsonObject obj = new JsonObject();
		
		Helper.setProxy();
		String macAddress = req.getParameter("macaddr");
		
		// Get the Datastore Service
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Filter filter =
				  new FilterPredicate("mac_address",
				                      FilterOperator.EQUAL,
				                      macAddress);		
		// Use class Query to assemble a query
		Query q = new Query("update").setFilter(filter);
		
		if(q!=null){

		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(q);

		
		
		for (Entity result : pq.asIterable()) {
		  boolean updateFlag = (boolean) result.getProperty("updateFlag");
		  String macAddr = (String) result.getProperty("mac_address");

		  System.out.println(updateFlag + " / " + macAddr);
		}

		}else {
			Entity updatecheck = new Entity("updatecheck", "updatecheck");
			updatecheck.setProperty("updateFlag", true);
			updatecheck.setProperty("mac_address", macAddress);
			
			datastore.put(updatecheck);
		}
	
		

		resp.setContentType("application/json");
	}

}
