package edu.umb.cs681.hw04;

import java.beans.Transient;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Stream;

class CarTest {

  private String[] carToStringArray(Car car) {
    String[] carStringArray = new String[3];
    carStringArray[0] = car.getMake();
    carStringArray[1] = car.getModel();
    carStringArray[2] = car.getYear() + "";
    return carStringArray;
  }

  public void verifyCarEqualityWithMakeModelYear() {
    String[] expected = { "Dodge", "Challenger", "2020" };
    Car actual = new Car("Dodge", "Challenger", 2020);
  }

  // 	Do the same (i.e., sort cars) with Stream API.
  // – c.f. Stream.sorted()
  // implement 4 different

  // implementing the sorting of cars using the Stream API
  public void verifyCarSortingWithMakeModelYear() {
    Car[] cars = {
      new Car("Dodge", "Challenger", 2020),
      new Car("Ford", "Mustang", 2019),
      new Car("Chevrolet", "Camaro", 2018),
    };
    Car[] expected = {
      new Car("Chevrolet", "Camaro", 2018),
      new Car("Dodge", "Challenger", 2020),
      new Car("Ford", "Mustang", 2019),
    };
    // sort the cars
    Stream<Car> carStream = Stream.of(cars);
    Stream<Car> sortedCarStream = carStream.sorted(
      Comparator.comparing(Car::getMake)
    );
    Car[] actual = sortedCarStream.toArray(Car[]::new);
    
    System.out.println("cars: " + cars);
    System.out.println("expected: " + expected);
    System.out.println("actual: " + actual);
  }

  // • Find the median price, year, mileage and domination count
  public void verifyCarStatistics() {
    Car[] cars = {
      new Car("Dodge", "Challenger", 2020, 10000, 10000),
      new Car("Ford", "Mustang", 2019, 20000, 20000),
      new Car("Chevrolet", "Camaro", 2018, 30000, 30000),
    };
    // find the median price
    Stream<Car> carStream = Stream.of(cars);
    Stream<Float> priceStream = carStream.map((Car car) -> car.getPrice());
    // find the median year
    carStream = Stream.of(cars);
    Stream<Integer> yearStream = carStream.map((Car car) -> car.getYear());
    OptionalDouble medianYear = yearStream
      .mapToInt((Integer year) -> year)
      .average();
    if (2019 == medianYear.getAsDouble()) {
      System.out.println("Test Successful.");
    }
    // find the median mileage
    carStream = Stream.of(cars);
    Stream<Integer> mileageStream = carStream.map((Car car) -> car.getMileage()
    );
    OptionalDouble medianMileage = mileageStream
      .mapToInt((Integer mileage) -> mileage)
      .average();
    if (20000 == medianMileage.getAsDouble()) {
      System.out.println("Test Successful.");
    }
    // find the median domination count
    carStream = Stream.of(cars);
    Stream<Integer> dominationCountStream = carStream.map((Car car) ->
      car.getDominationCount()
    );
    OptionalDouble medianDominationCount = dominationCountStream
      .mapToInt((Integer dominationCount) -> dominationCount)
      .average();
    if (0 == medianDominationCount.getAsDouble()) {
      System.out.println("Test Successful.");
    }
  }

  public static void main(String[] args) {
    System.out.println("HW04");
    CarTest ct = new CarTest();
    ct.verifyCarSortingWithMakeModelYear();
    ct.verifyCarStatistics();    

  }
}
