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

  Row(final int size) {
    this.size = size;
  }

  private final int size;

  private int[] description;

  private final List<Cell> rowCells = new ArrayList<>();

  private boolean solved;

  private List<List<Cell>> possibleValues = new ArrayList<>();
  private final Accumulator accum = new Accumulator();

  void addRowCell(final Cell c) {
    this.rowCells.add(c);
  }

  public boolean processTheRowsData() {
    // Remove rows that can't possibly match from the possible matches list.
    Iterator<List<Cell>> it = this.possibleValues.iterator();
    while (it.hasNext()) {
      if (!matchesEstablished(it.next())) {
        it.remove();
      }
    }

    // LOG.info("Possible values remaining: {}", this.possibleValues.size());

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

  private boolean matchesEstablished(final List<Cell> row) {
    for (int i = 0; i < row.size(); i++) {
      if (!(this.getCell(i).charValue() == Cell.UNDECIDED
          || this.getCell(i).charValue() == row.get(i).charValue())) {
        return false;
      }
    }
    return true;
  }

  boolean isSolved() {
    return this.solved;
  }

  @SuppressWarnings("boxing")
  public void setDescription(final List<Integer> is) {
    this.description = is.stream().mapToInt(i -> i).toArray();
    this.possibleValues = PatternGenerator.generateAllPossiblePatterns(this.size, this.description);
  }

  public int getSize() {
    return this.size;
  }

  public int[] getDescription() {
    return this.description;
  }

  private Cell getCell(final int i) {
    return this.rowCells.get(i);
  }
}
