package com.example.snake.controller.collision;

import com.example.snake.controller.FoodController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.geom.Area;

@Component
public class FoodCollisionController implements CollisionController {

    @Autowired
    FoodController foodController;
    Area collisionArea;

    public FoodCollisionController() {
        collisionArea = new Area();
    }

    @Override
    public Area getCollisionArea() {
        return collisionArea;
    }

    @Override
    public void refreshCollisionArea() {
        collisionArea = new Area();
        collisionArea.add(new Area(foodController.getFood().getRect()));
    }
}
