package Object;

import org.example.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

// A class to represent an object (interactable) in the game
public class GameObject
{
    // The game object's image
    public BufferedImage image;
    // The game object's name (could use the type class instead)?
    public String name;
    //
    public boolean collision = false;
    // The entire image is the hit box
    public Rectangle hitBox = new Rectangle(0,0,48, 48);

    //?? Do we really need this variable?
    public final int solidAreaDefaultX = 0;
    public final int solidAreaDefaultY = 0;

    // The coordinate of the entity with respect to the world
    public int worldX, worldY;

    // should add a draw class?
    public void draw(Graphics2D g2D, GamePanel gamePanel)
    {

        // Calculate where the tile should be drawn on the window
        int screenX = (worldX - gamePanel.player.worldX) + gamePanel.player.screenX;
        int screenY = (worldY - gamePanel.player.worldY) + gamePanel.player.screenY;

        // Calculate where the tile should be drawn on the window
        // Get the distance between the tile and the player in the world
        // Add the distance of the player on the screen
        if (onScreen(gamePanel))
        {
            g2D.drawImage(image, screenX, screenY,
                    gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }

    private boolean onScreen(GamePanel gamePanel)
    {
        return worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY;
    }

}
