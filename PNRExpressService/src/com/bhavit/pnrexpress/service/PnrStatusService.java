package com.bhavit.pnrexpress.service;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class PnrStatusService extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String pnr = (String) req.getParameter("pnr");

		HashMap<Object, Object> map = new HashMap<Object, Object>();
	}

}
