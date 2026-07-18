package SnakeGame;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeController {
    private static final int delay = 300;
    private Timer time;
    private Boolean isRunning = false;
    private SnakeLogic snakeLogic;
    private SnakeRenderer snakeRenderer;

    public SnakeController(SnakeLogic snakeLogic, SnakeRenderer snakeRenderer) {
        this.snakeLogic = snakeLogic;
        this.snakeRenderer = snakeRenderer;
        this.time = new Timer(delay, e->gameloop());    //TRIGGERS THE GAME LOOP
        this.snakeRenderer.addKeyListener(new UserInput());
    }

    public void startGame() {
        resetGame();
    }

    public void gameOver() {
        isRunning = false;
        time.stop();
    }

    public void resetGame() {
        snakeLogic.resetGame();
        isRunning = true;
        time.start();
    }

    //HANDLES THE GAME LOOP
    private void gameloop() {
        if(!isRunning) return;

        snakeLogic.move();

        if (snakeLogic.checkCollision()) {
            gameOver();
            return;
        }

        snakeRenderer.repaint();
    }

    //HANDLES USER INPUT
    private class UserInput extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_LEFT -> snakeLogic.setDirection('L');
                case KeyEvent.VK_RIGHT -> snakeLogic.setDirection('R');
                case KeyEvent.VK_UP -> snakeLogic.setDirection('U');
                case KeyEvent.VK_DOWN -> snakeLogic.setDirection('D');
                case KeyEvent.VK_ENTER -> {
                    if (!isRunning) resetGame();    //IF THE GAME IS NOT RUNNING, ENTER WILL TRIGGER RESTART
                }
            }
        }
    }
}
