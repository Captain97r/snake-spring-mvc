package com.example.snake.controller;

import com.example.snake.model.Direction;
import com.example.snake.model.Snake;
import com.example.snake.model.SnakePart;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import static com.example.snake.model.Direction.RIGHT;

@Component
@Data
public class SnakeController {

    Snake snake;
    Direction direction;

    private final int START_COORDINATE_X = 300;
    private final int START_COORDINATE_Y = 300;
    private final int SNAKE_PART_SIZE_X = 30;
    private final int SNAKE_PART_SIZE_Y = 30;

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

        int x = (int)snakePartList.getFirst().getRect().getLocation().getX();
        int y = (int)snakePartList.getFirst().getRect().getLocation().getY();

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
        snakePartList.getFirst().getRect().setLocation(x, y);
    }

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
}