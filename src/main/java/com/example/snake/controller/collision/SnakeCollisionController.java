package com.example.snake.controller.collision;

import com.example.snake.controller.SnakeController;
import com.example.snake.model.SnakePart;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.geom.Area;
import java.util.List;

@Component
@Getter
public class SnakeCollisionController implements CollisionController {

    private Area collisionArea;
    private Area headCollisionArea;
    private Area bodyCollisionArea;

    @Autowired
    private SnakeController snakeController;

    public SnakeCollisionController() {
        collisionArea = new Area();
        headCollisionArea = new Area();
        bodyCollisionArea = new Area();
    }

    @Override
    public void refreshCollisionArea() {
        collisionArea = new Area();
        snakeController.getSnake().getSnakeParts().stream().map(SnakePart::getRect).map(Area::new).forEach(collisionArea::add);

        bodyCollisionArea = new Area();
        snakeController.getSnake().getBody().stream().map(SnakePart::getRect).map(Area::new).forEach(bodyCollisionArea::add);

        headCollisionArea = new Area(snakeController.getSnake().getHead().getRect());
    }
}
