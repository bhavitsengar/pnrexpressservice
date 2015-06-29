/*******************************************************************************
 * Copyright (c) 2015 Bhavit Singh Sengar.
 *
 * All Rights Reserved.
 *******************************************************************************/

package com.bhavit.pnrexpress.model;

public class LiveStatusPojo {

	String station;
	String platform;
	String scheduledArrival;
	String scheduledDeparture;
	String actualArrival;
	String actualDeparture;
	String trainStatus;
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getScheduledArrival() {
		return scheduledArrival;
	}
	public void setScheduledArrival(String scheduledArrival) {
		this.scheduledArrival = scheduledArrival;
	}
	public String getScheduledDeparture() {
		return scheduledDeparture;
	}
	public void setScheduledDeparture(String scheduledDeparture) {
		this.scheduledDeparture = scheduledDeparture;
	}
	public String getActualArrival() {
		return actualArrival;
	}
	public void setActualArrival(String actualArrival) {
		this.actualArrival = actualArrival;
	}
	public String getActualDeparture() {
		return actualDeparture;
	}
	public void setActualDeparture(String actualDeparture) {
		this.actualDeparture = actualDeparture;
	}
	public String getTrainStatus() {
		return trainStatus;
	}
	public void setTrainStatus(String trainStatus) {
		this.trainStatus = trainStatus;
	}
	public LiveStatusPojo(String station, String platform,
			String scheduledArrival, String scheduledDeparture,
			String actualArrival, String actualDeparture, String trainStatus) {
		super();
		this.station = station;
		this.platform = platform;
		this.scheduledArrival = scheduledArrival;
		this.scheduledDeparture = scheduledDeparture;
		this.actualArrival = actualArrival;
		this.actualDeparture = actualDeparture;
		this.trainStatus = trainStatus;
	}
	
}
