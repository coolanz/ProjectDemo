package com.park.detail;

import com.parking.base.Vehicle;

public class Car implements Vehicle{


	private String regNum;
	private String color;
	private int slot;
	
	public Car( String regNum, String color) {
	
		this.regNum = regNum;
		this.color = color;
	}


	@Override
	public String getColor() {
		return color;
	}


	@Override
	public String getVehicleNumber() {
		return regNum;
	}


	@Override
	public void setParkingSlot(int slot) {
		this.slot=slot;
	}


	@Override
	public int getSlot() {
		return slot;
	}
	
	
}
