package edu.umb.cs681.hw20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NASA {

  public static void main(String[] args) {
    String line = "";
    String splitBy = ",";
    try {
      // parsing a CSV file into BufferedReader class constructor
      BufferedReader br = new BufferedReader(new FileReader("data.csv"));
      // Create a list to store the comma separated values
      List<String[]> list = new ArrayList<String[]>();
      while ((line = br.readLine()) != null) { // returns a Boolean value
        String[] data = line.split(splitBy); // use comma as separator
        list.add(data);
        // System.out.println(data);
      }

      System.out.println("Total Size of Data: " + list.size());

      //   list.forEach(l -> System.out.println(l[0]));

      // Convert the list to a stream
      list
        .stream().parallel()
        // Filter the stream to only include the lines that contain the word "Java"
        .filter(s -> s[0].contains("Boston"))
        // Convert the stream to a list
        .map((String s[]) ->
          (
            "%s - %s - %s - %.2f".formatted(
                s[0],
                s[1],
                s[2],
                Double.parseDouble(s[3])
              )
          )
        )
        .collect(Collectors.toList());
      // Print the list
      // .forEach(System.out::println);

      ArrayList<Double> temperature = new ArrayList<Double>();
      ArrayList<Double> max_temp = new ArrayList<Double>();

      for (int i = 0; i < list.size(); i++) {
        try {
          temperature.add(Double.parseDouble(list.get(i)[5]));
        } finally {
          try {
            max_temp.add(Double.parseDouble(list.get(i)[6]));
          } finally {
            continue;
          }
        }
      }

      // Find the average of the 5th index, i.e T2M_MIN using stream
      double average = temperature
        .stream()
        // Convert the stream to a list
        .mapToDouble(Double::doubleValue)
        .average()
        .getAsDouble();

      System.out.println("Average of T2M_MIN is: %.2f".formatted(average));

      // Find the maximum of the 6th index, i.e T2M_MAX using stream
      double max = max_temp
        .stream()
        .mapToDouble(Double::doubleValue)
        .max()
        .getAsDouble();
      System.out.println("Max of T2M_MAX is: %.2f".formatted(max));

      // Find the count of data between 2019 to 2020
      long count = list
        .stream()
        // Convert the stream to a list
        // .map(s -> s.split(splitBy))
        // Filter the stream to only include the lines that contain the year 2019 or 2020
        .filter(s -> s[1].contains("2019") || s[1].contains("2020"))
        // Count the number of lines
        .count();

      System.out.println("Count of data between 2019 to 2020 is: " + count);

      // Find the count of the row, where city is Boston
      long count1 = list
        .stream()
        // Convert the stream to a list
        // .map(s -> s.split(splitBy))
        // Filter the stream to only include the lines that contain the city Boston
        .filter(s -> s[0].contains("Boston"))
        // Count the number of lines
        .count();

      System.out.println(
        "Count of the row, where city is Boston is: " + count1
      );

      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
