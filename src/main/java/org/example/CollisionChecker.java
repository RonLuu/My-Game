package org.example;

import entity.Entity;

public class CollisionChecker
{
    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity)
    {
        // The x-value of the left side of the entity's hit box
        int leftValueHitBox  = entity.worldX + entity.solidArea.x;
        // The x-value of the right side of the entity's hit box
        int rightValueHitBox  = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        // The y-value of the top side of the entity's hit box
        int topValueHitBox  = entity.worldY + entity.solidArea.y;
        // The y-value of the bottom side of the entity's hit box
        int bottomValueHitBox  = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        // Find which tile they allocate to
        int leftCol = leftValueHitBox/gamePanel.tileSize;
        int rightCol = rightValueHitBox/gamePanel.tileSize;
        int topRow = topValueHitBox/gamePanel.tileSize;
        int bottomRow = bottomValueHitBox/gamePanel.tileSize;

        int leftSide, rightSide;

        switch (entity.curDirection)
        {
            case "up":
                // Calculate the next value based on the step
                topRow = (topValueHitBox - entity.speed)/gamePanel.tileSize;
                leftSide = gamePanel.worldManager.world[topRow][leftCol];
                rightSide = gamePanel.worldManager.world[topRow][rightCol];
                if(gamePanel.worldManager.tile[leftSide].collision ||
                        gamePanel.worldManager.tile[rightSide].collision)
                {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                bottomRow = (bottomValueHitBox + entity.speed)/gamePanel.tileSize;
                leftSide = gamePanel.worldManager.world[bottomRow][leftCol];
                rightSide = gamePanel.worldManager.world[bottomRow][rightCol];
                if(gamePanel.worldManager.tile[leftSide].collision||
                        gamePanel.worldManager.tile[rightSide].collision)
                {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                leftCol = (leftValueHitBox - entity.speed)/gamePanel.tileSize;
                leftSide = gamePanel.worldManager.world[topRow][leftCol];
                rightSide = gamePanel.worldManager.world[bottomRow][leftCol];
                if(gamePanel.worldManager.tile[leftSide].collision||
                        gamePanel.worldManager.tile[rightSide].collision)
                {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                rightCol = (rightValueHitBox + entity.speed)/gamePanel.tileSize;
                leftSide = gamePanel.worldManager.world[topRow][leftCol];
                rightSide = gamePanel.worldManager.world[bottomRow][rightCol];
                if(gamePanel.worldManager.tile[leftSide].collision||
                        gamePanel.worldManager.tile[rightSide].collision)
                {
                    entity.collisionOn = true;
                }
                break;
        }

    }
}
