package com.spamalot.game.picross;

class Cell {
  private CellState value;

  Cell(final CellState empty) {
    this.value = empty;
  }

  public CellState charValue() {
    return this.value;
  }

  public void setValue(final CellState c) {
    this.value = c;
  }

  @Override
  public String toString() {
    return this.value.toString();
  }
}
