package com.spamalot.game.picross;

enum CellState {
  /** A known empty cell. */
  EMPTY("."),
  /** A known filled cell. */
  FILLED("*"),
  /** An unknown cell. */
  UNDECIDED("-");

  /** Display Value. */
  private final String displayValue;

  CellState(final String c) {
    this.displayValue = c;
  }

  @Override
  public String toString() {
    return this.displayValue;
  }
}
