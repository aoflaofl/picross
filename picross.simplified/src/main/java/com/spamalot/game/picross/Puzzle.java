package com.spamalot.game.picross;

import java.util.List;

class Puzzle {
  private final CellState[][] board;

  private Puzzle(final int width, final int height) {
    this.height = height;
    this.width = width;

    this.board = new CellState[height][width];

    for (int h = 0; h < height; h++) {
      for (int w = 0; w < width; w++) {
        this.board[h][w] = CellState.UNDECIDED;
      }
    }
  }

  private List<List<Integer>> columnDescriptions;
  private List<List<Integer>> rowDescriptions;

  Puzzle(final PuzzleSpecification puzzleSpec) {
    this(puzzleSpec.getColumns().size(), puzzleSpec.getRows().size());
    columnDescriptions = puzzleSpec.getColumns();
    rowDescriptions = puzzleSpec.getRows();
  }

  private final int height;
  private final int width;

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
}
