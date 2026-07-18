package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class SnakeRenderer extends JPanel {
    private static final int boardWidth = 625;
    private static final int boardHeight = 625;
    private static final int gridSize = 25;
    private SnakeLogic logic;
    public SnakeRenderer(SnakeLogic snakeLogic) {
        this.logic = snakeLogic;
        this.setPreferredSize(new Dimension(boardWidth, boardHeight));
        this.setBackground(Color.black);
        this.setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);    //CLEARING SCREEN FIRST BEFORE DRAWING
        drawComponent(g);
    }

    public void drawComponent(Graphics g) {
        //DRAW GRID
        for (int i = 0; i < boardWidth/ gridSize; i++) {
            g.drawLine(i* gridSize, 0, i* gridSize, boardHeight);
            g.drawLine(0, i* gridSize, boardWidth, i* gridSize);
        }

        //PAINT SNAKE
        for (Point p : logic.getSnake()) {
            g.setColor(Color.green);
            g.fillRect(p.x, p.y, gridSize, gridSize);

            g.setColor(Color.black);
            g.drawRect(p.x, p.y, gridSize, gridSize);
        }

        //LOCATE APPLE LOCATION AND PAINT
        int x = logic.getAppleX();
        int y = logic.getAppleY();

        g.setColor(Color.red);
        g.fillOval(x+7, y+7, 8, 8);
    }
}
