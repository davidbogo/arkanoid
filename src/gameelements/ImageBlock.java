package gameelements;

import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageBlock extends Block {
    private Image image;
    private Color stroke;

    /**
     * construct a block from two coordinates,
     * width, height, color and initial number of hits.
     *
     * @param x             the x coordinate of the initial location of the block's upper left corner.
     * @param y             the y coordinate of the initial location of the block's upper left corner.
     * @param width         the block's width.
     * @param height        the block's height.
     * @param strokeColor   the stroke color.
     * @param hits          the hits
     */
    public ImageBlock(double x, double y, double width, double height, String imageFile, Color strokeColor, int hits) {
        super(x, y, width, height, hits);
        this.stroke = strokeColor;
        image = null;
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL resource = classLoader.getResource(imageFile);
        try {
            image = ImageIO.read(new File(resource.getPath()));
        } catch (IOException e) {
        }
    }

    /**
     * this method draws the block on given DrawSurface.
     * @param surface the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.drawImage(
                (int) Math.round(this.rectangle.getUpperLeft().getX()),
                (int) Math.round(this.rectangle.getUpperLeft().getY()),
                image);
        surface.setColor(this.stroke);
        surface.drawRectangle(
                (int) Math.round(this.rectangle.getUpperLeft().getX()),
                (int) Math.round(this.rectangle.getUpperLeft().getY()),
                (int) Math.round(this.rectangle.getWidth()),
                (int) Math.round(this.rectangle.getHeight()));
    }
}
