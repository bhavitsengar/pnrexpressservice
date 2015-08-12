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
public class PnrStatusService extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String pnr = (String) req.getParameter("pnr");

		String url = "http://railpnrapi.com/api/check_pnr/pnr/"
				+ pnr
				+ "/format/json/pbapikey/"
				+ Helper.publicapikey
				+ "/pbapisign/"
				+ HMACGenarator.getSignature(Helper.privateapikey, "json"
						+ Helper.publicapikey + pnr);

		RestClient client = new RestClient(url);

		System.out.println(url);

		String result = client.executeGet();
		System.out.println(result);

		resp.setContentType("application/json");
		resp.getWriter().print(result);

		/**********************************************************************************************************************/

		/*
		 * 
		 * String pnr = (String) req.getParameter("pnr");
		 * 
		 * HashMap<Object, Object> map = new HashMap<Object, Object>();
		 * 
		 * try { RestClient client = new RestClient(
		 * "http://pnrbuddy.com/hauth/pnrstatus");
		 * client.addHeader("Content-Type",
		 * "application/x-www-form-urlencoded"); client.addStringBody("pnr=" +
		 * pnr);
		 * 
		 * String result = client.executePost(); System.out.println(result);
		 * 
		 * UserAgent userAgent = new UserAgent();
		 * 
		 * userAgent.openContent(result);
		 * 
		 * Table table = userAgent.doc.getTable(0); // find table element
		 * 
		 * Elements elements = table.getRow(1);
		 * 
		 * final String trainNo = elements.getElement(0).findFirst("<a>")
		 * .innerHTML().trim(); final String trainName =
		 * elements.getElement(1).findFirst("<a>") .innerHTML().trim(); final
		 * String doj = elements.getElement(2).innerHTML().split("\\:")[1]
		 * .trim();
		 * 
		 * elements = table.getRow(2);
		 * 
		 * final String fromCode = elements.getElement(0).findFirst("<a>")
		 * .innerHTML().split("\\(")[0].trim(); final String fromName =
		 * elements.getElement(0).findFirst("<a>")
		 * .innerHTML().split("\\(")[1].split("\\)")[0]; final String toCode =
		 * elements.getElement(1).findFirst("<a>")
		 * .innerHTML().split("\\(")[0].trim(); final String toName =
		 * elements.getElement(1).findFirst("<a>")
		 * .innerHTML().split("\\(")[1].split("\\)")[0]; final String
		 * reservationUptoCode = elements.getElement(2)
		 * .findFirst("<a>").innerHTML().split("\\(")[0].trim(); final String
		 * reservationUptoName = elements.getElement(2)
		 * .findFirst("<a>").innerHTML().split("\\(")[1].split("\\)")[0];
		 * 
		 * elements = table.getRow(3);
		 * 
		 * final String boardingPointCode = elements.getElement(0)
		 * .findFirst("<a>").innerHTML().split("\\(")[0].trim(); final String
		 * boardingPointName = elements.getElement(0)
		 * .findFirst("<a>").innerHTML().split("\\(")[1].split("\\)")[0]; final
		 * String reservationClass = elements.getElement(1).innerHTML()
		 * .split("\\:")[1].trim(); int noOfPassengers =
		 * Integer.parseInt(elements.getElement(2)
		 * .innerHTML().split("\\:")[1].trim());
		 * 
		 * elements = table.getRow(4);
		 * 
		 * final String chart = elements.getElement(0).innerHTML()
		 * .split("\\:")[1].trim();
		 * 
		 * table = userAgent.doc.getTable(1);
		 * 
		 * String serial; String bookingStatus; String currentStatus; final
		 * List<Passenger> listPassengers = new ArrayList<Passenger>(); for (int
		 * i = 1; i <= noOfPassengers; i++) {
		 * 
		 * elements = table.getRow(i);
		 * 
		 * serial = (i) + ""; bookingStatus =
		 * elements.getElement(1).innerHTML().trim(); currentStatus =
		 * elements.getElement(2).innerHTML().trim();
		 * 
		 * listPassengers.add(new Passenger(serial, bookingStatus,
		 * currentStatus)); }
		 * 
		 * map.put("pnrstatus", new PnrStatusPojo(pnr, trainNo, trainName, doj,
		 * fromCode, fromName, toCode, toName, boardingPointCode,
		 * boardingPointName, reservationUptoCode, reservationUptoName,
		 * reservationClass, noOfPassengers, chart, listPassengers));
		 * 
		 * }catch(NoSuchElementException e){ map.put("error",
		 * "Pnr number is either expired or doesn't exist.");
		 * 
		 * } catch (Exception e) { e.printStackTrace(); map.put("error",
		 * "Network Error Occurred. Please try again."); }
		 * 
		 * Gson g = new Gson();
		 * 
		 * resp.setContentType("application/json");
		 * resp.getWriter().print(g.toJson(map));
		 */}

}
