package com.spamalot.game.picross;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PicrossSolver {
  private static final Logger LOG = LoggerFactory.getLogger(PicrossSolver.class);
  private static final ObjectMapper MAPPER = new ObjectMapper();

  private PicrossSolver() {
    // Do nothing.
  }

  /**
   * Begin here.
   * 
   * @param args The arguments to the program
   */
  public static void main(final String[] args) {
    LOG.info("Picross Solver.");

    List<Puzzle> pz = readPuzzleJson("src/main/resources/pppp.json");
    for (Puzzle p : pz) {
      solveTheDamnPuzzle(p);
    }
// [ 1, 2, 1 ] width is 8
    Odometer o = new Odometer(3, 3, 4);
    o.makeList();
  }

  private static List<Puzzle> readPuzzleJson(final String path) {
    List<Puzzle> ret = new ArrayList<>();

    try {
      List<PuzzleSpecification> list = Arrays.asList(MAPPER.readValue(new File(path), PuzzleSpecification[].class));
      for (PuzzleSpecification pd : list) {
        ret.add(new Puzzle(pd));
      }
    } catch (IOException e) {
      LOG.error("Problem reading file {}", path, e);
      System.exit(0);
    }
    return ret;
  }

  private static void solveTheDamnPuzzle(final Puzzle pz) {
    // Track progress in reducing number of rows made.
    // 125841
    // 117630
    // 87822
    // processRows(pz);
    processColumns(pz);

    while (true) {
      if (!(processRows(pz) && processColumns(pz))) {
        break;
      }
    }
    LOG.info("After Solve:\n{}", pz);
  }

  private static boolean processColumns(final Puzzle pz) {
    boolean different = false;
    for (Row i : pz.getColumns()) {
      if (!i.isSolved()) {
        different = i.processTheRowsData() || different;
      }
    }
    // LOG.info("After Columns:\n{}", pz);
    return different;
  }

  private static boolean processRows(final Puzzle pz) {
    boolean different = false;
    for (Row i : pz.getRows()) {
      if (!i.isSolved()) {
        different = i.processTheRowsData() || different;
      }
    }
    // LOG.info("After Rows:\n{}", pz);
    return different;
  }
}
