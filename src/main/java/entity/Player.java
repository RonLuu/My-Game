package entity;

import org.example.GamePanel;
import org.example.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Player extends Entity
{
    // The game panel of the game
    GamePanel gamePanel;
    // A keyHandler to notify which direction button's been pressed
    KeyHandler keyHandler;

    // The position of the player to draw on the window
    public final int screenX;
    public final int screenY;

    //
    private final int spriteSpeed = 5;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) throws FileNotFoundException
    {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        // The player should be drawn at the centre of the window
        screenX = gamePanel.windowWidth/2 - gamePanel.tileSize/2;
        screenY = gamePanel.windowHeight/2 - gamePanel.tileSize/2;

        setDefaultValue();
        getPlayerImage();
    }

    // Setting the default value from the coordinate and speed
    public void setDefaultValue()
    {
        // The player is placed at the centre of the world
        this.worldX = (gamePanel.maxWorldCol/2) * gamePanel.tileSize - gamePanel.tileSize/2;
        this.worldY = (gamePanel.maxWorldRow/2) * gamePanel.tileSize - gamePanel.tileSize/2;

        // The player's speed is 4
        this.speed = 4;

        // The default direction is down
        this.curDirection = this.directions[2];

        this.hitBox = new Rectangle(8,16,32,32);
        hitBoxDefaultX = hitBox.x;
        hitBoxDefaultY = hitBox.y;

    }

    // A function to load the player's image
    public void getPlayerImage() throws FileNotFoundException
    {
        try
        {
            // Load all upward images
            BufferedImage upImage1 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_back_1.png"));
            BufferedImage upImage2 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_back_2.png"));
            BufferedImage upImage3 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_back_3.png"));
            upImages = new BufferedImage[]{upImage1, upImage2, upImage3};

            // Load all leftward images
            BufferedImage leftImage1 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_left_1.png"));
            BufferedImage leftImage2 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_left_2.png"));
            BufferedImage leftImage3 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_left_3.png"));
            leftImages = new BufferedImage[]{leftImage1, leftImage2, leftImage3};

            // Load all downwards images
            BufferedImage downImage1 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_front_1.png"));
            BufferedImage downImage2 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_front_2.png"));
            BufferedImage downImage3 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_front_3.png"));
            downImages = new BufferedImage[]{downImage1, downImage2, downImage3};

            // Load all rightwards images
            BufferedImage rightImage1 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_right_1.png"));
            BufferedImage rightImage2 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_right_2.png"));
            BufferedImage rightImage3 = ImageIO.read(new File("src\\main\\resources\\Isaac_images\\Isaac_right_3.png"));
            rightImages = new BufferedImage[]{rightImage1, rightImage2, rightImage3};
        }
        catch(IOException e)
        {
            throw new FileNotFoundException("Can't find the player's image");
        }
    }

    // A function to handle the player's position
    public void movementHandler()
    {
        // If a key has been pressed
        if(keyHandler.isPressed())
        {
            // Depending on what key has pressed
            // Change the direction and position of the character
            curDirection = directions[keyHandler.getDirection()];
            collided = false;

            // Check if the player is colliding a tile
            gamePanel.collisionChecker.checkTile(this);

            // Check if the player is colliding an object
            int objIndex = gamePanel.collisionChecker.checkObject(this);
            pickUpObject(objIndex);

            // If the player is not colliding
            if (!collided)
            {
                // Make the player "stand still"
                switch (curDirection)
                {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
        }
    }
    public void pickUpObject(int index)
    {
        if (index != 999)
        {
            gamePanel.gameObjects[index] = null;
        }
    }
    // A function to handle the player's image
    public void spriteNumHandler()
    {
        spriteCounter++;
        // If the counter reaches to 10
        if(spriteCounter > spriteSpeed)
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

    public BufferedImage getImage()
    {
        // Depending on the direction
        // Change the image of the player
        return switch (curDirection)
        {
            case "up" -> upImages[spriteNum - 1];
            case "down" -> downImages[spriteNum - 1];
            case "left" -> leftImages[spriteNum - 1];
            case "right" -> rightImages[spriteNum - 1];
            default -> null;
        };
    }

    // A function to draw the current state (position and image) of the player
    public void draw(Graphics2D g2D)
    {
        BufferedImage image = getImage();
        // Draw the player with its image and position
        g2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }

}
