package items.primitive.shapes;

import helpers.Point;
import helpers.BoundingBox;
import helpers.Singleton;
import items.Item;
import items.composite.ComplexItem;

import java.awt.*;
import java.util.List;

public class Spiral extends Shape implements Singleton {
    private int startRadius;
    private int endRadius;
    private int numTurns;


    /* ------------ Constructors ------------- */

    public Spiral()
    {
        super();
        this.startRadius = 0;
        this.endRadius = 0;
        this.numTurns = 0;
        createBoundingBox();
    }
    public Spiral(Point position, boolean filled, int startRadius, int endRadius, int numTurns)
    {
        super(position, filled);
        this.startRadius = startRadius;
        this.endRadius = endRadius;
        this.numTurns = numTurns;
        createBoundingBox();
    }


    /* ------------ Getters and Setters ------------- */

    public int getStartRadius() {
        return startRadius;
    }

    public void setStartRadius(int startRadius) {
        this.startRadius = startRadius;
    }

    public int getEndRadius() {
        return endRadius;
    }

    public void setEndRadius(int endRadius) {
        this.endRadius = endRadius;
    }

    public int getNumTurns() {
        return numTurns;
    }

    public void setNumTurns(int numTurns) {
        this.numTurns = numTurns;
    }


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
        if (selected)
        {
            createBoundingBox();
            boundingBox.draw(g);
        }

        double angleStep = 0.1;

        int lastX = position.getX() + (int) (startRadius * Math.cos(0));
        int lastY = position.getY() + (int) (startRadius * Math.sin(0));

        for (double angle = angleStep; angle <= 2 * Math.PI * numTurns; angle += angleStep)
        {
            double fraction = angle / (2 * Math.PI * numTurns);
            double radius = startRadius + fraction * (endRadius - startRadius);
            int x = position.getX() + (int) (radius * Math.cos(angle));
            int y = position.getY() + (int) (radius * Math.sin(angle));

            if (filled) g.setColor(currentFillColor);
            else g.setColor(Color.BLACK);

            g.drawLine(lastX, lastY, x, y);

            lastX = x;
            lastY = y;
        }
    }

    @Override
    public void createBoundingBox()
    {
        Point lowerLeft = new Point(position.getX() - startRadius, position.getY() - startRadius);
        Point upperRight = new Point(position.getX() + startRadius, position.getY() + startRadius);
        Point upperLeft = new Point(position.getX() - startRadius, position.getY() + startRadius);
        Point lowerRight = new Point(position.getX() + startRadius, position.getY() - startRadius);

        boundingBox = new BoundingBox(upperLeft, upperRight, lowerLeft, lowerRight);
    }

    @Override
    public boolean contains(Point p)
    {
        int distance = (int) Math.sqrt(Math.pow(p.getX() - position.getX(), 2) + Math.pow(p.getY() - position.getY(), 2));
        return distance <= startRadius;
    }


    /* ---------- Mixin ---------- */

    @Override
    public void removeItem(List<Item> list)
    {
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i) instanceof ComplexItem)
                removeItem(((ComplexItem) list.get(i)).getChildren());

            if (list.get(i) instanceof Singleton)
                list.remove(i);
        }

    }
}
