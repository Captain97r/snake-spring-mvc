package com.example.snake.controller;

import com.example.snake.model.GameField;
import com.example.snake.view.GameFrame;
import lombok.Data;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.awt.*;

@Data
public class GameFieldController {

    private final int CELL_SIZE_X = 30;
    private final int CELL_SIZE_Y = 30;
    private final int FIELD_WIDTH_IN_CELLS = 30;
    private final int FIELD_HEIGHT_IN_CELLS = 30;

    GameField gameField;
    SnakeController snakeController;
    FoodController foodController;
    SnakeController.KeyController keyController;
    GameFrame gameFrame;

    boolean isGameStarted = false;

    public GameFieldController(SnakeController snakeController, FoodController foodController, SnakeController.KeyController keyController) {

        gameField = new GameField(CELL_SIZE_X, CELL_SIZE_Y, FIELD_WIDTH_IN_CELLS, FIELD_HEIGHT_IN_CELLS);

        this.snakeController = snakeController;
        this.foodController = foodController;
        this.keyController = keyController;

        this.snakeController.setPartSize_x(CELL_SIZE_X);
        this.snakeController.setPartSize_y(CELL_SIZE_Y);

        this.foodController.setSize_x(CELL_SIZE_X);
        this.foodController.setSize_y(CELL_SIZE_Y);

        gameFrame = new GameFrame(this::draw, FIELD_WIDTH_IN_CELLS * CELL_SIZE_X, FIELD_HEIGHT_IN_CELLS * CELL_SIZE_Y);
        gameFrame.setKeyListener(keyController);
    }

    public void draw(Graphics2D g2d) {
        snakeController.drawSnake(g2d);
        foodController.drawFood(g2d);
    }

    public void startGame() {
        snakeController.createSnake(5);
        foodController.createFood(FIELD_WIDTH_IN_CELLS, FIELD_HEIGHT_IN_CELLS);

        isGameStarted  = true;
    }

    @Scheduled(fixedDelay = 1000)
    void updateField() {

        if (!isGameStarted) {
            return;
        }

        snakeController.moveSnake();
        gameFrame.repaint();
    }
}
