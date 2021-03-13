package com.spamalot.game.picross;

public enum CellState {
  /** A known empty cell. */
  EMPTY('.'),
  /** A known filled cell. */
  FILLED('*'),
  /** An unknown cell. */
  UNDECIDED('-');

  /** Display Value. */
  private String displayValue;

  CellState(final char c) {
    this.displayValue = String.valueOf(c);
  }

  @Override
  public String toString() {
    return this.displayValue;
  }
}
