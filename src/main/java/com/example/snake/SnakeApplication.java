package com.example.snake;

import com.example.snake.controller.FoodController;
import com.example.snake.controller.GameFieldController;
import com.example.snake.controller.SnakeController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
@EnableScheduling
public class SnakeApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SnakeApplication.class).headless(false).run(args);
    }

    @Bean
    public GameFieldController gameController(SnakeController snakeController, FoodController foodController, SnakeController.KeyController keyController) {
        return new GameFieldController(snakeController, foodController, keyController);
    }

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        return scheduler;
    }

}
