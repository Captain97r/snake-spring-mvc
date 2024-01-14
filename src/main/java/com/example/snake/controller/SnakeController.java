package com.example.snake.controller;

import com.example.snake.controller.collision.SnakeCollisionController;
import com.example.snake.model.Direction;
import com.example.snake.model.Snake;
import com.example.snake.model.SnakePart;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.stream.Location;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.snake.Constants.CELL_SIZE_X;
import static com.example.snake.Constants.CELL_SIZE_Y;
import static com.example.snake.model.Direction.RIGHT;

@Component
@Data
public class SnakeController {

    Snake snake;
    Direction direction;

    private final int START_COORDINATE_X = 300;
    private final int START_COORDINATE_Y = 300;

    public void createSnake(int size) {
        snake = new Snake();
        direction = RIGHT;
        for (int i = 0; i < size; i++) {
            snake.addPart(new SnakePart(new Rectangle(START_COORDINATE_X - (CELL_SIZE_X * i), START_COORDINATE_Y, CELL_SIZE_X, CELL_SIZE_Y)));
        }
    }

    public void feedSnake() {
        snake.addPart(new SnakePart(new Rectangle(-100, -100, CELL_SIZE_X, CELL_SIZE_Y)));
    }

    public void moveSnake() {
        for (int i = snake.getSnakeParts().size() - 1; i > 0; i--) {
            snake.getSnakeParts().get(i).getRect().setLocation(snake.getSnakeParts().get(i - 1).getRect().getLocation());
        }

        Point headLocation = snake.getHead().getRect().getLocation();

        switch(direction) {
            case UP:
                headLocation.y -= CELL_SIZE_Y;
                break;
            case DOWN:
                headLocation.y += CELL_SIZE_Y;
                break;
            case RIGHT:
                headLocation.x += CELL_SIZE_X;
                break;
            case LEFT:
                headLocation.x -= CELL_SIZE_X;
                break;
        }

        // move snake head
        snake.getSnakeParts().get(0).getRect().setLocation(headLocation);
    }

    public void drawSnake(Graphics2D g2d) {
        List<SnakePart> parts = new ArrayList<>(snake.getSnakeParts());
        Collections.reverse(parts);
        for (SnakePart snakePart : parts) {

            if (snakePart == snake.getHead()) {
                g2d.setColor(Color.BLUE);
                g2d.fill(snakePart.getRect());
                g2d.draw(snakePart.getRect());
                continue;
            }

            g2d.setColor(Color.GREEN);
            g2d.fill(snakePart.getRect());
            g2d.setColor(Color.BLUE);
            g2d.draw(snakePart.getRect());
        }
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