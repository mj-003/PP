package helpers;

import java.awt.*;
import java.awt.geom.Line2D;

public record BoundingBox(Point topLeft, Point topRight, Point bottomLeft, Point bottomRight) {

    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        float[] dashPattern = {5, 5};

        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
        g2d.setColor(Color.BLUE);

        g2d.draw(new Line2D.Float(topLeft.getX(), topLeft.getY(), topRight.getX(), topRight.getY()));
        g2d.draw(new Line2D.Float(topRight.getX(), topRight.getY(), bottomRight.getX(), bottomRight.getY()));
        g2d.draw(new Line2D.Float(bottomRight.getX(), bottomRight.getY(), bottomLeft.getX(), bottomLeft.getY()));
        g2d.draw(new Line2D.Float(bottomLeft.getX(), bottomLeft.getY(), topLeft.getX(), topLeft.getY()));

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));
    }

    public boolean contains(Point point)
    {
        return point.getX() >= topLeft.getX() && point.getX() <= topRight.getX() && point.getY() <= topLeft.getY() && point.getY() >= bottomLeft.getY();
    }
}
