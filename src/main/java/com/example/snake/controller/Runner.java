package com.example.snake.controller;

import com.example.snake.view.GameFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {
    @Autowired
    private GameFieldController gameFieldController;

    @Override
    public void run(String... args) throws Exception {
        java.awt.EventQueue.invokeLater(() -> {
            gameFieldController.getGameFrame().setVisible(true);
            gameFieldController.startGame();
        });
    }
}
