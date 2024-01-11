package com.example.snake.controller.collision;

import com.example.snake.controller.SnakeController;
import com.example.snake.model.SnakePart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.geom.Area;

@Component
public class SnakeCollisionController implements CollisionController {

    private Area collisionArea;

    @Autowired
    private SnakeController snakeController;

    public SnakeCollisionController() {
        collisionArea = new Area();
    }

    @Override
    public Area getCollisionArea() {
        return collisionArea;
    }

    @Override
    public void refreshCollisionArea() {
        collisionArea = new Area();
        snakeController.getSnake().getSnakeParts().stream().map(SnakePart::getRect).map(Area::new).forEach(collisionArea::add);
    }
}
