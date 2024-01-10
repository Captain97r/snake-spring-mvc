package com.example.snake.controller;

import com.example.snake.model.Direction;
import com.example.snake.model.SnakePart;
import com.example.snake.view.GameFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.RoundRectangle2D;

public class GameController {

    SnakeController snakeController;
    SnakeController.KeyController keyController;
    GameFrame gameFrame;

    boolean isGameStarted = false;

    public GameController (SnakeController snakeController, SnakeController.KeyController keyController) {
        this.snakeController = snakeController;
        this.keyController = keyController;
        gameFrame = new GameFrame(this::draw);
        gameFrame.setKeyListener(keyController);
    }

    public void draw(Graphics2D g2d) {
        snakeController.drawSnake(g2d);
//        drawFood(g2d);
    }

    @Scheduled(fixedDelay = 1000)
    void updateFrame() {

        if (!isGameStarted) {
            snakeController.createSnake(5);
            isGameStarted = true;
        }

        snakeController.moveSnake();
        gameFrame.repaint();
    }

}
