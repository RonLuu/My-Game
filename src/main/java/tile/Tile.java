package tile;

import java.awt.image.BufferedImage;

// A class to represent a tile (A background image, can't interact) in the game
public class Tile
{
    // An image of the tile
    public BufferedImage image;
    // Set the tile to not collidable
    public boolean notTouchable = true;
}
