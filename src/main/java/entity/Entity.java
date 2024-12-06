package entity;

import java.awt.image.BufferedImage;

public class Entity
{
    int x,y;
    int speed;

    public BufferedImage upImage1, upImage2, upImage3;
    public BufferedImage downImage1, downImage2, downImage3;
    public BufferedImage leftImage1, leftImage2, leftImage3;
    public BufferedImage rightImage1, rightImage2, rightImage3;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
}
