package edu.umb.cs681.hw03;

public class Car {
	private String make, model;
	private int mileage, year;
	private float price;
	private int dominationCount;
	
	public Car(String make, String model, int year) {
		super();
		this.make = make;
		this.model = model;
		this.year = year;
	}

	public Car(String make, String model, int year, int mileage, int dominationCount) {
		super();
		this.make = make;
		this.model = model;
		this.mileage = mileage;
		this.year = year;
		this.dominationCount = dominationCount;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getYear() {
		return year;
	}

	public int getMileage() {
		return mileage;
	}

	public int getDominationCount() {
		return dominationCount;
	}

	public float getPrice() {
		return price;
	}
	
}