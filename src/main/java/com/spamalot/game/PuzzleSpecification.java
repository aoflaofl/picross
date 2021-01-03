package com.spamalot.game;

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
  @JsonProperty("name")
  private String name;

  @JsonProperty("columns")
  private List<List<Integer>> columns = new ArrayList<>();

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
  public void setColumns(final List<List<Integer>> columns) {
    this.columns = columns;
  }

  @JsonProperty("name")
  public void setName(final String name) {
    this.name = name;
  }

  @JsonProperty("rows")
  public void setRows(final List<List<Integer>> rows) {
    this.rows = rows;
  }
}
