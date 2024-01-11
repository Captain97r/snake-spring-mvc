package com.example.snake.controller;

import com.example.snake.model.Food;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
@Data
public class FoodController {

    Food food;

    int size_x;
    int size_y;

    public void createFood(int width, int height) {
        int x = (int) (Math.random() * (width - 1));
        int y = (int) (Math.random() * (height - 1));

        food = new Food(x * size_x, y * size_y, size_x, size_y);
    }

    public void drawFood(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fill(food.getRect());
        g2d.setColor(Color.RED);
        g2d.draw(food.getRect());
    }

}
