package levels;

import biuoop.DrawSurface;
import geometry.Rectangle;
import miscellaneous.Sprite;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageBackground implements Sprite {
    private Image image;

    public ImageBackground(String imageFile) {
        image = null;
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL resource = classLoader.getResource(imageFile);
        try {
            image = ImageIO.read(new File(resource.getPath()));
        } catch (IOException e) {
        }
    }

    /**
     * this method draws the background on given DrawSurface.
     * @param d the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.drawImage(0, 0, image);
    }

    /**
     * this method notifies the background that a time unit has passed.
     */
    public void timePassed() {
    }
}
