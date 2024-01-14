package com.example.snake.view;

import com.example.snake.Constants;
import com.example.snake.controller.GameFieldController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.geom.RoundRectangle2D;
import java.util.function.Consumer;

public class GameFrame extends JFrame {

    GamePanel gamePanel;
    MenuPanel menuPanel;

    JPanel cardPanel;


    CardLayout cardLayout;

    JButton button;

    public GameFrame(Consumer<Graphics2D> g2d) {

        this.setPreferredSize(new Dimension(Constants.CELL_SIZE_X * Constants.FIELD_WIDTH_IN_CELLS, Constants.CELL_SIZE_Y * Constants.FIELD_HEIGHT_IN_CELLS));

        cardLayout = new CardLayout();

        cardPanel = new JPanel();
        cardPanel.setPreferredSize(new Dimension(Constants.CELL_SIZE_X * Constants.FIELD_WIDTH_IN_CELLS, Constants.CELL_SIZE_Y * Constants.FIELD_HEIGHT_IN_CELLS));
        cardPanel.setLayout(cardLayout);
        cardPanel.setFocusable(true);

        gamePanel = new GamePanel(g2d);
        gamePanel.setPreferredSize(new Dimension(Constants.CELL_SIZE_X * Constants.FIELD_WIDTH_IN_CELLS, Constants.CELL_SIZE_Y * Constants.FIELD_HEIGHT_IN_CELLS));

        menuPanel = new MenuPanel();
        menuPanel.setPreferredSize(new Dimension(Constants.CELL_SIZE_X * Constants.FIELD_WIDTH_IN_CELLS, Constants.CELL_SIZE_Y * Constants.FIELD_HEIGHT_IN_CELLS));

        button = new JButton("Start Game");
        menuPanel.add(button);

        cardPanel.add(menuPanel, "menu");
        cardPanel.add(gamePanel, "game");

        this.setTitle("Snake Game");
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        getContentPane().add(cardPanel, BorderLayout.NORTH);
        cardLayout.show(cardPanel, "menu");
    }

    public void openGamePanel() {
        cardLayout.show(cardPanel, "game");
        gamePanel.setFocusable(true);
    }

    public JButton getButton() {
        return button;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}