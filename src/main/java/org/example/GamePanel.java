package org.example;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable
{
    // SCREEN SETTING
    final int originalTileSize = 16; // 16x16 pixels
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 pixels
    final int maxScreenCol = 16; // 16 vertical tile
    final int maxScreenRow = 12; // 12 row tile
    final int screenWidth = maxScreenCol * tileSize; //  48 * 16 = 768 pixels
    final int screenHeight = maxScreenRow * tileSize; // 48 * 12 = 576 pixels

    final KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, this.keyHandler);

    // FPS
    final int FPS = 60;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // A function to update the game in 60 frames per second
    // First method: sleep
    // The idea is after the program finishes drawing
    // it has to wait until the next time to draw
    // in order to maintain 60 FPS
    @Override
    public void run()
    {
        // The interval between each draw
        double drawFrameInterval = (double) 1_000_000_000/FPS;

        // The next time to draw is the current time added the drawing interval
        double nextDrawTime = System.nanoTime() + drawFrameInterval;

        while(gameThread != null)
        {
            // 1. Update: update the character's information
            update();

            // 2. Redraw: redraw the character's position
            repaint();

            // Now wait until next draw
            try
            {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1_000_000; // Convert to millisecond
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawFrameInterval;

            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    // A function to update the game in 60 frames per second
    // First method: delta
//    @Override
//    public void run()
//    {
//        double drawFrameInterval = 1_000_000_000/FPS;
//        double delta = 0;
//        long lastTime = System.nanoTime();
//        long currentTime;
//
//        while(gameThread != null)
//        {
//            currentTime = System.nanoTime();
//
//            delta = delta + (currentTime - lastTime)/drawFrameInterval;
//
//            lastTime = currentTime;
//
//            if(delta >= 1)
//            {
//                // 1. Update: update the character's information
//                update();
//
//                // 2. Redraw: redraw the character's position
//                repaint();
//
//                delta--;
//            }
//        }
//    }

    public void update()
    {
        player.update();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;

        player.draw(g2D);

        g2D.dispose();
    }
}
