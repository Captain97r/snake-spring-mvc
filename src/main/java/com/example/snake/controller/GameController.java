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
    @Autowired
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
//        RoundRectangle2D.Double rs = new RoundRectangle2D.Double(100, 100, 20, 20, 5, 5);
//        g2d.setColor(Color.GREEN);
//        g2d.draw(rs);
//        g2d.fill(rs);

//        drawFood(g2d);
        drawSnake(g2d);
    }

    public void drawSnake(Graphics2D g2d) {
        if (!isGameStarted) {
            snakeController.createSnake(5);
            isGameStarted = true;
        }

        for (SnakePart snakePart : snakeController.getSnake().getSnakeParts()) {
            g2d.setColor(Color.GREEN);
            g2d.fill(snakePart.getRect());
            g2d.setColor(Color.BLUE);
            g2d.draw(snakePart.getRect());
        }
    }

    @Scheduled(fixedDelay = 1000)
    void updateFrame() {

        snakeController.moveSnake();

        gameFrame.repaint();
    }

}
