package com.example.snake.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class Snake {

    List<SnakePart> snakeParts;

    public Snake() {
        snakeParts = new ArrayList<>();
    }

    public void addPart(SnakePart part) {
        snakeParts.add(part);
    }

    public SnakePart getHead() {
        return snakeParts.getFirst();
    }

    public List<SnakePart> getBody() {
        return snakeParts.stream().filter(snakePart -> !snakePart.equals(getHead())).toList();
    }
}
