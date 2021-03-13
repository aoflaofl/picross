package com.spamalot.game.picross;

import java.util.List;
import java.util.stream.IntStream;
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

  public void updateRow(int row) {
    List<Integer> r = this.rowDescriptions.get(row);
    final int numberOfOpenCells = this.width - r.stream().mapToInt(Integer::intValue).sum();

    LOG.info("Row description: {}, {}", r, numberOfOpenCells);

    Odometer o = new Odometer(r.size(), numberOfOpenCells);
    List<List<Integer>> e = o.makeList();
    for (int i = 0; i < e.size(); i++) {
      List<Integer> ww = e.get(i);
      int qwe = 0;
      for (int k = 0; k < ww.size(); k++) {
        qwe = qwe + ww.get(k) + r.get(k);
        for (int sas = 0; sas < ww.get(k); sas++) {
          System.out.print(".");
        }
        for (int sas = 0; sas < r.get(k); sas++) {
          System.out.print("*");
        }
      }
      for (int d = 0; d < (width - qwe); d++) {
        System.out.print(".");
      }
      System.out.println();
    }
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
