package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity
{
    // The coordinate of the entity with respect to the world
    public int worldX, worldY;
    public int speed;

    public BufferedImage[] upImages;
    public BufferedImage[] leftImages;
    public BufferedImage[] downImages;
    public BufferedImage[] rightImages;
    public String curDirection;
    public String[] directions = {"up", "left", "down", "right"};

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public boolean collisionOn;
}
