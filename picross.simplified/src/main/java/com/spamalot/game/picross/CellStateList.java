package com.spamalot.game.picross;

import java.util.ArrayList;

class CellStateList extends ArrayList<CellState> {

  private static final long serialVersionUID = 4744741741160611123L;

  void addSome(CellState st, int count) {
    for (int i = 0; i < count; i++) {
      this.add(st);
    }
  }

}
