package items.primitive.shapes;

import helpers.Point;
import items.primitive.Primitive;

import java.awt.*;


public abstract class Shape extends Primitive {
    protected boolean filled;
    protected Color currentFillColor;

    public Shape()
    {
        super();
    }

    public Shape(Point position, boolean filled)
    {
        super(position);
        this.filled = filled;
    }

    public void setFilled(boolean filled) { this.filled = filled; }
    public boolean getFilled() { return filled; }
    public void setColor(Color currentFillColor) { this.currentFillColor = currentFillColor; }
    public Color getColor() { return currentFillColor; }
}
