package com.example.snake.controller;

import com.example.snake.model.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Component
public class KeyController extends KeyAdapter {

    @Autowired
    SnakeController snakeController;

    @Override
    public void keyPressed(KeyEvent e) {

        Direction direction = snakeController.getDirection();
        int key = e.getKeyCode();
        if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && (direction != Direction.RIGHT)) {
            direction = Direction.LEFT;
        }
        else if ((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && (direction != Direction.LEFT)) {
            direction = Direction.RIGHT;
        }
        else if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && (direction != Direction.DOWN)) {
            direction = Direction.UP;
        }
        else if ((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && (direction != Direction.UP)) {
            direction = Direction.DOWN;
        }
        snakeController.setDirection(direction);
    }
}
