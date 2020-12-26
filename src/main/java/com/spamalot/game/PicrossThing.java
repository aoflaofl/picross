package com.spamalot.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class PicrossThing {
  private static final Logger LOG = LoggerFactory.getLogger(PicrossThing.class);

  private List<PicrossCell> accumulator;

  private int numberFound = 0;
  private PicrossRow workingRow;

  public static int rowsMade = 0;

  public PicrossThing(PicrossRow row) {
    this.workingRow = row;
  }

  List<PicrossCell> doThePicrossThing() {
    int openCells = this.workingRow.getSize() - IntStream.of(this.workingRow.getDescription()).sum();
    int[] spaces = new int[this.workingRow.getDescription().length];
    this.numberFound = 0;

    recursion(0, spaces, openCells);

    return this.accumulator;
  }

  private void recursion(int level, int[] spaces, int openPlaces) {
    if (level == this.workingRow.getDescription().length) {
      List<PicrossCell> row = buildRow(spaces);

      this.workingRow.addToPossibleValues(row);

      if (matchesEstablished(row)) {
        updateAccumulator(row);
      }
      return;
    }
    for (int i = level == 0 ? 0 : 1; i <= openPlaces; i++) {
      spaces[level] = i;
      recursion(level + 1, spaces, openPlaces - i);
    }
  }

  private void updateAccumulator(List<PicrossCell> row) {
    this.numberFound++;
    if (this.numberFound == 1) {
     // LOG.info("First One");
      this.accumulator = row;
    } else {
    //  LOG.info("Another one {}", this.numberFound);
      for (int i = 0; i < row.size(); i++) {
        if (this.accumulator.get(i).charValue() != row.get(i).charValue()) {
          this.accumulator.get(i).setValue(PicrossCell.UNDECIDED);
        }
      }
    }
    //LOG.info("Acc: {}", this.accumulator);
  }

  /**
   * Build a list of row cells using a list of space lengths and the row's
   * description.
   * 
   * @param spaces
   * @return
   */
  private List<PicrossCell> buildRow(int[] spaces) {
    List<PicrossCell> ret = new ArrayList<>(this.workingRow.getSize());

    int count = 0;
    for (int i = 0; i < spaces.length; i++) {
      count = count + spaces[i] + this.workingRow.getDescription()[i];
      for (int j = 0; j < spaces[i]; j++) {
        ret.add(new PicrossCell(PicrossCell.EMPTY));
      }
      for (int j = 0; j < this.workingRow.getDescription()[i]; j++) {
        ret.add(new PicrossCell(PicrossCell.FILLED));
      }

    }
    for (int i = count; i < this.workingRow.getSize(); i++) {
      ret.add(new PicrossCell(PicrossCell.EMPTY));
    }
    rowsMade++;
    return ret;
  }

  private boolean matchesEstablished(List<PicrossCell> row) {
    for (int i = 0; i < row.size(); i++) {
      if (!(this.workingRow.getCell(i).charValue() == PicrossCell.UNDECIDED
          || this.workingRow.getCell(i).charValue() == row.get(i).charValue())) {
        return false;
      }
    }
    return true;
  }

}
