package com.spamalot.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class PicrossPatternGenerator {
  private static final Logger LOG = LoggerFactory.getLogger(PicrossPatternGenerator.class);

  private PicrossPatternGenerator() {
  }

  static List<List<PicrossCell>> generateAllPossiblePatterns(final int rowSize, final int[] rowDescription) {
    int numberOfOpenCells = rowSize - IntStream.of(rowDescription).sum();
    int[] gaps = new int[rowDescription.length];

    List<List<PicrossCell>> ret = new ArrayList<>();
    recursion(0, gaps, numberOfOpenCells, rowDescription, rowSize, ret);
    return ret;
  }

  private static void recursion(final int level, final int[] gaps, final int openPlaces, final int[] rowDescription, final int rowSize,
      final List<List<PicrossCell>> ret) {
    if (level == rowDescription.length) {
      List<PicrossCell> row = buildRow(rowSize, gaps, rowDescription);

      ret.add(row);
      return;
    }
    for (int i = level == 0 ? 0 : 1; i <= openPlaces; i++) {
      gaps[level] = i;
      recursion(level + 1, gaps, openPlaces - i, rowDescription, rowSize, ret);
    }
  }

  /**
   * Build a list of row cells using a list of space lengths and the row's
   * description.
   * 
   * @param gaps
   * @return
   */
  private static List<PicrossCell> buildRow(final int rowSize, final int[] gaps, final int[] rowDescription) {
    List<PicrossCell> ret = new ArrayList<>(rowSize);

    int count = 0;
    for (int i = 0; i < gaps.length; i++) {
      count = count + gaps[i] + rowDescription[i];
      for (int j = 0; j < gaps[i]; j++) {
        ret.add(new PicrossCell(PicrossCell.EMPTY));
      }
      for (int j = 0; j < rowDescription[i]; j++) {
        ret.add(new PicrossCell(PicrossCell.FILLED));
      }

    }
    for (int i = count; i < rowSize; i++) {
      ret.add(new PicrossCell(PicrossCell.EMPTY));
    }
    return ret;
  }

}
