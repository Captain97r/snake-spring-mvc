package com.example.snake.controller;

import com.example.snake.model.Direction;
import com.example.snake.view.GameFrame;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;

@Data
public class GameController {

    GameFrame gameFrame;
    @Autowired
    GameFieldController gameFieldController;
    @Autowired
    SnakeController.KeyController keyController;

    boolean isGameStarted = false;

    public GameController() {
        gameFrame = new GameFrame(this::draw);
        addKeyListener();

        gameFrame.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button clicked!");
                startGame();
            }
        });
    }

    public void draw(Graphics2D g2d) {

        if (!isGameStarted) {
            return;
        }

        gameFieldController.snakeController.drawSnake(g2d);
        gameFieldController.foodController.drawFood(g2d);
    }

    public void startGame() {
        gameFrame.openGamePanel();
        isGameStarted = true;
        gameFieldController.snakeController.createSnake(5);
        gameFieldController.foodController.createFood();
    }

    @Scheduled(fixedDelay = 100)
    void updateField() {

        if (!isGameStarted) {
            return;
        }

        gameFieldController.snakeController.moveSnake();
        gameFieldController.gameCollisionController.refreshCollisionArea();

        if (gameFieldController.gameCollisionController.isFoodHasEaten()) {
            gameFieldController.snakeController.feedSnake();
            gameFieldController.foodController.createFood();
        }

        if (gameFieldController.gameCollisionController.isSnakeHasEatenItself()) {
            isGameStarted = false;
        }
        gameFrame.repaint();
    }

    void addKeyListener() {
        gameFrame.getGamePanel().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "up");
        gameFrame.getGamePanel().getActionMap().put("up", new MoveAction(1));
    }

    private class MoveAction extends AbstractAction {

        int direction;

        MoveAction(int direction) {
            this.direction = direction;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            gameFieldController.getSnakeController().setDirection(Direction.UP);
        }
    }
}
