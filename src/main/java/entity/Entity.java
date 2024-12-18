package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity
{
    // The coordinate of the entity with respect to the world
    public int worldX, worldY;
    // The entity's speed
    public int speed;

    // All the images for going upward
    public BufferedImage[] upImages;
    // All the images for going to the left
    public BufferedImage[] leftImages;
    // All the images for going downwards
    public BufferedImage[] downImages;
    // All the images for going to the right
    public BufferedImage[] rightImages;

    // The entity's direction
    public String curDirection;
    // The entity's valid direction
    public String[] directions = {"up", "left", "down", "right"};

    public int spriteCounter = 0;
    public int spriteNum = 1;

    // The entity hit box
    public Rectangle hitBox;
    //??
    public int hitBoxDefaultX, hitBoxDefaultY;
    // The entity's collision status
    public boolean collided = false;
}
