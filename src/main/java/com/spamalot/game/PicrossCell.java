package com.spamalot.game;

class PicrossCell {
  public static final char EMPTY = '.';
  public static final char FILLED = '*';
  public static final char UNDECIDED = '-';

  private char value;

  PicrossCell(char c) {
    this.value = c;
  }

  public char charValue() {
    return this.value;
  }

  public void setValue(char c) {
    this.value = c;
  }

  @Override
  public String toString() {
    return Character.toString(this.value);
  }
}
