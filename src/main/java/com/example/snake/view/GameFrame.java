package com.example.snake.view;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.geom.RoundRectangle2D;
import java.util.function.Consumer;

public class GameFrame extends JFrame {

    GamePanel gamePanel;
    public GameFrame(Consumer<Graphics2D> g2d) {
        gamePanel = new GamePanel(g2d);
        this.add(gamePanel);

        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500, 500);
        this.setVisible(true);
        this.setLayout(null);
    }

    public void setKeyListener(KeyListener listener) {
        this.addKeyListener(listener);
    }
}