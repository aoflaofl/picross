package com.spamalot.game;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
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

  PicrossRow(int size) {
    this.size = size;
  }

  private int size;

  private PicrossThing patternMatcher = new PicrossThing(this);

  public void setSize(int size) {
    this.size = size;
  }

  private int[] description;

  private List<PicrossCell> rowCells = new ArrayList<>();

  private boolean solved;

  void addRowCell(PicrossCell c) {
    this.rowCells.add(c);
  }

  public boolean processTheRowsData() {

    List<PicrossCell> accumulator = this.patternMatcher.doThePicrossThing();
    boolean different = false;
    solved = true;
    for (int i = 0; i < this.size; i++) {
      if (this.getCell(i).charValue() != accumulator.get(i).charValue()) {
        different = true;
        this.getCell(i).setValue(accumulator.get(i).charValue());
      }
      if (this.getCell(i).charValue() == PicrossCell.UNDECIDED) {
        solved = false;
      }
    }

    return different;
  }

  boolean isSolved() {
    return solved;
  }

  @SuppressWarnings("boxing")
  public void setDescription(List<Integer> is) {
    this.description = is.stream().mapToInt(i -> i).toArray();
  }

  public int getSize() {
    return this.size;
  }

  public int[] getDescription() {
    return this.description;
  }

  public PicrossCell getCell(int i) {
    return this.rowCells.get(i);
  }

  public String toJsonString() {
    StringBuilder sb = new StringBuilder();
    StringJoiner sj = new StringJoiner(",");

    for (int i : getDescription()) {
      sj.add(Integer.toString(i));
    }
    sb.append("[").append(sj).append("]");
    return sb.toString();
  }
}
