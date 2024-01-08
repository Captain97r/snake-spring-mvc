package com.example.snake.model;

import lombok.Data;

import java.awt.*;

@Data
public class Food {
    Rectangle rect;

    public Food() {
        rect = new Rectangle(-100, -100, 0, 0);
    }
}
