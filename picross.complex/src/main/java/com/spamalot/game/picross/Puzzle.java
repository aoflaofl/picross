package com.spamalot.game.picross;

import java.util.ArrayList;
import java.util.List;

class Puzzle {
  private final Cell[][] board;

  private final List<Row> rows = new ArrayList<>();
  private final List<Row> columns = new ArrayList<>();

  private Puzzle(final int width, final int height) {
    this.height = height;
    this.width = width;

    for (int i = 0; i < height; i++) {
      this.rows.add(new Row(width));
    }

    for (int i = 0; i < width; i++) {
      this.columns.add(new Row(height));
    }

    this.board = new Cell[height][width];

    for (int h = 0; h < height; h++) {
      for (int w = 0; w < width; w++) {
        Cell c = new Cell(CellState.UNDECIDED);
        this.board[h][w] = c;
        this.rows.get(h).addRowCell(c);
        this.columns.get(w).addRowCell(c);
      }
    }
  }

  Puzzle(final PuzzleSpecification puzzleSpec) {
    this(puzzleSpec.getColumns().size(), puzzleSpec.getRows().size());

    for (int i = 0; i < this.height; i++) {
      this.getRow(i).setDescription(puzzleSpec.getRows().get(i));
    }

    for (int i = 0; i < this.width; i++) {
      this.getColumn(i).setDescription(puzzleSpec.getColumns().get(i));
    }
  }

  private final int height;
  private final int width;

  private Row getRow(final int i) {
    return this.rows.get(i);
  }

  private Row getColumn(final int i) {
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

  public List<Row> getColumns() {
    return this.columns;
  }

  public List<Row> getRows() {
    return this.rows;
  }
}
