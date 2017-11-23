package com.park.detail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.park.util.ParkingUtil;



public class parking_lot {

	private static final String EXIT = "exit";
	public static void main(String[] args) {


		if(args == null || args.length == 0) {
			Scanner scan = new Scanner(System.in);
			String input = "";

			while(true){
				System.out.println("INPUT:");
				input = scan.nextLine();
				if(input.equalsIgnoreCase(EXIT))
					break;
				System.out.println("OUTPUT:");
				System.out.println(ParkingUtil.parseCommand(input));
			}

		} else {
			try  {
				BufferedReader br = new BufferedReader(new FileReader(args[0]));
			    String line;
			    while ((line = br.readLine()) != null) {
					System.out.println(ParkingUtil.parseCommand(line));
			    }
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}



	}
	
	
}
