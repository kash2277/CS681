package edu.umb.cs681.hw11;

import java.util.ArrayList;

public final class Position {
	
	private final double latitude;
	private final double longitude;
	private final double altitude;
	
	public ArrayList<Double> getCoordinate(){
		return null;
		
	}
	
	public Position(double lat, double lon, double alt) {
		this.latitude = lat;
		this.longitude = lon;
		this.altitude = alt;
	}
	
	public double getLatitude() {
		return this.latitude;
	}
	
	public double getLongitude() {
		return this.longitude;
	}
	
	public double getAltitude() {
		return this.altitude;
	}
	
	public boolean equals(Position p1){
		if(this.toString().equals(p1.toString())){ 
			return true; 
		} 
		else{ 
			return false; 
		}
	}
	
	public String toString(){
		return "Latitude: "+this.latitude + ", Longitude: " + this.longitude + ", Altitude: " + this.altitude;
	}
	
	Position change(double newLat, double newLon, double newAlt){
		return new Position(newLat, newLon, newAlt);
	} 
	
}
