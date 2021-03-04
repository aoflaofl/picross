package com.spamalot.game.picross;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Even if it is a column, it's going to be called a Row because Row has fewer
 * letters than Column. Plus it's all a matter of perspective, anyway.
 *
 * @author gej
 *
 */

class Row {
  private static final Logger LOG = LoggerFactory.getLogger(Row.class);

  private final Accumulator accum = new Row.Accumulator();

  private int[] description;

  private List<List<Cell>> possibleValues = new ArrayList<>();

  private final List<Cell> rowCells = new ArrayList<>();

  private final int size;

  private boolean solved;

  Row(final int size) {
    this.size = size;
  }

  void addRowCell(final Cell c) {
    this.rowCells.add(c);
  }

  private Cell getCell(final int i) {
    return this.rowCells.get(i);
  }

  public int[] getDescription() {
    return this.description;
  }

  public int getSize() {
    return this.size;
  }

  boolean isSolved() {
    return this.solved;
  }

  private boolean matchesEstablished(final List<Cell> row) {
    for (int i = 0; i < row.size(); i++) {
      if (!(this.getCell(i).charValue() == Cell.UNDECIDED || this.getCell(i).charValue() == row.get(i).charValue())) {
        return false;
      }
    }
    return true;
  }

  public boolean processTheRowsData() {
    // Remove rows that can't possibly match from the possible matches list.
    final Iterator<List<Cell>> it = this.possibleValues.iterator();
    while (it.hasNext()) {
      if (!matchesEstablished(it.next())) {
        it.remove();
      }
    }

    this.accum.update(this.possibleValues);

    boolean different = false;
    this.solved = true;
    for (int i = 0; i < this.size; i++) {
      if (this.getCell(i).charValue() != this.accum.get(i).charValue()) {
        different = true;
        this.getCell(i).setValue(this.accum.get(i).charValue());
      }
      if (this.getCell(i).charValue() == Cell.UNDECIDED) {
        this.solved = false;
      }
    }

    return different;
  }

  @SuppressWarnings("boxing")
  public void setDescription(final List<Integer> is) {
    this.description = is.stream().mapToInt(i -> i).toArray();
    this.possibleValues = PatternGenerator.generateAllPossiblePatterns(this.size, this.description);
  }

  private class Accumulator {
    private List<Cell> acc = new ArrayList<>();
    private int numberFound = 0;

    Accumulator() {
    }

    Cell get(final int i) {
      return this.acc.get(i);
    }

    private List<Cell> update(final List<List<Cell>> possibleRows) {
      this.numberFound = 0;
      this.acc = new ArrayList<>();
      for (final List<Cell> row : possibleRows) {
        updateAccumulator(row);
      }
      return this.acc;
    }

    private void updateAccumulator(final List<Cell> row) {
      this.numberFound++;
      if (this.numberFound == 1) {
        for (final Cell cell : row) {
          this.acc.add(new Cell(cell.charValue()));
        }
      } else {
        for (int i = 0; i < row.size(); i++) {
          if (this.acc.get(i).charValue() != row.get(i).charValue()) {
            this.acc.get(i).setValue(Cell.UNDECIDED);
          }
        }
      }
    }
  }
}
