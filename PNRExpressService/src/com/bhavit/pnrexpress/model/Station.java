/*******************************************************************************
 * Copyright (c) 2015 Bhavit Singh Sengar.
 *
 * All Rights Reserved.
 *******************************************************************************/

package com.bhavit.pnrexpress.model;

public class Station {

	private String stationName;
	private String stationCode;
	private String stationNo;
	private String arrivalTime;
	private String departureTime;
	private String stopTime;
	private String day;
	private String distance;
	private String latitude;
	private String longitude;

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getStopTime() {
		return stopTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Station(String stationName, String stationCode, String stationNo,
			String arrivalTime, String departureTime, String stopTime,
			String day, String distance, String latitude, String longitude) {
		super();

		this.stationName = stationName;
		this.stationCode = stationCode;
		this.stationNo = stationNo;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.stopTime = stopTime;
		this.day = day;
		this.distance = distance;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Station() {
		
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

}
