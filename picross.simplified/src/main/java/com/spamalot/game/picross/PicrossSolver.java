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
    solveTheDamnPuzzle(pz.get(0));
//    for (Puzzle p : pz) {
//      solveTheDamnPuzzle(p);
//    }
// [ 1, 2, 1 ] width is 8
    Odometer o = new Odometer(1, 3);
    o.makeList();
  }

  private static void solveTheDamnPuzzle(Puzzle p) {
    LOG.info("Puzzle:\n{}", p);
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
}
