package org.example;

import java.awt.*;
import java.awt.event.KeyEvent;

import java.util.ArrayList;

import static org.example.SnakeGame.DOT_SIZE;


import java.util.List;

public class Snake {
    private List<Point> body;
    private int direction;
    private boolean growing;

    public Snake() {
        body = new ArrayList<>();
        direction = KeyEvent.VK_RIGHT;
        growing = false;
    }

    public void initSnake() {
        body.clear();
        for (int i = 3; i >= 0; i--) {
            body.add(new Point(i, 0));
        }
    }

    public void changeDirection(int key) {
        if ((key == KeyEvent.VK_LEFT) && (direction != KeyEvent.VK_RIGHT)) {
            direction = KeyEvent.VK_LEFT;
        }
        if ((key == KeyEvent.VK_RIGHT) && (direction != KeyEvent.VK_LEFT)) {
            direction = KeyEvent.VK_RIGHT;
        }
        if ((key == KeyEvent.VK_UP) && (direction != KeyEvent.VK_DOWN)) {
            direction = KeyEvent.VK_UP;
        }
        if ((key == KeyEvent.VK_DOWN) && (direction != KeyEvent.VK_UP)) {
            direction = KeyEvent.VK_DOWN;
        }
    }

    public void move() {
        Point head = body.get(0);
        Point newHead = new Point(head.x, head.y);

        if (direction == KeyEvent.VK_LEFT) {
            newHead.x -= 1;
        } else if (direction == KeyEvent.VK_RIGHT) {
            newHead.x += 1;
        } else if (direction == KeyEvent.VK_UP) {
            newHead.y -= 1;
        } else if (direction == KeyEvent.VK_DOWN) {
            newHead.y += 1;
        }

        body.add(0, newHead);
        if (growing) {
            growing = false;
        } else {
            body.remove(body.size() - 1);
        }
    }

    public void grow() {
        growing = true;
    }

    public Point getHead() {
        return body.get(0);
    }

    public List<Point> getBody() {
        return body;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        for (Point point : body) {
            g.fillRoundRect(point.x * DOT_SIZE, point.y * DOT_SIZE, DOT_SIZE, DOT_SIZE, 10, 10);
        }
    }
}
