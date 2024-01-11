package com.example.snake.controller.collision;

import java.awt.geom.Area;

public interface CollisionController {

    Area getCollisionArea();
    void refreshCollisionArea();

}
