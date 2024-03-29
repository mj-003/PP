package items;

import helpers.Point;
import helpers.BoundingBox;

import java.awt.*;

public abstract class Item {
    protected Point position;
    protected boolean selected;
    protected BoundingBox boundingBox;

    public Item() {
        this.position = new Point(0, 0);
    }
    public Item(Point position)
    {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public boolean isSelected() {
        return selected;
    }
    public BoundingBox getBoundingBox() { return this.boundingBox; }

    public abstract void translate(int dx, int dy);
    public abstract void draw(Graphics g);
    public abstract void createBoundingBox();
    public abstract boolean contains(Point point);
}
