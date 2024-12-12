package tile;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Arrays;

public class LevelManager
{
    GamePanel gamePanel;
    Tile[] tile;
    int[][] level;

    public LevelManager(GamePanel gamePanel) throws FileNotFoundException
    {
        this.gamePanel = gamePanel;
        // There could be up to 10 different tiles in the game
        tile = new Tile[10];
        level = new int[gamePanel.maxWorldRow][gamePanel.maxWorldCol];
        getTileImage();
        loadLevel("src\\main\\resources\\Levels\\level_world.txt");
    }

    // Get all the tile images
    public void getTileImage()
    {
        try
        {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("src/main/resources/tile_images/floor.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("src/main/resources/tile_images/tile_top.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("src/main/resources/tile_images/tile_left.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("src/main/resources/tile_images/tile_bottom.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new File("src/main/resources/tile_images/tile_right.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new File("src/main/resources/tile_images/corner.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(new File("src/main/resources/tile_images/stone.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(new File("src/main/resources/tile_images/jar.png"));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    // Load a level given the file path
    public void loadLevel(String mapFile) throws FileNotFoundException
    {
        try
        {
            File fileLevel = new File(mapFile);
            BufferedReader br = new BufferedReader(new FileReader(fileLevel));

            String line;
            int row = 0;
            while ((line = br.readLine()) != null)
            {
                String[] rowNumbers = line.split(" ");
                for (int col = 0; col < gamePanel.maxWorldCol; col++)
                {
                    level[row][col] = Integer.parseInt(rowNumbers[col]);
                }
                row++;
            }
            br.close();
        }
        catch(Exception e)
        {
            throw new FileNotFoundException();
        }

    }

    public void draw(Graphics2D g2D)
    {
        for (int worldRow = 0; worldRow < gamePanel.maxWorldRow; worldRow++)
        {
            for (int worldCol = 0; worldCol < gamePanel.maxWorldCol; worldCol++)
            {
                int tileNum = level[worldRow][worldCol];
                int squareX = worldCol * gamePanel.tileSize;
                int squareY = worldRow * gamePanel.tileSize;

                int screenX = squareX - gamePanel.player.worldX + gamePanel.player.screenX;
                int screenY = squareY - gamePanel.player.worldY + gamePanel.player.screenY;

                if(squareX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                    squareX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                    squareY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                    squareY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY)
                {
                    g2D.drawImage(tile[tileNum].image, screenX, screenY,
                            gamePanel.tileSize, gamePanel.tileSize, null);
                }
            }
        }
    }
}
