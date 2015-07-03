package com.bhavit.pnrexpress.service;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bhavit.pnrexpress.util.Helper;
import com.bhavit.pnrexpress.util.RestClient;

@SuppressWarnings("serial")
public class SeatAvailabilityService  extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		Helper.setProxy();
		
		String tnum = (String) req.getParameter("tnum");
		String from = (String) req.getParameter("from");
		String to = (String) req.getParameter("to");
		String date = (String) req.getParameter("date");
		String classs = (String) req.getParameter("class");
		String quota = (String) req.getParameter("quota");

		RestClient client = new RestClient(
				"http://api.railwayapi.com/check_seat/train/"+tnum+"/source/"+from+"/dest/"+to+"/date/"+date+"/class/"+classs+"/quota/"+quota+"/apikey/"+Helper.apikey);
		client.addHeader("Content-Type",
				"application/x-www-form-urlencoded");

		String result = client.executeGet();
		System.out.println(result);

		resp.setContentType("application/json");
		resp.getWriter().print(result);
	}

}
