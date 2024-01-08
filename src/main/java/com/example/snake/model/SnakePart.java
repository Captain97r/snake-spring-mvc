package com.example.snake.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;

@Data
@AllArgsConstructor
public class SnakePart {
    Rectangle rect;

    public SnakePart() {
        rect = new Rectangle(-100, -100, 0, 0);
    }
}
