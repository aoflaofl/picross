package com.spamalot.game.picross;

public enum CellState {
  EMPTY('.'), FILLED('*'), UNDECIDED('-');

  private String displayValue;

  CellState(char c) {
    this.displayValue = String.valueOf(c);
  }

  @Override
  public String toString() {
    return this.displayValue;
  }
}
