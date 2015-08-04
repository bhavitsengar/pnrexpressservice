package com.bhavit.pnrexpress.service;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bhavit.pnrexpress.util.HMACGenarator;
import com.bhavit.pnrexpress.util.Helper;
import com.bhavit.pnrexpress.util.RestClient;

@SuppressWarnings("serial")
public class SeatAvailabilityService extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String tnum = (String) req.getParameter("tnum");
		String from = (String) req.getParameter("from");
		String to = (String) req.getParameter("to");
		String date = (String) req.getParameter("date");
		String classs = (String) req.getParameter("class");
		String quota = (String) req.getParameter("quota");

		String string = (classs + date + "json" + from + Helper.publicapikey
				+ quota + tnum + to).toLowerCase();
		System.out.println(string);

		String url = "http://railpnrapi.com/api/check_avail/tnum/" + tnum
				+ "/fscode/" + from + "/tscode/" + to + "/date/" + date
				+ "/class/" + classs + "/quota/" + quota
				+ "/format/json/pbapikey/" + Helper.publicapikey
				+ "/pbapisign/"
				+ HMACGenarator.getSignature(Helper.privateapikey, string);
		RestClient client = new RestClient(url);

		String result = client.executeGet();

		System.out.println(url);
		System.out.println(result);

		resp.setContentType("application/json");
		resp.getWriter().print(result);
	}

}
