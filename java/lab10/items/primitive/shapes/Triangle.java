package items.primitive.shapes;

import helpers.Point;
import helpers.BoundingBox;

import java.awt.*;

public class Triangle extends Shape {
    private Point p1;
    private Point p2;
    private Point p3;


    /* ------------ Constructors ------------- */

    public Triangle()
    {
        super();
        this.p1 = new Point(0, 0);
        this.p2 = new Point(0, 0);
        this.p3 = new Point(0, 0);
        createBoundingBox();
    }
    public Triangle(Point position, boolean filled, Point p1, Point p2, Point p3)
    {
        super(position, filled);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        createBoundingBox();
    }


    /* ------------ Getters and Setters ------------- */

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public Point getP3() {
        return p3;
    }

    public void setP3(Point p3) {
        this.p3 = p3;
    }


    /* ------------ Abstract Methods ------------- */

    @Override
    public void translate(int dx, int dy)
    {
        p1.setX(p1.getX() + dx);
        p1.setY(p1.getY() + dy);
        p2.setX(p2.getX() + dx);
        p2.setY(p2.getY() + dy);
        p3.setX(p3.getX() + dx);
        p3.setY(p3.getY() + dy);
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

        int[] xPoints = {p1.getX(), p2.getX(), p3.getX()};
        int[] yPoints = {p1.getY(), p2.getY(), p3.getY()};

        if (filled)
        {
            g2d.setColor(currentFillColor);
            g2d.fillPolygon(xPoints, yPoints, 3);
        }
        else
        {
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(xPoints, yPoints, 3);
        }
    }

    @Override
    public void createBoundingBox()
    {
        int minX = Math.min(Math.min(p1.getX(), p2.getX()), p3.getX());
        int minY = Math.min(Math.min(p1.getY(), p2.getY()), p3.getY());
        int maxX = Math.max(Math.max(p1.getX(), p2.getX()), p3.getX());
        int maxY = Math.max(Math.max(p1.getY(), p2.getY()), p3.getY());

        Point lowerLeft = new Point(minX, minY);
        Point upperRight = new Point(maxX, maxY);
        Point upperLeft = new Point(minX, maxY);
        Point lowerRight = new Point(maxX, minY);

        boundingBox = new BoundingBox(upperLeft, upperRight, lowerLeft, lowerRight);
    }

    @Override
    public boolean contains(Point point)
    {
        double d1, d2, d3;
        boolean hasNeg, hasPos;

        d1 = sign(point, p1, p2);
        d2 = sign(point, p2, p3);
        d3 = sign(point, p3, p1);

        hasNeg = (d1 < 0) || (d2 < 0) || (d3 < 0);
        hasPos = (d1 > 0) || (d2 > 0) || (d3 > 0);

        return !(hasNeg && hasPos);
    }

    private double sign(Point p1, Point p2, Point p3)
    {
        return (p1.getX() - p3.getX()) * (p2.getY() - p3.getY()) - (p2.getX() - p3.getX()) * (p1.getY() - p3.getY());
    }
}
