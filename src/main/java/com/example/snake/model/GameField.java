package com.example.snake.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameField {

    int cellSize_x;
    int cellSize_y;

    int width;
    int height;
}
