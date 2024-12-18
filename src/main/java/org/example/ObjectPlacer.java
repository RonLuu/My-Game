package org.example;
import Object.*;

import java.io.FileNotFoundException;

// A class to place object in the world
public class ObjectPlacer
{
    GamePanel gamePanel;
    public ObjectPlacer(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
    }

    public void setObject() throws FileNotFoundException
    {
        GameObject[] gameObjects = gamePanel.gameObjects;

        gameObjects[0] = new Key();
        setObjectPosition(gameObjects[0], 30, 11);

        gameObjects[1] = new Bomb();
        setObjectPosition(gameObjects[1], 29, 5);

        gameObjects[2] = new Chest();
        setObjectPosition(gameObjects[2], 30, 2);
    }

    // A function to set the game object's position
    private void setObjectPosition(GameObject gameObject, int squareX, int squareY)
    {
        gameObject.worldX = squareX * gamePanel.tileSize;
        gameObject.worldY = squareY * gamePanel.tileSize;
    }
}
