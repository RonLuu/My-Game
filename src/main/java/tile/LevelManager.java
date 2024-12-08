package tile;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class LevelManager
{
    GamePanel gamePanel;
    Tile[] tile;
    int[][] level;

    public LevelManager(GamePanel gamePanel) throws FileNotFoundException
    {
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        level = new int[gamePanel.maxScreenRow][gamePanel.maxScreenCol];
        getTileImage();
        loadLevel();
    }

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
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    public void loadLevel() throws FileNotFoundException
    {
        try
        {
            File fileLevel = new File("src\\main\\resources\\Levels\\level1.txt");
            BufferedReader br = new BufferedReader(new FileReader(fileLevel));

            String line;
            int row = 0;
            while ((line = br.readLine()) != null)
            {
                String[] numbers = line.split(" ");
                for (int col = 0; col < gamePanel.maxScreenCol; col++)
                {
                    level[row][col] = Integer.parseInt(numbers[col]);
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
        for (int i = 0; i < gamePanel.maxScreenRow; i++)
        {
            for (int j = 0; j < gamePanel.maxScreenCol; j++)
            {
                g2D.drawImage(tile[level[i][j]].image,
                        j * gamePanel.tileSize,i * gamePanel.tileSize,
                        gamePanel.tileSize, gamePanel.tileSize, null);
            }
        }
    }
}
