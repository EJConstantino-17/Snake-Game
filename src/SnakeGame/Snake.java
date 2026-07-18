package SnakeGame;

import javax.swing.*;
import java.awt.*;

public class Snake {
    public static void main(String[] args) {
        //FRAME LAYOUT
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(625, 625);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        SnakeLogic snakeLogic = new SnakeLogic();
        SnakeRenderer snakeRenderer = new SnakeRenderer(snakeLogic);
        SnakeController snakeController = new SnakeController(snakeLogic, snakeRenderer);
        frame.add(snakeRenderer);
        frame.pack();
        frame.setVisible(true);

        snakeController.startGame();
    }
}
