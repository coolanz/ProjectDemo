package com.park.util;

import com.park.detail.Car;
import com.parking.base.ParkSingelton;
import com.parking.base.Vehicle;

public class ParkingUtil {

	public static final String SPACE_SYMBOL = " ";
	private static final String CREATE = "create_parking_lot";
	private static final String PARK = "park";
	private static final String STATUS = "status";
	private static final String LEAVE = "leave";
	private static final String NUMBERS_BY_COLOR = "registration_numbers_for_cars_with_colour";
	private static final String SLOTS_BY_NUMBER = "slot_number_for_registration_number";
	private static final String SLOTS_BY_COLOR = "slot_numbers_for_cars_with_colour";
	
	
	public static String parseCommand(String input) {
		String[] sArray = null;
		String command = null;
		String param = null;
		
		if((input != null && input.contains(SPACE_SYMBOL))) {
			sArray = input.split(SPACE_SYMBOL);
			command = input.substring(0, input.indexOf(SPACE_SYMBOL));
			param = input.substring(input.indexOf(SPACE_SYMBOL));
		} else {
			command = input;
		}
			String message;
			switch (command) {
			
				case CREATE:
					int limit = 0;
					try{
						
						limit = Integer.parseInt(param.trim());
						
					}catch(NumberFormatException e){
						
						message = "Invalid number passed to the command, please provide a valid input.";			
					
						return message;
						
					}
					return ParkSingelton.getPark().createParking(limit);
					
				case STATUS:
					return ParkSingelton.getPark().getStatus();
					
				case LEAVE:
					int index = 0;
					try{
						
						index = Integer.parseInt(param.trim());
						
					}catch(NumberFormatException e){
						
						message = "Invalid number passed to the command, please provide a valid input.";			
					
						return message;
						
					}
					return ParkSingelton.getPark().leaveParking(index);
					
				case PARK:
					Vehicle vehicle = null; 
					if(param.contains(SPACE_SYMBOL)){
						String[] array = param.split(SPACE_SYMBOL);
						String registrationNumber = array[1];
						String color = array[2];
						vehicle = new Car(registrationNumber,color);
					}else{
						message = "Invalid parameter passed to Park command.";
						return message;
					}
					return ParkSingelton.getPark().parkvehicle(vehicle);
					
				case NUMBERS_BY_COLOR:

					return ParkSingelton.getPark().getRegNumByColor(param.trim());
					
				case SLOTS_BY_COLOR:

					return ParkSingelton.getPark().getSlotsBYColor(param.trim());
					
				case SLOTS_BY_NUMBER:
					
					return ParkSingelton.getPark().getSlotByRegNumber(param.trim());
					
				default : 
					
					return "Not Found";
			}

		

	}
	
}
