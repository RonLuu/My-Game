package tile;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileDrawer
{
    // The game panel of the game
    GamePanel gamePanel;
    // All available tiles in the game
    public Tile[] tile;
    // The whole map
    public int[][] world;

    public TileDrawer(GamePanel gamePanel) throws FileNotFoundException
    {
        this.gamePanel = gamePanel;
        // There could be up to 10 distinct tiles in the game
        tile = new Tile[10];
        world = new int[gamePanel.maxWorldRow][gamePanel.maxWorldCol];
        // Get all the available tiles
        getTileImage();
        // Load the world to the 2D array
        loadWorldTo2DArray("src\\main\\resources\\Levels\\level_world.txt");
    }

    // Load all images to the tile array
    public void getTileImage() throws FileNotFoundException
    {
        try
        {
            // Load the floor tile image
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("src/main/resources/tile_images/floor.png"));
            // The player can touch the floor
            tile[0].notTouchable = false;

            // Load the top tile image
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("src/main/resources/tile_images/tile_top.png"));

            // Load the right tile image
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("src/main/resources/tile_images/tile_right.png"));

            // Load the bottom tile image
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("src/main/resources/tile_images/tile_bottom.png"));

            // Load the right tile image
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new File("src/main/resources/tile_images/tile_left.png"));

            // Load the corner tile image
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new File("src/main/resources/tile_images/corner.png"));

            // Load the stone tile image
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(new File("src/main/resources/tile_images/stone.png"));

            // Load the jar tile image
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(new File("src/main/resources/tile_images/jar.png"));
        }
        catch (IOException e)
        {
            throw new FileNotFoundException("Can't find tile image file");
        }
    }

    // Load the world given the file path
    public void loadWorldTo2DArray(String mapFile) throws FileNotFoundException
    {
        try
        {
            // Create a file variable and a reader variable
            File fileLevel = new File(mapFile);
            BufferedReader br = new BufferedReader(new FileReader(fileLevel));

            String line;
            int row = 0;
            // Read each line of the file
            while ((line = br.readLine()) != null)
            {
                // Get an array of string numbers
                String[] rowNumbers = line.split(" ");
                // For each string number in the array
                for (int col = 0; col < gamePanel.maxWorldCol; col++)
                {
                    // Place the number in world array
                    world[row][col] = Integer.parseInt(rowNumbers[col]);
                }
                // Move to the next row
                row++;
            }
            br.close();
        }
        catch(Exception e)
        {
            throw new FileNotFoundException("Can't find the world file");
        }
    }

    // A function to draw the part of world where the character is at the centre
    public void draw(Graphics2D g2D)
    {
        // From each row of the 2D array world map
        for (int worldRow = 0; worldRow < gamePanel.maxWorldRow; worldRow++)
        {
            // From each column of the row
            for (int worldCol = 0; worldCol < gamePanel.maxWorldCol; worldCol++)
            {
                // Get the current tile at that row and column
                int tileIndex = world[worldRow][worldCol];

                // Get the x and y position (the top left position) of that tile
                int topLeftX = worldCol * gamePanel.tileSize;
                int topLeftY = worldRow * gamePanel.tileSize;

                // Calculate where the tile should be drawn on the window
                // Get the distance between the tile and the player in the world
                // Add the distance of the player on the screen
                int screenX = (topLeftX - gamePanel.player.worldX) + gamePanel.player.screenX;
                int screenY = (topLeftY - gamePanel.player.worldY) + gamePanel.player.screenY;

                // Check if the tile can be seen on the screen
                if (onScreen(topLeftX, topLeftY))
                {
                    // Draw it
                    g2D.drawImage(tile[tileIndex].image, screenX, screenY,
                            gamePanel.tileSize, gamePanel.tileSize, null);
                }
            }
        }
    }

    private boolean onScreen(int topLeftX, int topLeftY)
    {
        return topLeftX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                topLeftX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                topLeftY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                topLeftY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY;
    }
}
