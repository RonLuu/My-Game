package entity;

import org.example.GamePanel;
import org.example.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity
{
    GamePanel gamePanel;
    KeyHandler keyHandler;

    // The position of the camera on the player
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler)
    {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        screenX = gamePanel.screenWidth/2 - gamePanel.tileSize/2;
        screenY = gamePanel.screenHeight/2 - gamePanel.tileSize/2;
        setDefaultValue();
        getPlayerImage();
    }

    // Setting the default value from the coordinate and speed
    public void setDefaultValue()
    {
        this.worldX = 100;
        this.worldY = 100;
        this.speed = 4;
        direction = "down";
    }

    // A function to load the player's image
    public void getPlayerImage()
    {
        try
        {
            BufferedImage upImage1 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_back_1.png"));
            BufferedImage upImage2 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_back_2.png"));
            BufferedImage upImage3 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_back_3.png"));
            upImages = new BufferedImage[]{upImage1, upImage2, upImage3};

            BufferedImage leftImage1 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_left_1.png"));
            BufferedImage leftImage2 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_left_2.png"));
            BufferedImage leftImage3 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_left_3.png"));
            leftImages = new BufferedImage[]{leftImage1, leftImage2, leftImage3};

            BufferedImage downImage1 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_front_1.png"));
            BufferedImage downImage2 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_front_2.png"));
            BufferedImage downImage3 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_front_3.png"));
            downImages = new BufferedImage[]{downImage1, downImage2, downImage3};

            BufferedImage rightImage1 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_right_1.png"));
            BufferedImage rightImage2 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_right_2.png"));
            BufferedImage rightImage3 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_right_3.png"));
            rightImages = new BufferedImage[]{rightImage1, rightImage2, rightImage3};
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    // A function to handle the player's position
    public void movementHandler()
    {
        // Depending on what key has pressed
        // Change the direction and position of the character
        if(keyHandler.upPressed)
        {
            direction = "up";
            worldY -= speed;
        }
        if(keyHandler.downPressed)
        {
            direction = "down";
            worldY += speed;
        }
        if(keyHandler.leftPressed)
        {
            direction = "left";
            worldX -= speed;
        }
        if(keyHandler.rightPressed)
        {
            direction = "right";
            worldX += speed;
        }
    }

    // A function to handle the player's image
    public void spriteNumHandler()
    {
        spriteCounter++;
        // If the counter reaches to 10
        if(spriteCounter > 10)
        {
            // Reset the counter
            spriteCounter = 0;

            // Change
            if(spriteNum == 1)
            {
                spriteNum = 2;
            }
            else if (spriteNum == 2)
            {
                spriteNum = 3;
            }
            else if (spriteNum == 3)
            {
                spriteNum = 1;
            }
        }
    }

    // A function to update the player's position and image
    public void update()
    {
        movementHandler();
        spriteNumHandler();
    }


    // A function to draw the current state (position and image) of the player
    public void draw(Graphics2D g2D)
    {
        BufferedImage image = getImage();
        // Draw the player with its image and position
        g2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }

    public BufferedImage getImage()
    {
        // Depending on the direction
        // Change the image of the player
        return switch (direction)
        {
            case "up" -> upImages[spriteNum - 1];
            case "down" -> downImages[spriteNum - 1];
            case "left" -> leftImages[spriteNum - 1];
            case "right" -> rightImages[spriteNum - 1];
            default -> null;

        };
    }
}
