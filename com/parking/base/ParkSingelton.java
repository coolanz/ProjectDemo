package com.parking.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkSingelton {

	
	private static ParkSingelton park = null;
	private Vehicle[] parkingArray;
	private List<Integer> availableSlots;
	private Map<String,List<Vehicle>> colorMap;
	private Map<String,Vehicle> vehMap;
	private int current = -1;
	private int maxLimit = 0;
	private ParkSingelton (){
		
	}
	
	public static ParkSingelton getPark(){
		
		if(park == null)
			park = new ParkSingelton();
		
		return park;
	}
	
	public String createParking(int limit ){

		String message = "";

		if(parkingArray == null) {

			parkingArray = new Vehicle[limit];

			maxLimit = limit;

			current = 0;

			message = "Created a parking lot with "+limit+" slots";

		}
		else
			message = " Create can be executed only once...Already parking lot created with size "+parkingArray.length;

		return message;
	}
	
	
	
	public String parkvehicle(Vehicle vehicle){
		
		
		if(vehMap == null)
			vehMap = new HashMap<String,Vehicle>();
		
		if(colorMap == null)
			colorMap =  new HashMap<String, List<Vehicle>>();
		
		if(availableSlots == null)
			availableSlots = new ArrayList<Integer>();
		
		if(availableSlots.size() > 0){
			Collections.sort(availableSlots);
		}
		int slot = current;
		if( current >= 0 && availableSlots.size() > 0 && availableSlots.get(0) != null && current > availableSlots.get(0)) {
			slot = availableSlots.get(0);
			availableSlots.remove(0);
		}else
			current++;
		

		if(slot >= maxLimit){
			return "Sorry, parking lot is full";
		}
		
		parkingArray[slot] = vehicle;
		vehicle.setParkingSlot(slot);
		
		String color = vehicle.getColor();
		if(colorMap.get(color) != null){
			colorMap.get(color).add(vehicle); 
		}else{
			List<Vehicle> list = new ArrayList<Vehicle>();
			list.add(vehicle);
			colorMap.put(color, list);
		}
		
		
		vehMap.put(vehicle.getVehicleNumber(),vehicle);
		
		
		
		return "Allocated slot number:"+(slot+1);
	
	}
	
	
	public String leaveParking(int slot){
	
		if(slot < 1 || slot > maxLimit){
			System.out.println("Slot unavailable, exiting.");
			System.exit(0);
		}
		
		//For array indexing
		slot--;
		
		if(parkingArray[slot] == null) {
			
			return "Slot number "+(slot+1)+" is free";
			
		} else {
			
			Vehicle v = parkingArray[slot];
			
			vehMap.remove(v);
			
			if( colorMap.get(v.getColor()) != null ){
				colorMap.get(v.getColor()).remove(v);
				if(colorMap.get(v.getColor()).isEmpty())
					colorMap.remove(v.getColor());
			}
			
			parkingArray[slot] = null;
			
			v= null;

			availableSlots.add(slot);
			
			return "Slot number "+(slot+1)+" is free";
			
			
		}
		
		
	}
	
	public String getRegNumByColor(String color){
		
		String ret = "Not found";
		List<Vehicle> list = colorMap.get(color);
		if(list != null && list.size() > 0){
			ret = "";
			
			for(Vehicle v : list){
					ret+=((ret.length() != 0)?",":"")+v.getVehicleNumber();	
			}
			
		}
		
		return ret;
	}
	
	
	public String getSlotsBYColor(String color){
		
		String ret = "Not found";
		List<Vehicle> list = colorMap.get(color);
		if(list != null && list.size() > 0){
			ret = "";
			
			for(Vehicle v : list){
					ret+=((ret.length() != 0)?",":"")+(v.getSlot()+1);	
			}
			
		}
		
		return ret;
	}
	
	
	public String getSlotByRegNumber(String regNumber){
		
		String ret = "Not Found";
		if(vehMap.get(regNumber) != null){
			ret = (vehMap.get(regNumber).getSlot()+1)+"";
		}
		
		return ret;
	}
	
	public String getStatus(){
		
		if(parkingArray.length==0){
			return "";
		}
		String ret = "";
		ret = "Slot No.             Registration No.              Colour\n";
		for(Vehicle v : parkingArray){
			if(v != null)
				ret+=v.getSlot()+1+"                    "+v.getVehicleNumber()+"                 "+v.getColor()+"\n";
			
		}
		return ret;
	}
	
	public Vehicle[] getParkingList(){
		
		return parkingArray;
	
	}
}
