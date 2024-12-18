package org.example;

import entity.Player;
import tile.TileDrawer;
import Object.GameObject;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class GamePanel extends JPanel implements Runnable
{
    // SCREEN SETTING
    final int originalTileSize = 16; // 16x16 pixels
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 pixels
    public final int maxScreenCol = 16; // 16 vertical tiles for the window
    public final int maxScreenRow = 12; // 12 row tiles for the window

    public final int windowWidth = maxScreenCol * tileSize; //  48 * 16 = 768 pixels wide
    public final int windowHeight = maxScreenRow * tileSize; // 48 * 12 = 576 pixels long

    public final int maxWorldCol = 32; // The number of column tiles in the whole world
    public final int maxWorldRow = 24; // The number of row tiles in the whole world

    public final int worldWidth = maxWorldCol * tileSize; //  48 * 32 = 1536 pixels
    public final int worldHeight = maxWorldRow * tileSize; //  48 * 24 = 1152 pixels

    Thread gameThread;
    // A keyHandler to notify which direction button's been pressed
    final KeyHandler keyHandler = new KeyHandler();
    // A player to play the game
    public Player player = new Player(this, this.keyHandler);
    // A world manager to handle and draw the world
    public TileDrawer tileDrawer = new TileDrawer(this);
    // A collision checker to check all the collision in the game
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    // An array to DISPLAY only 10 objects in the entire game
    public GameObject[] gameObjects = new GameObject[10];
    // An object placer to set up all the objects
    public ObjectPlacer objectPlacer = new ObjectPlacer(this);
    // FPS
    final int FPS = 60;

    public GamePanel() throws FileNotFoundException
    {
        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }
    // A function to set up all the object in the world
    public void setupGame() throws FileNotFoundException
    {
        objectPlacer.setObject();
    }
    // A function to start the game
    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // A function to update the game in 60 frames per second
    // method: delta
    @Override
    public void run()
    {
        double drawFrameInterval = (double) 1_000_000_000 /FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null)
        {
            currentTime = System.nanoTime();

            delta = delta + (currentTime - lastTime)/drawFrameInterval;

            lastTime = currentTime;

            if(delta >= 1)
            {
                // 1. Update: update the character's information
                update();
                // 2. Redraw: redraw the character's position
                repaint();

                delta--;
            }
        }
    }

    // A function to update everything in the game
    public void update()
    {
        // Update the player
        player.update();
    }

    // A function to draw everything in the game
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;

        // Draw the world
        tileDrawer.draw(g2D);
        // Draw the game objects
        drawGameObjects(g2D);
        // Draw the player
        player.draw(g2D);

        g2D.dispose();
    }

    public void drawGameObjects(Graphics2D g2D)
    {
        for (GameObject gameObject : gameObjects)
        {
            if (gameObject != null)
            {
                gameObject.draw(g2D, this);
            }
        }
    }
}
