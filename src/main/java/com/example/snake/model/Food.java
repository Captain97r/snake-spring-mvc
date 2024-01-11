package com.example.snake.model;

import lombok.Data;

import java.awt.*;

@Data
public class Food {

    Rectangle rect;

    public Food() {
        rect = new Rectangle(-100, -100, 0, 0);
    }

    public Food(int x, int y, int width, int height) {
        rect = new Rectangle(x, y, width, height);
    }
}
