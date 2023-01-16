package org.code.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Board {
    private final Integer size;
    private final List<Integer> board;

    public Board(Integer size) {
        this.size = size;
        this.board = new ArrayList<>(Collections.nCopies(size + 1, 0));
    }
}
