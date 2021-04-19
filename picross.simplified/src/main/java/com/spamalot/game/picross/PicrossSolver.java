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

    try {
      List<Puzzle> pz = readPuzzleJson("src/main/resources/pppp.json");
      solveTheDamnPuzzle(pz.get(0));
//    for (Puzzle p : pz) {
//    solveTheDamnPuzzle(p);
//  }
    } catch (IOException e) {
      LOG.error("Error", e);
    }
  }

  private static void solveTheDamnPuzzle(final Puzzle p) {
    LOG.info("Puzzle:\n{}", p);
    p.updateRows();
  }

  private static List<Puzzle> readPuzzleJson(final String path) throws IOException {
    List<Puzzle> ret = new ArrayList<>();

    List<PuzzleSpecification> list = Arrays.asList(MAPPER.readValue(new File(path), PuzzleSpecification[].class));
    for (PuzzleSpecification pd : list) {
      ret.add(new Puzzle(pd));
    }

    return ret;
  }
}
