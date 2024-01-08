package com.example.snake.view;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.function.Consumer;

public class GamePanel extends JPanel {

    Consumer<Graphics2D> graphics;
    public GamePanel(Consumer<Graphics2D> g2d) {
        this.setBackground(Color.BLACK);
        graphics = g2d;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;

        graphics.accept(g2d);
    }
}
