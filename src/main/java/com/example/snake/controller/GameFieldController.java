package com.example.snake.controller;

import com.example.snake.controller.collision.CollisionController;
import com.example.snake.controller.collision.GameCollisionController;
import com.example.snake.model.GameField;
import com.example.snake.view.GameFrame;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.awt.*;

import static com.example.snake.Constants.*;

@Data
public class GameFieldController {

    GameField gameField;
    SnakeController snakeController;
    FoodController foodController;
    SnakeController.KeyController keyController;
    GameFrame gameFrame;

    @Autowired
    GameCollisionController gameCollisionController;

    boolean isGameStarted = false;
    boolean isGameStopped = false;

    public GameFieldController(SnakeController snakeController, FoodController foodController, SnakeController.KeyController keyController) {

        gameField = new GameField(CELL_SIZE_X, CELL_SIZE_Y, FIELD_WIDTH_IN_CELLS, FIELD_HEIGHT_IN_CELLS);

        this.snakeController = snakeController;
        this.foodController = foodController;
        this.keyController = keyController;

        gameFrame = new GameFrame(this::draw, FIELD_WIDTH_IN_CELLS * CELL_SIZE_X, FIELD_HEIGHT_IN_CELLS * CELL_SIZE_Y);
        gameFrame.setKeyListener(keyController);
    }

    public void draw(Graphics2D g2d) {
        snakeController.drawSnake(g2d);
        foodController.drawFood(g2d);
    }

    public void startGame() {
        snakeController.createSnake(5);
        foodController.createFood();

        isGameStarted  = true;
    }

    @Scheduled(fixedDelay = 100)
    void updateField() {

        if (!isGameStarted) {
            return;
        }

        snakeController.moveSnake();
        gameCollisionController.refreshCollisionArea();

        if (gameCollisionController.isFoodHasEaten()) {
            snakeController.feedSnake();
            foodController.createFood();
        }

        if (gameCollisionController.isSnakeHasEatenItself()) {
            isGameStarted = false;
        }

        gameFrame.repaint();
    }
}
