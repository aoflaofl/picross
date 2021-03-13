package com.spamalot.game.picross;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Odometer {
  private final int size;
  private int[] counters;
  private final int total;
  private int curTotal;

  Odometer(final int numCounters, final int total) {
    this.size = numCounters;
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

  private boolean incrementWithRolloverCheck(final int i) {
    boolean rollOver = false;

    this.counters[i]++;
    this.curTotal++;

    if (this.curTotal > this.total) {
      this.curTotal = this.curTotal - this.counters[i] + 1;
      rollOver = true;
      if (i == 0) {
        this.counters[i] = 0;
      } else {
        this.counters[i] = 1;
      }
    }
    return rollOver;
  }

  private void initCounters(final int numCounters) {
    this.counters = new int[numCounters];
    this.counters[0] = 0;
    for (int i = 1; i < numCounters; i++) {
      this.counters[i] = 1;
    }
  }

  List<List<Integer>> makeList() {
    List<List<Integer>> s = new ArrayList<>();

    do {
      // System.out.println(Arrays.toString(this.counters) + " total: " +
      // this.curTotal);
      ArrayList<Integer> integerArray = (ArrayList<Integer>) Arrays.stream(this.counters).boxed()
          .collect(Collectors.toList());
      s.add(integerArray);
    } while (advance());

    return s;
  }

}
