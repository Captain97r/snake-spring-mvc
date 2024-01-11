package com.example.snake.controller.collision;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.geom.Area;

@Component
public class GameCollisionController implements CollisionController {

    Area collisionArea;

    @Autowired
    SnakeCollisionController snakeCollisionController;
    @Autowired
    FoodCollisionController foodCollisionController;

    public GameCollisionController() {
        collisionArea = new Area();
    }

    @Override
    public Area getCollisionArea() {
        return collisionArea;
    }

    @Override
    public void refreshCollisionArea() {

        snakeCollisionController.refreshCollisionArea();
        foodCollisionController.refreshCollisionArea();

        collisionArea = new Area();
        collisionArea.add(snakeCollisionController.getCollisionArea());
        collisionArea.add(foodCollisionController.getCollisionArea());
    }

    public boolean isFoodHasEaten() {
        Area a = new Area(snakeCollisionController.getCollisionArea());
        a.intersect(foodCollisionController.getCollisionArea());
        return !a.isEmpty();
    }
}
