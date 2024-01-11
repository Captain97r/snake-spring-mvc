package com.example.snake.view;

import com.example.snake.controller.GameFieldController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.geom.RoundRectangle2D;
import java.util.function.Consumer;

public class GameFrame extends JFrame {

    GamePanel gamePanel;
    public GameFrame(Consumer<Graphics2D> g2d, int width, int height) {
        gamePanel = new GamePanel(g2d);
        gamePanel.setPreferredSize(new Dimension(width, height));
        this.add(gamePanel);

        this.setTitle("Snake Game");
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public void setKeyListener(KeyListener listener) {
        this.gamePanel.addKeyListener(listener);
    }
}