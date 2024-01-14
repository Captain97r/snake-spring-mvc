package com.example.snake.controller;

import com.example.snake.controller.collision.CollisionController;
import com.example.snake.controller.collision.GameCollisionController;
import com.example.snake.model.GameField;
import com.example.snake.view.GameFrame;
import com.example.snake.view.MenuPanel;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.awt.*;

import static com.example.snake.Constants.*;

@Component
@Data
public class GameFieldController {

    GameField gameField;
    @Autowired
    SnakeController snakeController;
    @Autowired
    FoodController foodController;
    @Autowired
    GameCollisionController gameCollisionController;


    public GameFieldController() {
        gameField = new GameField(CELL_SIZE_X, CELL_SIZE_Y, FIELD_WIDTH_IN_CELLS, FIELD_HEIGHT_IN_CELLS);
    }
}
