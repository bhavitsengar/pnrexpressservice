/*******************************************************************************
 * Copyright (c) 2015 Bhavit Singh Sengar.
 *
 * All Rights Reserved.
 *******************************************************************************/

package com.bhavit.pnrexpress.service;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bhavit.pnrexpress.util.HMACGenarator;
import com.bhavit.pnrexpress.util.Helper;
import com.bhavit.pnrexpress.util.RestClient;

@SuppressWarnings("serial")
public class TrainsBetweenStationService extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String from = (String) req.getParameter("from");
		String to = (String) req.getParameter("to");
		String date = (String) req.getParameter("date");
		String classs = (String) req.getParameter("class");

		RestClient client = new RestClient(
				"http://railpnrapi.com/api/trains_between_stations/fscode/"
						+ from
						+ "/tscode/"
						+ to
						+ "/date/"
						+ date
						+ (classs.equalsIgnoreCase("all")?"":"/class/")
						+ (classs.equalsIgnoreCase("all")?"":classs)
						+ "/orderby/time/format/json/pbapikey/"
						+ Helper.publicapikey
						+ "/pbapisign/"
						+ HMACGenarator
								.getSignature(
										Helper.privateapikey,
										((classs.equalsIgnoreCase("all")?"":classs) + date + "json" + from + "time" + Helper.publicapikey+to)
												.toLowerCase()));

		String result = client.executeGet();
		System.out.println(result);

		resp.setContentType("application/json");
		resp.getWriter().print(result);

		/**********************************************************************************************************************/

		/*
		 * 
		 * String from = (String) req.getParameter("from"); String to = (String)
		 * req.getParameter("to"); String date = (String)
		 * req.getParameter("date"); String classs = (String)
		 * req.getParameter("class"); String quota = (String)
		 * req.getParameter("quota");
		 * 
		 * HashMap<Object, Object> map = new HashMap<Object, Object>();
		 * 
		 * try { RestClient client = new
		 * RestClient("http://pnrbuddy.com/hauth/seatavailtrains");
		 * client.addHeader("Content-Type",
		 * "application/x-www-form-urlencoded");
		 * client.addStringBody("from="+from
		 * +"&to="+to+"&date="+date+"&class="+classs+"&quota="+quota);
		 * 
		 * String result = client.executePost(); System.out.println(result);
		 * 
		 * UserAgent userAgent = new UserAgent();
		 * 
		 * userAgent.openContent(result);
		 * 
		 * Table table = userAgent.doc.getTable("<table>"); // find table //
		 * element
		 * 
		 * Element eTable = userAgent.doc.findFirst("<table>");
		 * 
		 * Elements trs = eTable.findEach("<tr>"); Elements tableHeadings =
		 * table.getRow(0);
		 * 
		 * List<Train> trains = new ArrayList<Train>();
		 * 
		 * for (int i = 1; i < trs.size(); i++) {
		 * 
		 * Elements elements = table.getRow(i); Element anchor =
		 * elements.findFirst("<a href='#'>"); String trainNo =
		 * elements.getElement(0).innerHTML(); String trainName =
		 * elements.getElement(1).innerHTML().trim() .substring(1); String
		 * fromStation = elements.getElement(2).innerHTML().trim(); String
		 * departureTime = elements.getElement(3).innerHTML() .trim(); String
		 * toStation = elements.getElement(4).innerHTML().trim(); String
		 * arrivalTime = elements.getElement(5).innerHTML().trim(); String
		 * travelTime = elements.getElement(6).innerHTML().trim(); String runOn
		 * = elements.getElement(7).innerHTML().trim() +
		 * elements.getElement(8).innerHTML().trim() +
		 * elements.getElement(9).innerHTML().trim() +
		 * elements.getElement(10).innerHTML().trim() +
		 * elements.getElement(11).innerHTML().trim() +
		 * elements.getElement(12).innerHTML().trim() +
		 * elements.getElement(13).innerHTML().trim();
		 * 
		 * String classes = "";
		 * 
		 * for (int j = 14; j <= 21; j++) { if
		 * (!elements.getElement(j).innerHTML().trim().equals("-")) { classes =
		 * classes + tableHeadings.getElement(j).innerHTML() .trim() + "|"; } }
		 * 
		 * String seatAvailabilityQuery = anchor.getAt("onclick").substring(12,
		 * 34); trains.add(new Train(trainName, trainNo, fromStation, toStation,
		 * departureTime, arrivalTime, travelTime.split("\\:")[0] + "Hours " +
		 * travelTime.split("\\:")[1] + "mins", runOn, classes,
		 * seatAvailabilityQuery)); }
		 * 
		 * map.put("trains", trains);
		 * 
		 * } catch(Exception e){ e.printStackTrace(); map.put("error",
		 * "Unable to get availability. Please try again later."); }
		 * 
		 * Gson g = new Gson();
		 * 
		 * resp.setContentType("application/json");
		 * resp.getWriter().print(g.toJson(map));
		 */}
}
