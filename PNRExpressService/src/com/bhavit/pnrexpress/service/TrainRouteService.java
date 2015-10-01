/*******************************************************************************
 * Copyright (c) 2015 Bhavit Singh Sengar.
 *
 * All Rights Reserved.
 *******************************************************************************/

package com.bhavit.pnrexpress.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.util.ajax.JSONObjectConvertor;

import com.bhavit.pnrexpress.model.Station;
import com.bhavit.pnrexpress.model.Train;
import com.bhavit.pnrexpress.util.HMACGenarator;
import com.bhavit.pnrexpress.util.Helper;
import com.bhavit.pnrexpress.util.RestClient;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.UserAgent;
import com.jaunt.component.Table;

@SuppressWarnings("serial")
public class TrainRouteService extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		Helper.setProxy();
		String trainNo = (String) req.getParameter("trainno");
		
		String url = "http://railpnrapi.com/api/route/train/"
				+ trainNo
				+ "/format/json/pbapikey/"
				+ Helper.publicapikey
				+ "/pbapisign/"
				+ HMACGenarator.getSignature(Helper.privateapikey, "json"
						+ Helper.publicapikey + trainNo);
		
		RestClient client = new RestClient(url);

		System.out.println(url);

		String result = client.executeGet();
		System.out.println(result);
		
		HashMap<Object, Object> map = new HashMap<Object, Object>();

		try {
			JSONObject root = new JSONObject(result);
			if(!root.has("msg")){
			
				Train train = new Train();
				train.setTrainName(root.getJSONObject("train").getString("name")+"("+root.getJSONObject("train").getString("number")+")");
				//train.setRunsOn(runsOn);
				
				map.put("train_information", train);

				List<Station> stations = new ArrayList<Station>();
				JSONArray arr = root.getJSONObject("train").getJSONArray("route");
				for(int i = 0 ; i<arr.length() ; i++){

					JSONObject obj = arr.getJSONObject(i);
					String stationNo = obj.getString("no");
					String arrivalTime = obj.getString("arrival_time");
					String departureTime = obj.getString("departure_time");
					String stopTime = obj.getString("stop_time");
					String day = obj.getString("day");
					String distance = obj.getString("distance");

					JSONObject station= obj.getJSONObject("station");
					String stationName = station.getString("name");
					String stationCode = station.getString("code");

					JSONObject location = station.getJSONObject("location");
					String latitude = location.getString("lat");
					String longitude = location.getString("lng");


					stations.add(new Station(stationName, stationCode, stationNo, arrivalTime, departureTime, stopTime, day, distance, latitude, longitude));

				}
				map.put("train_route", stations);

			} else {
				map.put("error", root.getString("msg"));
			}

		} catch(Exception e){
			e.printStackTrace();
			map.put("error", "Network Error Occurred. Please try again.");
		}

		Gson g = new Gson();

		resp.setContentType("application/json");
		resp.getWriter().print(g.toJson(map));

		/*HashMap<Object, Object> map = new HashMap<Object, Object>();

		try {
			RestClient client = new RestClient("http://pnrbuddy.com/hauth/trainroute");
			client.addHeader("Content-Type",
					"application/x-www-form-urlencoded");
			client.addStringBody("trainno="+trainNo);

			String result = client.executePost();
			System.out.println("Response>>>>"+result);

			if(!result.contains("No train found.")){

				UserAgent userAgent = new UserAgent();

				userAgent.openContent(result);
			
				Element tNameSpan = userAgent.doc.findFirst("<span class=train-name>");
				String trainName = tNameSpan.innerHTML().trim().replaceAll("\\t+", "");
				
				Element runsOnSpan = userAgent.doc.findFirst("<span class=train-days>");
				String runsOn = runsOnSpan.innerHTML().trim().replaceAll("\\t+", "");;
				
				Train obj = new Train();
				obj.setTrainName(trainName);
				obj.setRunsOn(runsOn);
				
				map.put("train_information", obj);

				Table table = userAgent.doc.getTable("<table class=default-table>");  //find table element

				Element eTable = userAgent.doc.findFirst("<table class=default-table>");  //find table element
				Elements trs = eTable.findEach("<tr>");

				List<Station> stations = new ArrayList<Station>();

				for(int i = 1 ; i<trs.size() ; i++){

					Elements elements = table.getRow(i);
					String stationNo = elements.getElement(0).innerHTML().trim();
					String arrivalTime = elements.getElement(2).innerHTML().trim();
					String departureTime = elements.getElement(3).innerHTML().trim();
					String stopTime = elements.getElement(4).innerHTML().trim();
					String day = "";
					String distance = elements.getElement(5).innerHTML().trim();

					String station= elements.getElement(1).innerHTML();
					String stationName = station.split("\\(")[0].trim();
					String stationCode = station.split("\\(")[1].split("\\)")[0].trim();

					//JSONObject location = station.getJSONObject("location");
					String latitude = "null";
					String longitude = "null";


					stations.add(new Station(stationName, stationCode, stationNo, arrivalTime, departureTime, stopTime, day, distance, latitude, longitude));

				}
				map.put("train_route", stations);

			} else {
				map.put("error", "No train found.");
			}

		} catch(Exception e){
			e.printStackTrace();
			map.put("error", "Network Error Occurred. Please try again.");
		}

		Gson g = new Gson();

		resp.setContentType("application/json");
		resp.getWriter().print(g.toJson(map));
*/
	}

}
