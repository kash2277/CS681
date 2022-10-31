package edu.umb.cs681.hw03;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CarTest {


  static List<Car> cars = new ArrayList<Car>();

  static {
    cars.add(new Car("Dodge", "Challenger", 2019, 56, 1));
    cars.add(new Car("Prado", "Challenger", 2020, 789, 1));
    cars.add(new Car("Benz", "Challenger", 2021, 34567, 1));
    cars.add(new Car("Mercedeez", "Challenger", 2022, 45678, 1));
  }

  private String[] carToStringArray(Car car) {
    String[] carStringArray = new String[3];
    carStringArray[0] = car.getMake();
    carStringArray[1] = car.getModel();
    carStringArray[2] = car.getYear() + "";
    return carStringArray;
  }

  public void verifyCarEqualityWithMakeModelYear() {
    String[] expected = { "Dodge", "Challenger", "2020" };
    Car actual = new Car("Dodge", "Challenger", 2020, 456, 123);
  }

  public static void main(String[] args) {




  List<Car> sortedByMake = cars
    .stream()
    .sorted((o1, o2) -> o1.getMake().compareToIgnoreCase(o2.getMake()))
    .collect(Collectors.toList());
  List<Car> sortedByModel = cars
    .stream()
    .sorted((o1, o2) -> o1.getModel().compareToIgnoreCase(o2.getModel()))
    .collect(Collectors.toList());
  List<Car> sortedByPrice = cars
    .stream()
    .sorted((o1, o2) -> Float.compare(o1.getPrice(), o2.getPrice()))
    .collect(Collectors.toList());
  List<Car> sortedByYear = cars
    .stream()
    .sorted((o1, o2) -> Integer.compare(o1.getYear(), o2.getYear()))
    .collect(Collectors.toList());

  Float total = cars
    .parallelStream()
    .map(car -> car.getPrice())
    .reduce((float) 0.0, Float::sum);
  Float average = total / cars.size();

  // HW4
  Optional<Car> max = cars.stream().max(Comparator.comparing(Car::getMileage));
  Optional<Car> min = cars.stream().min(Comparator.comparing(Car::getMileage));

  Optional<Car> maxYear = cars.stream().max(Comparator.comparing(Car::getYear));
  Optional<Car> minYear = cars.stream().min(Comparator.comparing(Car::getYear));

    System.out.println("Max Mileage: " + max.get().getMileage());
    System.out.println("Min Mileage: " + min.get().getMileage());

    System.out.println("Max Year: " + maxYear.get().getYear());
    System.out.println("Min Year: " + minYear.get().getYear());
  }
}
