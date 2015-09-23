/*******************************************************************************
 * Copyright (c) 2015 Bhavit Singh Sengar.
 *
 * All Rights Reserved.
 *******************************************************************************/

package com.bhavit.pnrexpress.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bhavit.pnrexpress.model.LiveStatusPojo;
import com.bhavit.pnrexpress.util.Helper;
import com.bhavit.pnrexpress.util.RestClient;
import com.google.gson.Gson;
import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.UserAgent;
import com.jaunt.component.Table;

@SuppressWarnings("serial")
public class LiveStatusService extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String tnum = (String) req.getParameter("tnum");
		String date = req.getParameter("date");

		HashMap<Object, Object> map = new HashMap<Object, Object>();

		try {
			RestClient client = new RestClient(
					"http://runningstatus.in/status/" + tnum + "-on-" + date
					+ "");
			client.addHeader("Content-Type",
					"application/x-www-form-urlencoded");

			String result = client.executeGet();
			System.out.println("Response>>>>"+result);
			
			UserAgent userAgent = new UserAgent();

			userAgent.openContent(result);
			
			Table table = userAgent.doc.getTable(0); // find table element

			Element eTable = userAgent.doc.findFirst("<table>"); // find table
			// element
			Elements trs = eTable.findEach("<tr>");

			ArrayList<LiveStatusPojo> array = new ArrayList<LiveStatusPojo>();
			for (int i = 1; i < trs.size() - 2; i++) {

				Elements elements = table.getRow(i);
				String station = elements.getElement(0).innerHTML();
				System.out.println(station);
				String platform = elements.getElement(1).innerHTML().trim();
				String scheduledArrival = elements.getElement(2).innerHTML()
						.trim();
				String scheduledDeparture = elements.getElement(3).innerHTML()
						.trim();

				String actualArrival = "";
				String actualDeparture = "";
				if (!elements.getElement(4).innerHTML().trim()
						.contains("Waiting for Update")) {
					actualArrival = elements.getElement(4).innerHTML().trim()
							.split("/")[0];
					actualDeparture = elements.getElement(4).innerHTML().trim()
							.split("/")[1];
				}

				String trainStatus = "";
				System.out.println(elements.getElement(5).innerHTML());
				if (!elements.getElement(5).innerHTML().trim()
						.contains("E.T.A")
						&& !elements.getElement(5).innerHTML().trim()
						.contains("Waiting for Update")) {
					userAgent.openContent(elements.getElement(5).innerHTML()
							.trim());

					Element font = userAgent.doc.findFirst("<font>");
					trainStatus = font.innerHTML();

					userAgent.openContent(elements.getElement(5).innerHTML()
							.trim().split(" / ")[1]);
					font = userAgent.doc.findFirst("<font>");
					trainStatus = trainStatus + "/" + font.innerHTML();
					if(trainStatus.contains("Arrived"))	 
						trainStatus = "Arrived/"+ font.innerHTML();
				} else if (elements.getElement(5).innerHTML().trim()
						.contains("Waiting for Update")) {

					trainStatus = "Waiting for Update";

				} else {
					trainStatus = "";
				}

				array.add(new LiveStatusPojo(station, platform,
						scheduledArrival, scheduledDeparture, actualArrival,
						actualDeparture, trainStatus));

			}

			System.out
			.println("--------------------------------------------------------------");

			map.put("livestatus", array);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", "Network Error Occurred. Please try again.");
		}

		Gson g = new Gson();

		resp.setContentType("application/json");
		resp.getWriter().print(g.toJson(map));
	}

}
