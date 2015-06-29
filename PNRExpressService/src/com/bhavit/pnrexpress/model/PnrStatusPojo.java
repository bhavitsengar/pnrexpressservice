/*******************************************************************************
 * Copyright (c) 2015 Bhavit Singh Sengar.
 *
 * All Rights Reserved.
 *******************************************************************************/

package com.bhavit.pnrexpress.model;

import java.util.List;

public class PnrStatusPojo {

	String pnr;
	String trainNo;
	String trainName;
	String doj;
	String originStationCode;
	String originStationName;
	String lastStationCode;
	String lastStationname;
	String boardingPointCode;
	String boardingPointName;
	String reservationUptoCode;
	String reservationUptoName;
	String reservationClass;
	int numberOfPassengers;
	String chart;
	List<Passenger> passengers;

	public PnrStatusPojo(String pnr, String trainNo, String trainName,
			String doj, String originStationCode, String originStationName,
			String lastStationCode, String lastStationname,
			String boardingPointCode, String boardingPointName,
			String reservationUptoCode, String reservationUptoName,
			String reservationClass, int numberOfPassengers, String chart,
			List<Passenger> passengers) {
		super();
		this.pnr = pnr;
		this.trainNo = trainNo;
		this.trainName = trainName;
		this.doj = doj;
		this.originStationCode = originStationCode;
		this.originStationName = originStationName;
		this.lastStationCode = lastStationCode;
		this.lastStationname = lastStationname;
		this.boardingPointCode = boardingPointCode;
		this.boardingPointName = boardingPointName;
		this.reservationUptoCode = reservationUptoCode;
		this.reservationUptoName = reservationUptoName;
		this.reservationClass = reservationClass;
		this.numberOfPassengers = numberOfPassengers;
		this.chart = chart;
		this.passengers = passengers;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public String getTrainNo() {
		return trainNo;
	}

	public void setTrainNo(String trainNo) {
		this.trainNo = trainNo;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public String getOriginStationCode() {
		return originStationCode;
	}

	public void setOriginStationCode(String originStationCode) {
		this.originStationCode = originStationCode;
	}

	public String getOriginStationName() {
		return originStationName;
	}

	public void setOriginStationName(String originStationName) {
		this.originStationName = originStationName;
	}

	public String getLastStationCode() {
		return lastStationCode;
	}

	public void setLastStationCode(String lastStationCode) {
		this.lastStationCode = lastStationCode;
	}

	public String getLastStationname() {
		return lastStationname;
	}

	public void setLastStationname(String lastStationname) {
		this.lastStationname = lastStationname;
	}

	public String getBoardingPointCode() {
		return boardingPointCode;
	}

	public void setBoardingPointCode(String boardingPointCode) {
		this.boardingPointCode = boardingPointCode;
	}

	public String getBoardingPointName() {
		return boardingPointName;
	}

	public void setBoardingPointName(String boardingPointName) {
		this.boardingPointName = boardingPointName;
	}

	public String getReservationUptoCode() {
		return reservationUptoCode;
	}

	public void setReservationUptoCode(String reservationUptoCode) {
		this.reservationUptoCode = reservationUptoCode;
	}

	public String getReservationUptoName() {
		return reservationUptoName;
	}

	public void setReservationUptoName(String reservationUptoName) {
		this.reservationUptoName = reservationUptoName;
	}

	public String getReservationClass() {
		return reservationClass;
	}

	public void setReservationClass(String reservationClass) {
		this.reservationClass = reservationClass;
	}

	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}

	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}

	public String getChart() {
		return chart;
	}

	public void setChart(String chart) {
		this.chart = chart;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

}
