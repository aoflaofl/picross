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

class PicrossRow {
  private static final Logger LOG = LoggerFactory.getLogger(PicrossRow.class);

  PicrossRow(final int size) {
    this.size = size;
  }

  private final int size;

  private int[] description;

  private final List<PicrossCell> rowCells = new ArrayList<>();

  private boolean solved;

  private List<List<PicrossCell>> possibleValues = new ArrayList<>();
  private final Accumulator accum = new PicrossRow.Accumulator();

  void addRowCell(final PicrossCell c) {
    this.rowCells.add(c);
  }

  @SuppressWarnings("boxing")
  public boolean processTheRowsData() {
    // Remove rows that can't possibly match from the possible matches list.
    Iterator<List<PicrossCell>> it = this.possibleValues.iterator();
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
      if (this.getCell(i).charValue() == PicrossCell.UNDECIDED) {
        this.solved = false;
      }
    }

    return different;
  }

  private boolean matchesEstablished(final List<PicrossCell> row) {
    for (int i = 0; i < row.size(); i++) {
      if (!(this.getCell(i).charValue() == PicrossCell.UNDECIDED
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
    this.possibleValues = PicrossPatternGenerator.generateAllPossiblePatterns(this.size, this.description);
  }

  public int getSize() {
    return this.size;
  }

  public int[] getDescription() {
    return this.description;
  }

  private PicrossCell getCell(final int i) {
    return this.rowCells.get(i);
  }

  private class Accumulator {
    private List<PicrossCell> acc = new ArrayList<>();
    private int numberFound = 0;

    Accumulator() {
    }

    private List<PicrossCell> update(final List<List<PicrossCell>> possibleRows) {
      this.numberFound = 0;
      this.acc = new ArrayList<>();
      for (List<PicrossCell> row : possibleRows) {
        updateAccumulator(row);
      }
      return this.acc;
    }

    private void updateAccumulator(final List<PicrossCell> row) {
      this.numberFound++;
      if (this.numberFound == 1) {
        for (PicrossCell cell : row) {
          this.acc.add(new PicrossCell(cell.charValue()));
        }
      } else {
        for (int i = 0; i < row.size(); i++) {
          if (this.acc.get(i).charValue() != row.get(i).charValue()) {
            this.acc.get(i).setValue(PicrossCell.UNDECIDED);
          }
        }
      }
    }

    PicrossCell get(final int i) {
      return this.acc.get(i);
    }
  }
}
