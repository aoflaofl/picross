package com.spamalot.game.picross;

import java.util.ArrayList;
import java.util.List;

class Accumulator {
  private List<Cell> acc = new ArrayList<>();
  private int numberFound = 0;

  Accumulator() {
  }

  List<Cell> update(final List<List<Cell>> possibleRows) {
    this.numberFound = 0;
    this.acc = new ArrayList<>();
    for (List<Cell> row : possibleRows) {
      updateAccumulator(row);
    }
    return this.acc;
  }

  private void updateAccumulator(final List<Cell> row) {
    this.numberFound++;
    if (this.numberFound == 1) {
      for (Cell cell : row) {
        this.acc.add(new Cell(cell.charValue()));
      }
    } else {
      for (int i = 0; i < row.size(); i++) {
        if (this.acc.get(i).charValue() != row.get(i).charValue()) {
          this.acc.get(i).setValue(CellState.UNDECIDED);
        }
      }
    }
  }

  Cell get(final int i) {
    return this.acc.get(i);
  }
}