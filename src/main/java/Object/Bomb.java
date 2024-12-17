package Object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;

public class Bomb extends GameObject
{
    public Bomb() throws FileNotFoundException
    {
        this.name = "Bomb";
        try
        {
            image = ImageIO.read(new File("src\\main\\resources\\Object\\Bomb.png"));
        }
        catch (Exception e)
        {
            throw new FileNotFoundException("Can't not find image bomb");
        }
    }
}
