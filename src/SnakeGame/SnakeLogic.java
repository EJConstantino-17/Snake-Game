/**
 * Project: Snake Game
 * Author: EJConstantino-17
 * Date: 2026-07-18
 */

package SnakeGame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;


public class SnakeLogic {
    //PRIVATE ACCESS TO VARIABLES
    private static final int boardWidth = 625;
    private static final int boardHeight = 625;
    private static final int gridSize = 25;
    private int appleX;
    private int appleY;
    private int dx;
    private int dy;
    private char currentDirection = 'R';
    Random random;
    private List<Point> snake = new ArrayList<>();
    int bodyParts = 5;
    private boolean inputLocked = false;

    public SnakeLogic() {
        random = new Random();
        resetGame();

    }

    public void resetGame() {
        snake.clear();

        //SPAWNING SNAKE BASED ON NUMBER OF BODY PARTS
        for (int i = 0; i < bodyParts; i++) {
            snake.add(new Point(100, 100));
        }

        //INITIAL DIRECTION
        setDirection('R');

        newApple();
    }

    //GETTERS FOR READ ONLY VARIABLE
    public int getAppleX() {
        return appleX;
    }

    public int getAppleY() {
        return appleY;
    }

    public List<Point> getSnake() {
        return snake;
    }

    //SPAWN APPLE AT RANDOM LOCATION
    public void newApple() {
        appleX = random.nextInt(boardWidth/gridSize) * gridSize;
        appleY = random.nextInt(boardHeight/gridSize) * gridSize;
    }

    public void move() {
        setDirection(currentDirection);
        Point head = snake.get(0);
        Point newHead = new Point(head.x + dx, head.y + dy);

        //IF SNAKE HAS EATEN APPLE DON'T REMOVE TAIL
        if (isAppleAt(newHead)) {
            snake.addFirst(newHead);
            newApple();
        }

        else {
            snake.addFirst(newHead);
            snake.removeLast();     //REMOVE TAIL TO SHOW MOVEMENT WHEN APPLE IS NOT EATEN
        }
        inputLocked = false;
    }

    public void setDirection(char newDirection) {
        if (inputLocked) {
            return;
        }

        //TO AVOID 180 DEGREE TURN
        if (newDirection == 'R' && currentDirection != 'L') {
            currentDirection = 'R';
            inputLocked = true; //LOCK INPUT BEFORE MOVING TO AVOID RAPID KEY REGISTERS
        }
        else if (newDirection == 'L' && currentDirection != 'R') {
            currentDirection = 'L';
            inputLocked = true;
        }
        else if (newDirection == 'U' && currentDirection != 'D') {
            currentDirection = 'U';
            inputLocked = true;
        }
        else if (newDirection == 'D' && currentDirection != 'U') {
            currentDirection = 'D';
            inputLocked = true;
        }

        //ASSIGNING MOVEMENT BASED ON DIRECTION
        switch (currentDirection) {
            case 'R' -> {
                dx = 25; dy = 0;
            }
            case 'L' -> {
                dx = -25; dy = 0;
            }
            case 'U' -> {
                dx = 0; dy = -25;
            }
            case 'D' -> {
                dx = 0; dy = 25;
            }
        }

    }

    //CHECKING COLLISION WITH WALL OR ITSELF
    public boolean checkCollision() {
        int x = snake.getFirst().x;
        int y = snake.getFirst().y;

        if (x >= boardWidth || x < 0 ||
            y >= boardHeight || y < 0) {
            return true;
        }

        for (int i = 1; i < snake.size(); i++) {
            if (snake.get(i).x == x && snake.get(i).y == y) {
                return true;
            }
        }
        return false;
    }

    //CHECK IF APPLE COORDINATES MATCH THE HEAD
    public boolean isAppleAt(Point p) {
        return p.x == appleX && p.y == appleY;
    }
}
