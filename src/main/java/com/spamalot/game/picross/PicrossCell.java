package com.spamalot.game.picross;

class PicrossCell {
  public static final char EMPTY = '.';
  public static final char FILLED = '*';
  public static final char UNDECIDED = '-';

  private char value;

  PicrossCell(final char c) {
    this.value = c;
  }

  public char charValue() {
    return this.value;
  }

  public void setValue(final char c) {
    this.value = c;
  }

  @Override
  public String toString() {
    return Character.toString(this.value);
  }
}
