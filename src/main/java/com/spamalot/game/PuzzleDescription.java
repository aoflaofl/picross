package com.spamalot.game;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
class PuzzleDescription {
  @JsonProperty("name")
  private String name;

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("rows")
  private List<List<Integer>> rows = new ArrayList<>();

  @JsonProperty("columns")
  private List<List<Integer>> columns = new ArrayList<>();

  @JsonProperty("rows")
  public List<List<Integer>> getRows() {
    return this.rows;
  }

  @JsonProperty("rows")
  public void setRows(List<List<Integer>> rows) {
    this.rows = rows;
  }

  @JsonProperty("columns")
  public List<List<Integer>> getColumns() {
    return this.columns;
  }

  @JsonProperty("columns")
  public void setColumns(List<List<Integer>> columns) {
    this.columns = columns;
  }
}
