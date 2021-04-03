package com.spamalot.game.picross;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Puzzle {
  private static final Logger LOG = LoggerFactory.getLogger(Puzzle.class);

  private final CellState[][] board;

  private List<List<Integer>> columnDescriptions;
  private List<List<Integer>> rowDescriptions;

  private final int height;

  private final int width;

  /**
   * Handle Picross Puzzle operations.
   * 
   * @param width  the width of the puzzle
   * @param height the height of the puzzle
   */
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

  Puzzle(final PuzzleSpecification puzzleSpec) {
    this(puzzleSpec.getColumns().size(), puzzleSpec.getRows().size());
    this.columnDescriptions = puzzleSpec.getColumns();
    this.rowDescriptions = puzzleSpec.getRows();
  }

  public void updateRow(final int rowNum) {
    List<Integer> rowDescription = this.rowDescriptions.get(rowNum);
    int rowWidth = this.width;

    extracted(rowDescription, rowWidth);
  }

  private void extracted(List<Integer> description, int length) {
    final int numberOfOpenCells = length - description.stream().mapToInt(Integer::intValue).sum();

    LOG.info("Row description: {}, {}", description, numberOfOpenCells);

    Odometer o = new Odometer(description.size(), numberOfOpenCells);
    List<List<Integer>> spaceDescription = o.makeList();

    for (List<Integer> spaces : spaceDescription) {
      protnDOIdljg(description, spaces);
      System.out.println();
    }
  }

  private CellStateList protnDOIdljg(List<Integer> description, List<Integer> spaces) {
    CellStateList csl = new CellStateList();
    int cellsFilled = 0;
    for (int i = 0; i < spaces.size(); i++) {
      cellsFilled = cellsFilled + spaces.get(i) + description.get(i);
      for (int j = 0; j < spaces.get(i); j++) {
        csl.add(CellState.EMPTY);
        System.out.print(".");
      }
      for (int j = 0; j < description.get(i); j++) {
        csl.add(CellState.FILLED);
        System.out.print("*");
      }
    }
    for (int i = 0; i < (this.width - cellsFilled); i++) {
      csl.add(CellState.EMPTY);
      System.out.print(".");
    }
    return csl;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    for (int hIndex = 0; hIndex < this.height; hIndex++) {
      for (int wIndex = 0; wIndex < this.width; wIndex++) {
        sb.append(this.board[hIndex][wIndex]);
      }

      sb.append("\n");
    }
    sb.append("Columns:\n");
    for (final List<Integer> i : this.columnDescriptions) {
      sb.append(i);
      sb.append("\n");
    }
    sb.append("Rows:\n");
    for (final List<Integer> i : this.rowDescriptions) {
      sb.append(i);
      sb.append("\n");
    }

    return sb.toString();
  }
}
