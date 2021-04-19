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

  public void updateRows() {
    for (int rowNum = 0; rowNum < this.height; rowNum++) {
      List<Integer> rowDescription = this.rowDescriptions.get(rowNum);
      generatePatternList(rowDescription, this.width);
    }
  }

  private static void generatePatternList(List<Integer> description, int length) {
    final int numberOfOpenCells = length - description.stream().mapToInt(Integer::intValue).sum();

    LOG.info("Row description: {}, {}", description, numberOfOpenCells);

    Odometer o = new Odometer(description.size(), numberOfOpenCells);
    List<List<Integer>> spaceDescription = o.makeList();

    for (List<Integer> spaces : spaceDescription) {
      generatePattern(description, spaces, length);
    }
  }

  private static CellStateList generatePattern(List<Integer> description, List<Integer> spaces, int length) {
    CellStateList csl = new CellStateList();
    int cellsFilled = 0;
    for (int i = 0; i < spaces.size(); i++) {
      cellsFilled = cellsFilled + spaces.get(i) + description.get(i);
      csl.addSome(CellState.EMPTY, spaces.get(i));
      csl.addSome(CellState.FILLED, description.get(i));
    }
    csl.addSome(CellState.EMPTY, (length - cellsFilled));

    LOG.info("CSL: {}", csl);
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
