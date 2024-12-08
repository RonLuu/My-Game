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

    public Player(GamePanel gamePanel, KeyHandler keyHandler)
    {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultValue();
        getPlayerImage();
    }

    // Setting the default value from the coordinate and speed
    public void setDefaultValue()
    {
        this.x = 100;
        this.y = 100;
        this.speed = 4;
        direction = "down";
    }

    // A function to load the player's image
    public void getPlayerImage()
    {
        try
        {
            upImage1 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_back_1.png"));
            upImage2 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_back_2.png"));
            upImage3 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_back_3.png"));

            leftImage1 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_left_1.png"));
            leftImage2 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_left_2.png"));
            leftImage3 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_left_3.png"));

            downImage1 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_front_1.png"));
            downImage2 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_front_2.png"));
            downImage3 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_front_3.png"));

            rightImage1 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_right_1.png"));
            rightImage2 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_right_2.png"));
            rightImage3 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_right_3.png"));
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
            y -= speed;
        }
        if(keyHandler.downPressed)
        {
            direction = "down";
            y += speed;
        }
        if(keyHandler.leftPressed)
        {
            direction = "left";
            x -= speed;
        }
        if(keyHandler.rightPressed)
        {
            direction = "right";
            x += speed;
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
        BufferedImage image = null;
        // Depending on the direction
        // Change the image of the player
        switch (direction)
        {
            case "up":
                if (spriteNum == 1)
                {
                    image = upImage1;
                }

                if (spriteNum == 2)
                {
                    image = upImage2;
                }
                if (spriteNum == 3)
                {
                    image = upImage3;
                }
                break;
            case "down":
                if (spriteNum == 1)
                {
                    image = downImage1;
                }
                if (spriteNum == 2)
                {
                    image = downImage2;
                }
                if (spriteNum == 3)
                {
                    image = downImage3;
                }
                break;
            case "left":
                if (spriteNum == 1)
                {
                    image = leftImage1;
                }
                if (spriteNum == 2)
                {
                    image = leftImage2;
                }
                if (spriteNum == 3)
                {
                    image = leftImage3;
                }
                break;
            case "right":
                if (spriteNum == 1)
                {
                    image = rightImage1;
                }
                if (spriteNum == 2)
                {
                    image = rightImage2;
                }
                if (spriteNum == 3)
                {
                    image = rightImage3;
                }
                break;
        }

        // Draw the player with its image and position
        g2D.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
