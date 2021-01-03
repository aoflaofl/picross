package com.spamalot.game;

import java.util.ArrayList;
import java.util.List;

class PicrossPuzzle {
  private final PicrossCell[][] board;

  private final List<PicrossRow> rows = new ArrayList<>();
  private final List<PicrossRow> columns = new ArrayList<>();

  private PicrossPuzzle(final int width, final int height) {
    this.height = height;
    this.width = width;

    for (int i = 0; i < height; i++) {
      this.rows.add(new PicrossRow(width));
    }

    for (int i = 0; i < width; i++) {
      this.columns.add(new PicrossRow(height));
    }

    this.board = new PicrossCell[height][width];

    for (int h = 0; h < height; h++) {
      for (int w = 0; w < width; w++) {
        PicrossCell c = new PicrossCell(PicrossCell.UNDECIDED);
        this.board[h][w] = c;
        this.rows.get(h).addRowCell(c);
        this.columns.get(w).addRowCell(c);
      }
    }
  }

  PicrossPuzzle(final PuzzleSpecification puzzleSpec) {
    this(puzzleSpec.getColumns().size(), puzzleSpec.getRows().size());

    for (int i = 0; i < this.height; i++) {
      this.getRow(i).setDescription(puzzleSpec.getRows().get(i));
    }

    for (int i = 0; i < this.width; i++) {
      this.getColumn(i).setDescription(puzzleSpec.getColumns().get(i));
    }
  }

  private int height;
  private int width;

  private PicrossRow getRow(final int i) {
    return this.rows.get(i);
  }

  private PicrossRow getColumn(final int i) {
    return this.columns.get(i);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int hIndex = 0; hIndex < this.height; hIndex++) {
      for (int wIndex = 0; wIndex < this.width; wIndex++) {
        sb.append(this.board[hIndex][wIndex]);
      }
      sb.append("\n");
    }
    return sb.toString();
  }

  public List<PicrossRow> getColumns() {
    return this.columns;
  }

  public List<PicrossRow> getRows() {
    return this.rows;
  }
}
