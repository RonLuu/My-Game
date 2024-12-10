package entity;

import java.awt.image.BufferedImage;

public class Entity
{
    public int worldX, worldY;
    int speed;

    public BufferedImage[] upImages;
    public BufferedImage[] leftImages;
    public BufferedImage[] downImages;
    public BufferedImage[] rightImages;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
}
