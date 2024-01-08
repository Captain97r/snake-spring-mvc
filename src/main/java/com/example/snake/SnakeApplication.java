package com.example.snake;

import com.example.snake.controller.GameController;
import com.example.snake.controller.KeyController;
import com.example.snake.controller.SnakeController;
import com.example.snake.model.Food;
import com.example.snake.view.GameFrame;
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
    public GameController gameController(SnakeController snakeController, KeyController keyController) {
        return new GameController(snakeController, keyController);
    }

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        return scheduler;
    }

}
