package com.spamalot.game.picross;

import java.util.Arrays;

class Odometer {
  private final int size;
  private final int max;
  private int[] counters;
  private final int total;
  private int curTotal;

  Odometer(int numCounters, int maxValue, int total) {
    this.size = numCounters;
    this.max = maxValue;
    this.total = total;

    this.curTotal = numCounters - 1;
    initCounters(this.size);
  }

  private boolean advance() {
    boolean rollOver = true;
    for (int i = this.size - 1; rollOver; i--) {
      if (i == -1) {
        return false;
      }
      rollOver = incrementWithRolloverCheck(i);
    }
    return true;
  }

  private boolean incrementWithRolloverCheck(int i) {
    boolean rollOver = false;

    this.counters[i]++;
    this.curTotal++;
    if (this.counters[i] > this.max) {
      this.curTotal -= this.max;
      rollOver = true;
      if (i == 0) {
        this.counters[i] = 0;
      } else {
        this.counters[i] = 1;
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

  void makeList() {
    if (this.curTotal <= this.total) {
      System.out.println(Arrays.toString(this.counters) + " total: " + this.curTotal);
    }
    while (advance()) {
      if (this.curTotal <= this.total) {
        System.out.println(Arrays.toString(this.counters) + " total: " + this.curTotal);
      }
    }

  }

}
