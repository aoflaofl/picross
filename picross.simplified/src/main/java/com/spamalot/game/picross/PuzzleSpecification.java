package com.spamalot.game.picross;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * The Specifications of a Picross Puzzle.
 *
 * @author gej
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
class PuzzleSpecification {

  /** Name of puzzle. */
  @JsonProperty("name")
  private String name;

  /** List of Column descriptions. */
  @JsonProperty("columns")
  private List<List<Integer>> columns = new ArrayList<>();

  /** List of row descriptions. */
  @JsonProperty("rows")
  private List<List<Integer>> rows = new ArrayList<>();

  @JsonProperty("columns")
  public List<List<Integer>> getColumns() {
    return this.columns;
  }

  @JsonProperty("name")
  public String getName() {
    return this.name;
  }

  @JsonProperty("rows")
  public List<List<Integer>> getRows() {
    return this.rows;
  }

  @JsonProperty("columns")
  public void setColumns(final List<List<Integer>> c) {
    this.columns = c;
  }

  @JsonProperty("name")
  public void setName(final String n) {
    this.name = n;
  }

  @JsonProperty("rows")
  public void setRows(final List<List<Integer>> r) {
    this.rows = r;
  }
}
