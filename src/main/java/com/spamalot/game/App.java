package com.spamalot.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class App {
  private static final Logger LOG = LoggerFactory.getLogger(App.class);
  private static final ObjectMapper MAPPER = new ObjectMapper();

  private App() {
    // Do nothing.
  }

  /**
   * Begin here.
   * 
   * @param args The arguments to the program
   */
  public static void main(final String[] args) {
    LOG.info("Picross Solver.");

    List<PicrossPuzzle> pz = readPuzzleJson("src/main/resources/pppp.json");
    for (PicrossPuzzle p : pz) {
      solveTheDamnPuzzle(p);
    }
  }

  private static List<PicrossPuzzle> readPuzzleJson(final String path) {
    List<PicrossPuzzle> ret = new ArrayList<>();

    try {
      List<PuzzleSpecification> list = Arrays.asList(MAPPER.readValue(new File(path), PuzzleSpecification[].class));
      for (PuzzleSpecification pd : list) {
        ret.add(new PicrossPuzzle(pd));
      }
    } catch (IOException e) {
      LOG.error("Problem reading file {}", path, e);
      System.exit(0);
    }
    return ret;
  }

  private static void solveTheDamnPuzzle(final PicrossPuzzle pz) {
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
  }

  private static boolean processColumns(final PicrossPuzzle pz) {
    boolean different = false;
    for (PicrossRow i : pz.getColumns()) {
      if (!i.isSolved()) {
        different = i.processTheRowsData() || different;
      }
    }
    LOG.info("After Columns:\n{}", pz);
    return different;
  }

  private static boolean processRows(final PicrossPuzzle pz) {
    boolean different = false;
    for (PicrossRow i : pz.getRows()) {
      if (!i.isSolved()) {
        different = i.processTheRowsData() || different;
      }
    }
    LOG.info("After Rows:\n{}", pz);
    return different;
  }
}
