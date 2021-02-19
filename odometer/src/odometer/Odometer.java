package odometer;

import java.util.Iterator;

public class Odometer implements Iterator {
  int size;
  int max;
  int[] counters;
  private int total;

  public Odometer(int numCounters, int maxValue, int total) {
    this.size = numCounters;
    this.max = maxValue;
    this.total = total;

    initCounters(this.size);
  }

  public boolean spam() {
    boolean ret = false;
    do {
      ret = advance();
    } while (!validSum());
    return ret;
  }

  private boolean validSum() {
    int sum = 0;
    for (int i = 0; i < size; i++) {
      sum += counters[i];
    }
    return sum == total;
  }

  private boolean advance() {
    boolean rollOver = true;
    for (int i = size - 1; rollOver; i--) {
      System.out.println(i);
      if (i == -1) {
        return false;
      }
      rollOver = incrementWithRolloverCheck(i);
    }
    return true;
  }

  private boolean incrementWithRolloverCheck(int i) {
    boolean rollOver = false;

    counters[i]++;
    if (counters[i] > max) {
      rollOver = true;
      if (i == 0) {
        counters[i] = 0;
      } else {
        counters[i] = 1;
      }
    }
    return rollOver;
  }

  private void initCounters(int numCounters) {
    this.counters = new int[numCounters];
    this.counters[0] = 0;
    for (int i = 1; i < numCounters; i++) {
      this.counters[i] = 1;
    }
  }

  @Override
  public boolean hasNext() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Object next() {
    // TODO Auto-generated method stub
    return null;
  }

}
