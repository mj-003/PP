package items.primitive.shapes;

import helpers.Point;
import helpers.BoundingBox;

import java.awt.*;

public class Circle extends Shape {

    private int radius;


    /* ------------ Constructors ------------- */

    public Circle()
    {
        super();
        this.radius = 0;
        createBoundingBox();
    }

    public Circle(Point position, boolean filled, int radius)
    {
        super(position, filled);
        this.radius = radius;
        createBoundingBox();
    }



    /* ------------ Getters and Setters ------------- */

    public int getRadius() { return radius; }
    public void setRadius(int radius) { this.radius = radius; }



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
            g.fillOval(position.getX() - radius, position.getY() - radius, 2 * radius, 2 * radius);
        }
        else
        {
            g2d.setColor(Color.BLACK);
            g2d.drawOval(position.getX() - radius, position.getY() - radius, 2 * radius, 2 * radius);
        }
    }

    @Override
    public void createBoundingBox()
    {
        Point lowerLeft = new Point(position.getX() - radius, position.getY() - radius);
        Point upperRight = new Point(position.getX() + radius, position.getY() + radius);
        Point upperLeft = new Point(position.getX() - radius, position.getY() + radius);
        Point lowerRight = new Point(position.getX() + radius, position.getY() - radius);

        boundingBox = new BoundingBox(upperLeft, upperRight, lowerLeft, lowerRight);
    }

    @Override
    public boolean contains(Point p)
    {
        int distance = (int) Math.sqrt(Math.pow(p.getX() - position.getX(), 2) + Math.pow(p.getY() - position.getY(), 2));
        return distance <= radius;
    }

}
