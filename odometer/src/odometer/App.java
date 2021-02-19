package odometer;

import java.util.Arrays;

public class App {
  public static void main(String... args) {
    // For a description like [1, 2, 1] with size 8
    Odometer o = new Odometer(3, 2, 4);

    while (o.spam()) {
      System.out.println(Arrays.toString(o.counters));
    }

  }

}
