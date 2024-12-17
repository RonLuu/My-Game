package org.example;
import Object.*;

import java.io.FileNotFoundException;

public class ObjectPlacer
{
    GamePanel gamePanel;
    public ObjectPlacer(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
    }

    public void setObject() throws FileNotFoundException
    {
        gamePanel.gameObjects[0] = new Key();
        int squareX = 30;
        int squareY = 11;

        gamePanel.gameObjects[0].worldX = squareX * gamePanel.tileSize;
        gamePanel.gameObjects[0].worldY = squareY * gamePanel.tileSize;

        gamePanel.gameObjects[1] = new Bomb();
        squareX = 29;
        squareY = 5;

        gamePanel.gameObjects[1].worldX = squareX * gamePanel.tileSize;
        gamePanel.gameObjects[1].worldY = squareY * gamePanel.tileSize;
    }
}
