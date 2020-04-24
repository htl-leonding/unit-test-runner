package at.htl.runner;

import java.util.Scanner;

public class InOutFaker {

  public void out() {
    System.out.println("Hello World!");
  }

  public String in() {
    try (Scanner scanner = new Scanner(System.in)) {
      String input = scanner.next();
      System.out.println("in() -> " + input);
      return input;
    }
  }

  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      String input = scanner.next();
      System.out.print(input);
    }
  }
}
