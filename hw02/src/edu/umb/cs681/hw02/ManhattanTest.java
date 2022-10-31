package edu.umb.cs681.hw02;

import java.util.Arrays;
import java.util.List;

public class ManhattanTest {

  public static void matrixTest() {
    List<List<Double>> points = Arrays.asList(
      Arrays.asList(2.0, 3.0, 5.0),
      Arrays.asList(2.0, 5.0, 6.0),
      Arrays.asList(3.0, 4.0, 7.0)
    );
    List<List<Double>> expected = Arrays.asList(
      Arrays.asList(0.0, 3.0, 4.0),
      Arrays.asList(3.0, 0.0, 3.0),
      Arrays.asList(4.0, 3.0, 0.0)
    );
    List<List<Double>> actual = Distance.matrix(
      points,
      (List<Double> p1, List<Double> p2) -> {
        double sumOfCoordinates = 0;
        for (int i = 0; i < p1.size(); i++) {
          sumOfCoordinates += Math.abs(p1.get(i) - p2.get(i));
        }
        return sumOfCoordinates;
      }
    );

    System.out.println("points: " + points);
    System.out.println("expected: " + expected);
    System.out.println("actual: " + actual);
  }

  // intialize the get method

  public static void getTest() {
    List<Double> pt1 = Arrays.asList(2.0, 3.0, 5.0);
    List<Double> pt2 = Arrays.asList(2.0, 5.0, 6.0);
    double expected = 3;
    double actual = Distance.get(
      pt1,
      pt2,
      (List<Double> p1, List<Double> p2) -> {
        double sumOfCoordinates = 0;
        for (int i = 0; i < p1.size(); i++) {
          sumOfCoordinates += Math.abs(p1.get(i) - p2.get(i));
        }
        return sumOfCoordinates;
      }
    );

    System.out.println("expected: " + expected);
    System.out.println("actual: " + actual);
  }

  // 	Test the Manhattan metric with 5 or more 3-
  // dimensional points
  // –Distance.matrix() returns a 5 x 5 matrix.

  public static void matrixTestWith5Points() {
    List<List<Double>> points = Arrays.asList(
      Arrays.asList(2.0, 3.0, 5.0),
      Arrays.asList(2.0, 5.0, 6.0),
      Arrays.asList(3.0, 4.0, 7.0),
      Arrays.asList(4.0, 5.0, 8.0),
      Arrays.asList(5.0, 6.0, 9.0)
    );
    List<List<Double>> expected = Arrays.asList(
      Arrays.asList(0.0, 3.0, 4.0, 5.0, 6.0),
      Arrays.asList(3.0, 0.0, 3.0, 4.0, 5.0),
      Arrays.asList(4.0, 3.0, 0.0, 3.0, 4.0),
      Arrays.asList(5.0, 4.0, 3.0, 0.0, 3.0),
      Arrays.asList(6.0, 5.0, 4.0, 3.0, 0.0)
    );
    List<List<Double>> actual = Distance.matrix(
      points,
      (List<Double> p1, List<Double> p2) -> {
        double sumOfCoordinates = 0;
        for (int i = 0; i < p1.size(); i++) {
          sumOfCoordinates += Math.abs(p1.get(i) - p2.get(i));
        }
        return sumOfCoordinates;
      }
    );

    System.out.println("points: " + points);
    System.out.println("expected: " + expected);
    System.out.println("actual: " + actual);
  }

  public static void main(String[] args) {
    System.out.println("HW02");

    matrixTest();
    getTest();
    matrixTestWith5Points();
  }
}
