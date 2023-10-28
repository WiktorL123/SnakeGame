package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Time;
import java.util.Random;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class SnakeGame extends JPanel implements ActionListener {


    private Snake snake;
    private Point food;
    private int score;
    private boolean inGame;
    private Timer timer;
    private Random random;

    public static final int DOT_SIZE=20;
    public static final int GRID_SIZE=20;
    public static final int MAX_DOTS=400;
    public static final int DELAY=140;

        public SnakeGame(){
            snake=new Snake();
            random=new Random();
            inGame=true;
            score=0;

            setPreferredSize(new Dimension(GRID_SIZE*DOT_SIZE, GRID_SIZE*DOT_SIZE));
            setBackground(Color.BLACK);
            setFocusable(true);
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    int key=e.getKeyCode();
                    snake.changeDirection(key);
                }
            });
            initGame();
        }
        public void initGame(){
            snake.initSnake();
            createFood();
            inGame=true;
            timer=new Timer(DELAY, this);
            timer.start();
        }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame){
            snake.move();
            checkCollision();
            checkFood();
            repaint();
        }
    }

    private void createFood() {
            int foodX=random.nextInt(GRID_SIZE);
            int foodY=random.nextInt(GRID_SIZE);
            food = new Point(foodX, foodY);
    }
//    private void  checkCollision(){
//            Point head=snake.getHead();
//
//            if (head.equals(food)){
//                snake.grow();
//                createFood();
//                score+=1;
//            }
//            else if (head.x<0||head.x>=GRID_SIZE||head.y<0||head.y>=GRID_SIZE) {
//                inGame=false;
//                timer.stop();
//            }
//
//            else if (snake.getBody().subList(1, snake.getBody().size()).contains(head)){
//                inGame=false;
//                timer.stop();
//            }
//    }


    private void checkCollision() {
        Point head = snake.getHead();
        List<Point> body = snake.getBody();

        if (head.equals(food)) {
            snake.grow();
            createFood();
            score += 1;
        } else if (head.x < 0 || head.x >= GRID_SIZE || head.y < 0 || head.y >= GRID_SIZE) {
            inGame = false;
            timer.stop();
        } else {
            for (int i = 1; i < body.size(); i++) {
                Point segment = body.get(i);
                if (head.x == segment.x && head.y == segment.y) {
                    inGame = false;
                    timer.stop();
                    break;
                }
            }
        }
    }





    public void checkFood(){
            if(food==null)return;
            if(snake.getHead().equals(food)){
                snake.grow();
                createFood();
            }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        snake.draw(g);
        drawFood(g);
        if(!inGame)
            gameOver(g);
        drawScore(g);
    }

    private void drawScore(Graphics g) {
            String msg="wynik: "+score;
            Font font= new Font("Arial", Font.PLAIN, 16);
            g.setColor(Color.cyan);
            g.setFont(font);
        g.drawString(msg, 10, 20 );
    }

    private void gameOver(Graphics g) {
        String msg="Game over!";
        Font font= new Font("Arial", Font.PLAIN, 25);
        g.setColor(Color.red);
        g.setFont(font);
        g.drawString(msg, 10, 50);

        }

    private void drawFood(Graphics g) {
        if (food!=null){
         g.setColor(Color.RED);
         g.fillOval(food.x*DOT_SIZE, food.y*DOT_SIZE, DOT_SIZE, DOT_SIZE);

        }
        }

    @Override
    public boolean contains(int x, int y) {
        return super.contains(x, y);
    }
}
