package com.bhavit.pnrexpress.service;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class SubmitLocation extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String lat = (String) req.getParameter("lat");
		String lon = (String) req.getParameter("long");

		resp.setContentType("text/plain");
		resp.getWriter().print("Saved these location values: Latitude ="+lat+"Longitude="+lon);
	}

}
