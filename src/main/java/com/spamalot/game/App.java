package com.spamalot.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
  private static final Logger LOG = LoggerFactory.getLogger(App.class);
  private static final ObjectMapper MAPPER = new ObjectMapper();

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
    LOG.info("Rows: {}", com.spamalot.game.PicrossThing.rowsMade);
  }

  private static List<PicrossPuzzle> readPuzzleJson(final String path) {
    List<PicrossPuzzle> ret = new ArrayList<>();

    try {
      List<PuzzleDescription> list = Arrays.asList(MAPPER.readValue(new File(path), PuzzleDescription[].class));
      for (PuzzleDescription pd : list) {
        ret.add(new PicrossPuzzle(pd));
      }
    } catch (IOException e) {
      LOG.error("Problem reading file {}", path, e);
      System.exit(0);
    }
    return ret;
  }

  private static void solveTheDamnPuzzle(final PicrossPuzzle pz) {
    // 125841
    // 117630
    processRows(pz);
    processColumns(pz);

    boolean running = true;
    while (running) {
      boolean different1 = processRows(pz);
      if (!different1) {
        break;
      }
      boolean different2 = processColumns(pz);
      if (!different2) {
        break;
      }
    }
  }

  private static boolean processColumns(final PicrossPuzzle pz) {
    boolean different = false;
    for (PicrossRow i : pz.getColumns()) {
      different = i.processTheRowsData() || different;
    }
    LOG.info("After Columns:\n{}", pz);
    return different;
  }

  private static boolean processRows(final PicrossPuzzle pz) {
    boolean different = false;
    for (PicrossRow i : pz.getRows()) {
      different = i.processTheRowsData() || different;
    }
    LOG.info("After Rows:\n{}", pz);
    return different;
  }
}
