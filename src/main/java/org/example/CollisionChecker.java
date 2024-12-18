package org.example;

import entity.Entity;
import Object.GameObject;
import entity.Player;
import tile.TileDrawer;

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
        int leftValueHitBox  = entity.worldX + entity.hitBox.x;
        // The x-value of the right side of the entity's hit box
        int rightValueHitBox  = entity.worldX + entity.hitBox.x + entity.hitBox.width;
        // The y-value of the top side of the entity's hit box
        int topValueHitBox  = entity.worldY + entity.hitBox.y;
        // The y-value of the bottom side of the entity's hit box
        int bottomValueHitBox  = entity.worldY + entity.hitBox.y + entity.hitBox.height;

        // Find which tile they allocate to
        int leftCol = leftValueHitBox/gamePanel.tileSize;
        int rightCol = rightValueHitBox/gamePanel.tileSize;
        int topRow = topValueHitBox/gamePanel.tileSize;
        int bottomRow = bottomValueHitBox/gamePanel.tileSize;

        int leftSide, rightSide;
        TileDrawer tileDrawer = gamePanel.tileDrawer;
        switch (entity.curDirection)
        {
            case "up":
                // Calculate the next value based on the step
                topRow = (topValueHitBox - entity.speed)/gamePanel.tileSize;
                leftSide = tileDrawer.world[topRow][leftCol];
                rightSide = tileDrawer.world[topRow][rightCol];
                if(tileDrawer.tile[leftSide].notTouchable || tileDrawer.tile[rightSide].notTouchable)
                {
                    entity.collided = true;
                }
                break;
            case "down":
                bottomRow = (bottomValueHitBox + entity.speed)/gamePanel.tileSize;
                leftSide = tileDrawer.world[bottomRow][leftCol];
                rightSide = tileDrawer.world[bottomRow][rightCol];
                if(tileDrawer.tile[leftSide].notTouchable || tileDrawer.tile[rightSide].notTouchable)
                {
                    entity.collided = true;
                }
                break;
            case "left":
                leftCol = (leftValueHitBox - entity.speed)/gamePanel.tileSize;
                leftSide = tileDrawer.world[topRow][leftCol];
                rightSide = tileDrawer.world[bottomRow][leftCol];
                if(tileDrawer.tile[leftSide].notTouchable || tileDrawer.tile[rightSide].notTouchable)
                {
                    entity.collided = true;
                }
                break;
            case "right":
                rightCol = (rightValueHitBox + entity.speed)/gamePanel.tileSize;
                leftSide = tileDrawer.world[topRow][leftCol];
                rightSide = tileDrawer.world[bottomRow][rightCol];
                if(tileDrawer.tile[leftSide].notTouchable || tileDrawer.tile[rightSide].notTouchable)
                {
                    entity.collided = true;
                }
                break;
        }
    }

//    Ignore this part please
//    public boolean checkTile(Entity entity)
//    {
//        // The x-value of the left side of the entity's hit box
//        int leftValueHitBox  = entity.worldX + entity.hitBox.x;
//        // The x-value of the right side of the entity's hit box
//        int rightValueHitBox  = entity.worldX + entity.hitBox.x + entity.hitBox.width;
//        // The y-value of the top side of the entity's hit box
//        int topValueHitBox  = entity.worldY + entity.hitBox.y;
//        // The y-value of the bottom side of the entity's hit box
//        int bottomValueHitBox  = entity.worldY + entity.hitBox.y + entity.hitBox.height;
//
//        // Find which tile they allocate to
//        int leftCol = leftValueHitBox/gamePanel.tileSize;
//        int rightCol = rightValueHitBox/gamePanel.tileSize;
//        int topRow = topValueHitBox/gamePanel.tileSize;
//        int bottomRow = bottomValueHitBox/gamePanel.tileSize;
//
//        int leftSide, rightSide;
//
//        switch (entity.curDirection)
//        {
//            case "up":
//                // Calculate the next value based on the step
//                topRow = (topValueHitBox - entity.speed)/gamePanel.tileSize;
//                leftSide = gamePanel.tileDrawer.world[topRow][leftCol];
//                rightSide = gamePanel.tileDrawer.world[topRow][rightCol];
//                if(gamePanel.tileDrawer.tile[leftSide].notTouchable ||
//                        gamePanel.tileDrawer.tile[rightSide].notTouchable)
//                {
//                    return true;
//                }
//                break;
//            case "down":
//                bottomRow = (bottomValueHitBox + entity.speed)/gamePanel.tileSize;
//                leftSide = gamePanel.tileDrawer.world[bottomRow][leftCol];
//                rightSide = gamePanel.tileDrawer.world[bottomRow][rightCol];
//                if(gamePanel.tileDrawer.tile[leftSide].notTouchable ||
//                        gamePanel.tileDrawer.tile[rightSide].notTouchable)
//                {
//                    return true;
//                }
//                break;
//            case "left":
//                leftCol = (leftValueHitBox - entity.speed)/gamePanel.tileSize;
//                leftSide = gamePanel.tileDrawer.world[topRow][leftCol];
//                rightSide = gamePanel.tileDrawer.world[bottomRow][leftCol];
//                if(gamePanel.tileDrawer.tile[leftSide].notTouchable ||
//                        gamePanel.tileDrawer.tile[rightSide].notTouchable)
//                {
//                    return true;
//                }
//                break;
//            case "right":
//                rightCol = (rightValueHitBox + entity.speed)/gamePanel.tileSize;
//                leftSide = gamePanel.tileDrawer.world[topRow][leftCol];
//                rightSide = gamePanel.tileDrawer.world[bottomRow][rightCol];
//                if(gamePanel.tileDrawer.tile[leftSide].notTouchable ||
//                        gamePanel.tileDrawer.tile[rightSide].notTouchable)
//                {
//                    return true;
//                }
//                break;
//        }
//        return false;
//    }

    public int checkObject(Entity entity)
    {
        int index = 999;
        for(int i = 0; i < gamePanel.gameObjects.length; i++)
        {
            GameObject curObject = gamePanel.gameObjects[i];
            if(curObject != null)
            {
                // get entity's solid area position
                entity.hitBox.x += entity.worldX;
                entity.hitBox.y += entity.worldY;

                // get the object's solid area position
                curObject.hitBox.x += curObject.worldX;
                curObject.hitBox.y += curObject.worldY;

                switch (entity.curDirection)
                {
                    case "up" :
                        entity.hitBox.y -= entity.speed;
                        break;
                    case "down" :
                        entity.hitBox.y += entity.speed;
                        break;
                    case "left" :
                        entity.hitBox.x -= entity.speed;
                        break;
                    case "right" :
                        entity.hitBox.x += entity.speed;
                        break;
                }

                //?? Apparently I can't make this function?
                if(entity.hitBox.intersects(curObject.hitBox))
                {
                    if (curObject.collision)
                    {
                        entity.collided = true;
                    }

                    if (entity instanceof Player)
                    {
                        index = i;
                    }
                }

                entity.hitBox.x = entity.hitBoxDefaultX;
                entity.hitBox.y = entity.hitBoxDefaultY;

                curObject.hitBox.x = curObject.solidAreaDefaultX;
                curObject.hitBox.y = curObject.solidAreaDefaultY;
            }
        }
        return index;
    }

    // Ignore this part
//    public int checkCollisionWithObject(Entity entity, GameObject curObject, int i, boolean player)
//    {
//        if(entity.hitBox.intersects(curObject.hitBox))
//        {
//            if (curObject.collision)
//            {
//                entity.collided = true;
//            }
//
//            if (player)
//            {
//                return i;
//            }
//        }
//
//        return 999;
//    }

}
