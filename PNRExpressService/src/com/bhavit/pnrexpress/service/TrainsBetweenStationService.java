/*******************************************************************************
 * Copyright (c) 2015 Bhavit Singh Sengar.
 *
 * All Rights Reserved.
 *******************************************************************************/

package com.bhavit.pnrexpress.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bhavit.pnrexpress.model.Train;
import com.bhavit.pnrexpress.util.HMACGenarator;
import com.bhavit.pnrexpress.util.Helper;
import com.bhavit.pnrexpress.util.RestClient;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.UserAgent;
import com.jaunt.component.Table;

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
						+ (classs.equalsIgnoreCase("all") ? "" : "/class/")
						+ (classs.equalsIgnoreCase("all") ? "" : classs)
						+ "/orderby/time/format/json/pbapikey/"
						+ Helper.publicapikey
						+ "/pbapisign/"
						+ HMACGenarator.getSignature(Helper.privateapikey,
								((classs.equalsIgnoreCase("all") ? "" : classs)
										+ date + "json" + from + "time"
										+ Helper.publicapikey + to)
										.toLowerCase()));

		String result = client.executeGet();
		System.out.println(result);

		resp.setContentType("application/json");
		resp.getWriter().print(result);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
