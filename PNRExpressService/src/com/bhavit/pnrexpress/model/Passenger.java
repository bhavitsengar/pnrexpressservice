/*******************************************************************************
 * Copyright (c) 2015 Bhavit Singh Sengar.
 *
 * All Rights Reserved.
 *******************************************************************************/

package com.bhavit.pnrexpress.model;

public class Passenger {
	
	String sno;
	String statusBefore;
	String statusAfter;
	public String getsno() {
		return sno;
	}
	public void setsno(String sno) {
		this.sno = sno;
	}
	public String getStatusBefore() {
		return statusBefore;
	}
	public void setStatusBefore(String statusBefore) {
		this.statusBefore = statusBefore;
	}
	public String getStatusAfter() {
		return statusAfter;
	}
	public void setStatusAfter(String statusAfter) {
		this.statusAfter = statusAfter;
	}
	public Passenger(String sno, String statusBefore, String statusAfter) {
		super();
		this.sno = sno;
		this.statusBefore = statusBefore;
		this.statusAfter = statusAfter;
	}
	
	

}
