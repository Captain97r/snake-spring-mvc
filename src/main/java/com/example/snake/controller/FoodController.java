package com.example.snake.controller;

import com.example.snake.controller.collision.FoodCollisionController;
import com.example.snake.model.Food;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;

import static com.example.snake.Constants.*;

@Component
@Data
public class FoodController {

    Food food;
    public void createFood() {
        int x = (int) (Math.random() * (FIELD_WIDTH_IN_CELLS - 1));
        int y = (int) (Math.random() * (FIELD_HEIGHT_IN_CELLS - 1));

        food = new Food(x * CELL_SIZE_X, y * CELL_SIZE_Y, CELL_SIZE_X, CELL_SIZE_Y);
    }

    public void drawFood(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fill(food.getRect());
        g2d.setColor(Color.RED);
        g2d.draw(food.getRect());
    }

}
