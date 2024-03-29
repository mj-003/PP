package items.primitive;

import helpers.Point;
import helpers.BoundingBox;

import java.awt.*;

public class Segment extends Primitive {
    Point startPoint;
    Point endPoint;
    int length;


    /* ------------ Constructors ------------- */

    public Segment()
    {
        super();
        this.startPoint = new Point(0, 0);
        this.endPoint = new Point(0, 0);
        this.length = 0;
        createBoundingBox();
    }
    public Segment(Point startPoint, Point endPoint)
    {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.length = (int) Math.sqrt(Math.pow(endPoint.getX() - startPoint.getX(), 2) + Math.pow(endPoint.getY() - startPoint.getY(), 2));
        createBoundingBox();
    }


    /* ------------ Getters and Setters ------------- */

    public Point getStartPoint() { return startPoint; }

    public void setStartPoint(Point startPoint) { this.startPoint = startPoint; }

    public Point getEndPoint() { return endPoint; }

    public void setEndPoint(Point endPoint) { this.endPoint = endPoint; }

    public int getLength() { return length; }


    /* ------------ Abstract Methods ------------- */

    @Override
    public void translate(int dx, int dy)
    {
        startPoint.setX(startPoint.getX() + dx);
        startPoint.setY(startPoint.getY() + dy);
        endPoint.setX(endPoint.getX() + dx);
        endPoint.setY(endPoint.getY() + dy);
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

        g2d.setColor(Color.BLACK);
        g2d.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
    }

    @Override
    public void createBoundingBox()
    {
        Point topLeft = new Point(Math.min(startPoint.getX(), endPoint.getX()), Math.max(startPoint.getY(), endPoint.getY()));
        Point topRight = new Point(Math.max(startPoint.getX(), endPoint.getX()), Math.max(startPoint.getY(), endPoint.getY()));
        Point bottomLeft = new Point(Math.min(startPoint.getX(), endPoint.getX()), Math.min(startPoint.getY(), endPoint.getY()));
        Point bottomRight = new Point(Math.max(startPoint.getX(), endPoint.getX()), Math.min(startPoint.getY(), endPoint.getY()));

        boundingBox = new BoundingBox(topLeft, topRight, bottomLeft, bottomRight);
    }

    @Override
    public boolean contains(Point point)
    {
        createBoundingBox();
        return boundingBox.contains(point);
    }
}
