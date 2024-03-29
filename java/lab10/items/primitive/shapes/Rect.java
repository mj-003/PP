package items.primitive.shapes;

import helpers.Point;
import helpers.BoundingBox;

import java.awt.*;

public class Rect extends Shape {
    private int width;
    private int height;


    /* ------------ Constructors ------------- */

    public Rect()
    {
        super();
        this.width = 0;
        this.height = 0;
        createBoundingBox();
    }

    public Rect(Point position, boolean filled, int width, int height)
    {
        super(position, filled);
        this.width = width;
        this.height = height;
        createBoundingBox();
    }


    /* ------------ Getters and Setters ------------- */

    public int getWidth() { return width; }

    public void setWidth(int width) { this.width = width; }

    public int getHeight() { return height; }

    public void setHeight(int height) { this.height = height; }


    /* ------------ Abstract Methods ------------- */

    @Override
    public void translate(int dx, int dy)
    {
        position.setX(position.getX() + dx);
        position.setY(position.getY() + dy);
        createBoundingBox();
    }

    @Override
    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (selected)
        {
            createBoundingBox();
            boundingBox.draw(g);
        }

        if (filled)
        {
            g2d.setColor(currentFillColor);
            g2d.fillRect(position.getX() - width / 2, position.getY() - height / 2, width, height);
        }
        else
        {
            g2d.setColor(Color.BLACK);
            g2d.drawRect(position.getX() - width / 2, position.getY() - height / 2, width, height);
        }
    }

    @Override
    public void createBoundingBox()
    {
        Point lowerLeft = new Point(position.getX() - width / 2, position.getY() - height / 2);
        Point upperRight = new Point(position.getX() + width / 2, position.getY() + height / 2);
        Point upperLeft = new Point(position.getX() - width / 2, position.getY() + height / 2);
        Point lowerRight = new Point(position.getX() + width / 2, position.getY() - height / 2);

        boundingBox = new BoundingBox(upperLeft, upperRight, lowerLeft, lowerRight);
    }

    @Override
    public boolean contains(Point p)
    {
        createBoundingBox();
        return boundingBox.contains(p);
    }
}
