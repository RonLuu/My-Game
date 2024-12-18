package Object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;

public class Chest extends GameObject
{
    public Chest() throws FileNotFoundException
    {
        this.name = "Key";
        try
        {
            image = ImageIO.read(new File("src\\main\\resources\\Object\\Chest.png"));
        }
        catch (Exception e)
        {
            throw new FileNotFoundException("Can't not find image chest");
        }
    }
}
