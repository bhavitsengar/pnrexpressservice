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

import com.bhavit.pnrexpress.util.Helper;
import com.bhavit.pnrexpress.util.RestClient;


@SuppressWarnings("serial")
public class PnrStatusServiceNew extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		String pnr = (String) req.getParameter("pnr");

		
			RestClient client = new RestClient(
					"http://api.railwayapi.com/pnr_status/pnr/"+pnr+"/apikey/"+Helper.apikey);
			client.addHeader("Content-Type",
					"application/x-www-form-urlencoded");

			String result = client.executeGet();
			System.out.println(result);

			resp.setContentType("application/json");
			resp.getWriter().print(result);
			
	}

}
