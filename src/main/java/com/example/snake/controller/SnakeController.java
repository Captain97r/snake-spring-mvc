package com.example.snake.controller;

import com.example.snake.model.Direction;
import com.example.snake.model.Snake;
import com.example.snake.model.SnakePart;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

import static com.example.snake.model.Direction.RIGHT;

@Component
@Data
public class SnakeController {

    Snake snake;
    Direction direction;

    private final int START_COORDINATE_X = 100;
    private final int START_COORDINATE_Y = 100;
    private final int SNAKE_PART_SIZE_X = 10;
    private final int SNAKE_PART_SIZE_Y = 10;

    public void createSnake(int size) {
        snake = new Snake();
        direction = RIGHT;
        for (int i = 0; i < size; i++) {
            snake.addPart(new SnakePart(new Rectangle(START_COORDINATE_X - (SNAKE_PART_SIZE_X * i), START_COORDINATE_Y, SNAKE_PART_SIZE_X, SNAKE_PART_SIZE_Y)));
        }
    }

    public void moveSnake() {

        List<SnakePart> snakePartList = snake.getSnakeParts();

        for (int i = snake.getSnakeParts().size() - 1; i > 0; i--) {
            SnakePart snakePart = new SnakePart();
            snakePart.getRect().setSize(SNAKE_PART_SIZE_X, SNAKE_PART_SIZE_Y);
            snakePart.getRect().setLocation((int)snakePartList.get(i - 1).getRect().getX(), (int)snakePartList.get(i - 1).getRect().getY());

            snakePartList.set(i, snakePart);
        }

        int x = (int)snakePartList.get(0).getRect().getLocation().getX();
        int y = (int)snakePartList.get(0).getRect().getLocation().getY();

        switch(direction) {
            case UP:
                y -= SNAKE_PART_SIZE_Y;
                break;
            case DOWN:
                y += SNAKE_PART_SIZE_Y;
                break;
            case RIGHT:
                x += SNAKE_PART_SIZE_X;
                break;
            case LEFT:
                x -= SNAKE_PART_SIZE_X;
                break;
        }

        // move snake head
        snakePartList.get(0).getRect().setLocation(x, y);
    }
}