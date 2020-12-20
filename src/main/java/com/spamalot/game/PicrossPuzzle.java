package com.spamalot.game;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

class PicrossPuzzle {
  private PicrossCell[][] board;

  private List<PicrossRow> rows = new ArrayList<>();
  private List<PicrossRow> columns = new ArrayList<>();

  private PicrossPuzzle(int width, int height) {
    this.height = height;
    this.width = width;

    for (int i = 0; i < height; i++) {
      this.rows.add(new PicrossRow(width));
    }

    for (int i = 0; i < width; i++) {
      this.columns.add(new PicrossRow(height));
    }

    this.board = new PicrossCell[height][width];

    for (int h = 0; h < height; h++) {
      for (int w = 0; w < width; w++) {
        PicrossCell c = new PicrossCell(PicrossCell.UNDECIDED);
        this.board[h][w] = c;
        this.rows.get(h).addRowCell(c);
        this.columns.get(w).addRowCell(c);
      }
    }
  }

  public PicrossPuzzle(PuzzleDescription pppp) {
    this(pppp.getColumns().size(), pppp.getRows().size());
    for (int i = 0; i < this.height; i++) {
      this.getRow(i).setDescription(pppp.getRows().get(i));
    }
    for (int i = 0; i < this.width; i++) {
      this.getColumn(i).setDescription(pppp.getColumns().get(i));
    }
  }

  private int height;
  private int width;

  public int getHeight() {
    return this.height;
  }

  public int getWidth() {
    return this.width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  private PicrossRow getRow(int i) {
    return this.rows.get(i);
  }

  private PicrossRow getColumn(int i) {
    return this.columns.get(i);
  }

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

  public List<PicrossRow> getColumns() {
    return this.columns;
  }

  public List<PicrossRow> getRows() {
    return this.rows;
  }

  public String toJsonString() {
    StringBuilder sb = new StringBuilder();

    sb.append("{");
    StringJoiner sj = new StringJoiner(",");

    sb.append("\"rows\":[");
    for (PicrossRow i : this.rows) {
      sj.add(i.toJsonString());
    }
    sb.append(sj).append("],\"columns\":[");

    sj = new StringJoiner(",");
    for (PicrossRow i : this.columns) {
      sj.add(i.toJsonString());
    }
    sb.append(sj).append("]}");
    return sb.toString();
  }
}
